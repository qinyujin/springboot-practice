package com.aimer.mongo;

import com.aimer.mongo.service.Employee;
import com.aimer.mongo.service.Zips;
import com.mongodb.client.result.UpdateResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author :覃玉锦
 * @create :2024-01-22 21:55:00
 */
@SpringBootTest
class MongoApplicationTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void testCollection() {
        boolean exists = mongoTemplate.collectionExists("emp");
        if (exists) {
            mongoTemplate.dropCollection("emp");
        }

        mongoTemplate.createCollection("emp");
    }

    @Test
    public void testInsert() {
        Employee employee = new Employee(1, "小明", 30, 10000.00, new Date());

        //添加文档
        // sava:  _id存在时更新数据
        //mongoTemplate.save(employee);
        // insert： _id存在抛出异常   支持批量操作
        mongoTemplate.insert(employee);

        List<Employee> list = Arrays.asList(
                new Employee(2, "张三", 21, 5000.00, new Date()),
                new Employee(3, "李四", 26, 8000.00, new Date()),
                new Employee(4, "王五", 22, 8000.00, new Date()),
                new Employee(5, "张龙", 28, 6000.00, new Date()),
                new Employee(6, "赵虎", 24, 7000.00, new Date()),
                new Employee(7, "赵六", 28, 12000.00, new Date()));
        //插入多条数据
        mongoTemplate.insert(list, Employee.class);
    }

    @Test
    public void testFind(){

        System.out.println("==========查询所有文档===========");
        //查询所有文档
        List<Employee> list = mongoTemplate.findAll(Employee.class);
        list.forEach(System.out::println);

        System.out.println("==========根据_id查询===========");
        //根据_id查询
        Employee e = mongoTemplate.findById(1, Employee.class);
        System.out.println(e);

        System.out.println("==========findOne返回第一个文档===========");
        //如果查询结果是多个，返回其中第一个文档对象
        Employee one = mongoTemplate.findOne(new Query(), Employee.class);
        System.out.println(one);

        System.out.println("==========条件查询===========");
        //new Query() 表示没有条件
        //查询薪资大于等于8000的员工
        //Query query = new Query(Criteria.where("salary").gte(8000));
        //查询薪资大于4000小于10000的员工
        //Query query = new Query(Criteria.where("salary").gt(4000).lt(10000));
        //正则查询（模糊查询）  java中正则不需要有//
        //Query query = new Query(Criteria.where("name").regex("张"));

        //and  or  多条件查询
        Criteria criteria = new Criteria();
        //and  查询年龄大于25&薪资大于8000的员工
        //criteria.andOperator(Criteria.where("age").gt(25),Criteria.where("salary").gt(8000));
        //or 查询姓名是张三或者薪资大于8000的员工
        criteria.orOperator(Criteria.where("name").is("张三"),Criteria.where("salary").gt(5000));
        Query query = new Query(criteria);

        //sort排序
        //query.with(Sort.by(Sort.Order.desc("salary")));


        //skip limit 分页  skip用于指定跳过记录数，limit则用于限定返回结果数量。
        query.with(Sort.by(Sort.Order.desc("salary")))
                .skip(0)  //指定跳过记录数
                .limit(4);  //每页显示记录数


        //查询结果
        List<Employee> employees = mongoTemplate.find(
                query, Employee.class);
        employees.forEach(System.out::println);
    }

    @Test
    public void testFindByJson() {

        //使用json字符串方式查询
        //等值查询
//        String json = "{name:'张三'}";
        //多条件查询
        String json = "{$or:[{age:{$gt:25}},{salary:{$gte:8000}}]}";
        Query query = new BasicQuery(json);

        //查询结果
        List<Employee> employees = mongoTemplate.find(
                query, Employee.class);
        employees.forEach(System.out::println);
    }

    @Test
    public void testUpdate(){

        //query设置查询条件
        Query query = new Query(Criteria.where("salary").gte(15000));

        System.out.println("==========更新前===========");
        List<Employee> employees = mongoTemplate.find(query, Employee.class);
        employees.forEach(System.out::println);

        Update update = new Update();
        //设置更新属性
        update.set("salary",13000);

        //updateFirst() 只更新满足条件的第一条记录
        //UpdateResult updateResult = mongoTemplate.updateFirst(query, update, Employee.class);
        //updateMulti() 更新所有满足条件的记录
        //UpdateResult updateResult = mongoTemplate.updateMulti(query, update, Employee.class);

        //upsert() 没有符合条件的记录则插入数据
        //update.setOnInsert("id",11);  //指定_id
        UpdateResult updateResult = mongoTemplate.upsert(query, update, Employee.class);

        //返回修改的记录数
        System.out.println(updateResult.getModifiedCount());


        System.out.println("==========更新后===========");
        employees = mongoTemplate.find(query, Employee.class);
        employees.forEach(System.out::println);
    }

    @Test
    public void testDelete(){

        //删除所有文档
//        mongoTemplate.remove(new Query(),Employee.class);

        //条件删除
        Query query = new Query(Criteria.where("salary").gte(10000));
        mongoTemplate.remove(query,Employee.class);

    }

    /**
     * db.zips.aggregate( [
     *    { $group: { _id: "$state", totalPop: { $sum: "$pop" } } },
     *    { $match: { totalPop: { $gt: 10*1000*1000 } } }
     * ] )
     */
    @Test
    public void testAggregate1(){
        //$group
        GroupOperation groupOperation = Aggregation.group("state").sum("pop").as("totalPop");

        //$match
        MatchOperation matchOperation = Aggregation.match(
                Criteria.where("totalPop").gte(10*1000*1000));

        // 按顺序组合每一个聚合步骤
        TypedAggregation<Zips> typedAggregation = Aggregation.newAggregation(Zips.class,
                groupOperation, matchOperation);

        //执行聚合操作,如果不使用 Map，也可以使用自定义的实体类来接收数据
        AggregationResults<Map> aggregationResults = mongoTemplate.aggregate(typedAggregation, Map.class);
        // 取出最终结果
        List<Map> mappedResults = aggregationResults.getMappedResults();
        for(Map map:mappedResults){
            System.out.println(map);
        }

    }

    /**
     * db.zips.aggregate( [
     *    { $group: { _id: { state: "$state", city: "$city" }, cityPop: { $sum: "$pop" } } },
     *    { $group: { _id: "$_id.state", avgCityPop: { $avg: "$cityPop" } } },
     *    { $sort:{avgCityPop:-1}}
     * ] )
     */
    @Test
    public void testAggregate2(){
        //$group
        GroupOperation groupOperation = Aggregation.group("state","city").sum("pop").as("cityPop");
        //$group
        GroupOperation groupOperation2 = Aggregation.group("_id.state").avg("cityPop").as("avgCityPop");
        //$sort
        SortOperation sortOperation = Aggregation.sort(Sort.Direction.DESC,"avgCityPop");

        // 按顺序组合每一个聚合步骤
        TypedAggregation<Zips> typedAggregation = Aggregation.newAggregation(Zips.class,
                groupOperation, groupOperation2,sortOperation);

        //执行聚合操作,如果不使用 Map，也可以使用自定义的实体类来接收数据
        AggregationResults<Map> aggregationResults = mongoTemplate.aggregate(typedAggregation, Map.class);
        // 取出最终结果
        List<Map> mappedResults = aggregationResults.getMappedResults();
        for(Map map:mappedResults){
            System.out.println(map);
        }
    }

    /**
     * db.zips.aggregate( [
     *    { $group:
     *       {
     *         _id: { state: "$state", city: "$city" },
     *         pop: { $sum: "$pop" }
     *       }
     *    },
     *    { $sort: { pop: 1 } },
     *    { $group:
     *       {
     *         _id : "$_id.state",
     *         biggestCity:  { $last: "$_id.city" },
     *         biggestPop:   { $last: "$pop" },
     *         smallestCity: { $first: "$_id.city" },
     *         smallestPop:  { $first: "$pop" }
     *       }
     *    },
     *   { $project:
     *     { _id: 0,
     *       state: "$_id",
     *       biggestCity:  { name: "$biggestCity",  pop: "$biggestPop" },
     *       smallestCity: { name: "$smallestCity", pop: "$smallestPop" }
     *     }
     *   },
     *    { $sort: { state: 1 } }
     * ] )
     */
    @Test
    public void testAggregate3(){
        //$group
        GroupOperation groupOperation = Aggregation
                .group("state","city").sum("pop").as("pop");

        //$sort
        SortOperation sortOperation = Aggregation
                .sort(Sort.Direction.ASC,"pop");

        //$group
        GroupOperation groupOperation2 = Aggregation
                .group("_id.state")
                .last("_id.city").as("biggestCity")
                .last("pop").as("biggestPop")
                .first("_id.city").as("smallestCity")
                .first("pop").as("smallestPop");

        //$project
        ProjectionOperation projectionOperation = Aggregation
                .project("state","biggestCity","smallestCity")
                .and("_id").as("state")
                .andExpression(
                        "{ name: \"$biggestCity\",  pop: \"$biggestPop\" }")
                .as("biggestCity")
                .andExpression(
                        "{ name: \"$smallestCity\", pop: \"$smallestPop\" }"
                ).as("smallestCity")
                .andExclude("_id");

        //$sort
        SortOperation sortOperation2 = Aggregation
                .sort(Sort.Direction.ASC,"state");


        // 按顺序组合每一个聚合步骤
        TypedAggregation<Zips> typedAggregation = Aggregation.newAggregation(
                Zips.class, groupOperation, sortOperation, groupOperation2,
                projectionOperation,sortOperation2);

        //执行聚合操作,如果不使用 Map，也可以使用自定义的实体类来接收数据
        AggregationResults<Map> aggregationResults = mongoTemplate
                .aggregate(typedAggregation, Map.class);
        // 取出最终结果
        List<Map> mappedResults = aggregationResults.getMappedResults();
        for(Map map:mappedResults){
            System.out.println(map);
        }

    }
}