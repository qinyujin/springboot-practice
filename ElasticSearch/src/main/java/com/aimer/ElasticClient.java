package com.aimer;

import com.aimer.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.MaxAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;

import java.io.IOException;

/**
 * @Author:yujinqin
 * @Date:2023/2/2 14:20
 */
public class ElasticClient {

    public static void main(String[] args) throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));

        System.out.println(client);

        //index s
//        createIndex(client);

//        getIndexes(client);

//        deleteIndex(client);

        //doc s
//        insertDoc(client);

//        updateDoc(client);

//        getDoc(client);

//        deleteDoc(client);

//        batchInsert(client);

//        batchDeletes(client);

//        queryAllDocData(client);

//        queryDocWithTerm(client);

//        queryDocByPage(client);

//        queryDocBySort(client);

//        queryByBoolCondition(client);

//        queryByRangeCondition(client);

//        queryByFuzzyMatch(client);

//        queryByHighlight(client);

        queryWithAggregationAndGroupBy(client);

        //关闭客户端连接
        client.close();
    }

    //region Index s
    public static void createIndex(RestHighLevelClient client) throws IOException {
        //创建user2索引
        CreateIndexRequest request = new CreateIndexRequest("user2");
        CreateIndexResponse response = client.indices().create(request, RequestOptions.DEFAULT);

        //是否接收到并且确认
        boolean acknowledged = response.isAcknowledged();
        System.out.println("操作状态 = " + acknowledged);
    }

    public static void getIndexes(RestHighLevelClient client) throws IOException {
        GetIndexRequest request = new GetIndexRequest("user2");
        GetIndexResponse response = client.indices().get(request, RequestOptions.DEFAULT);

        System.out.println("aliases:" + response.getAliases());
        System.out.println("mappings:" + response.getMappings());
        System.out.println("settings:" + response.getSettings());

    }

    public static void deleteIndex(RestHighLevelClient client) throws IOException {
        DeleteIndexRequest request = new DeleteIndexRequest("user2");
        AcknowledgedResponse response = client.indices().delete(request, RequestOptions.DEFAULT);
        System.out.println("操作结果:" + response.isAcknowledged());
    }
    //endregion

    //region Doc s
    public static void insertDoc(RestHighLevelClient client) throws IOException {
        IndexRequest request = new IndexRequest();
        //setting index info
        request.index("user").id("1001");

        //create biz data object
        User user = new User();
        user.setName("张三");
        user.setAge(30);
        user.setSex("男");

        //use json format for transport
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStr = objectMapper.writeValueAsString(user);
        //if didn't choose contentType, default use json
        request.source(jsonStr, XContentType.JSON);

        IndexResponse response = client.index(request, RequestOptions.DEFAULT);

        System.out.println("_index:" + response.getIndex());
        System.out.println("_id:" + response.getId());
        System.out.println("_result:" + response.getResult());
    }

    public static void updateDoc(RestHighLevelClient client) throws IOException {
        UpdateRequest request = new UpdateRequest();
        request.index("user").id("1001");
        request.doc(XContentType.JSON, "sex", "女");
        UpdateResponse response = client.update(request, RequestOptions.DEFAULT);

        System.out.println("_index:" + response.getIndex());
        System.out.println("_id:" + response.getId());
        System.out.println("_result:" + response.getResult());
    }

    public static void getDoc(RestHighLevelClient client) throws IOException {
        GetRequest request = new GetRequest();
        request.index("user").id("1001");
        GetResponse response = client.get(request, RequestOptions.DEFAULT);

        System.out.println("_index:" + response.getIndex());
        System.out.println("_type:" + response.getType());
        System.out.println("_id:" + response.getId());
        System.out.println("_source:" + response.getSource());
    }

    public static void deleteDoc(RestHighLevelClient client) throws IOException {
        DeleteRequest request = new DeleteRequest();
        request.index("user").id("1001");
        DeleteResponse response = client.delete(request, RequestOptions.DEFAULT);
        System.out.println(response.toString());
    }

    public static void batchInsert(RestHighLevelClient client) throws IOException {
        BulkRequest request = new BulkRequest();
        request.add(new IndexRequest().index("user").id("1001").source(XContentType.JSON, "name", "zhangsan", "age", 30));
        request.add(new IndexRequest().index("user").id("1002").source(XContentType.JSON, "name", "lisi", "age", 18));
        request.add(new IndexRequest().index("user").id("1003").source(XContentType.JSON, "name", "wangwu", "age", 22));

        BulkResponse response = client.bulk(request, RequestOptions.DEFAULT);
        System.out.println("_took:" + response.getTook());
        System.out.println("_items:" + response.getItems());
    }

    public static void batchDeletes(RestHighLevelClient client) throws IOException {
        BulkRequest request = new BulkRequest();
        request.add(new DeleteRequest().index("user").id("1001"));
        request.add(new DeleteRequest().index("user").id("1002"));
        request.add(new DeleteRequest().index("user").id("1003"));

        BulkResponse response = client.bulk(request, RequestOptions.DEFAULT);

        System.out.println("_took:" + response.getTook());
        System.out.println("_items:" + response.getItems());
    }

    public static void queryAllDocData(RestHighLevelClient client) throws IOException {
        SearchRequest request = new SearchRequest();
        //choose definite index
        request.indices("user");

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        //query all
        sourceBuilder.query(QueryBuilders.matchAllQuery());
        request.source(sourceBuilder);

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        printSearchResultInfos(response);
    }

    //region advanced query
    //term means 条件
    public static void queryDocWithTerm(RestHighLevelClient client) throws IOException {
        SearchRequest request = new SearchRequest();
        request.indices("user");

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.termQuery("age", "30"));
        request.source(sourceBuilder);

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        printSearchResultInfos(response);
    }

    public static void queryDocByPage(RestHighLevelClient client) throws IOException {
        SearchRequest request = new SearchRequest();
        request.indices("user");

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.matchAllQuery());
        sourceBuilder.from(0);
        sourceBuilder.size(2);
        request.source(sourceBuilder);

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        printSearchResultInfos(response);
    }

    public static void queryDocBySort(RestHighLevelClient client) throws IOException {
        SearchRequest request = new SearchRequest();
        request.indices("user");

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.matchAllQuery());
        sourceBuilder.sort("age", SortOrder.ASC);
        request.source(sourceBuilder);

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        printSearchResultInfos(response);
    }

    public static void queryByBoolCondition(RestHighLevelClient client) throws IOException {
        SearchRequest request = new SearchRequest();
        request.indices("user");

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        //1 similar to 'and' in mysql
//        boolQueryBuilder.must(QueryBuilders.matchQuery("name", "zhangsan"));
//        boolQueryBuilder.must(QueryBuilders.matchQuery("age", "30"));

        //means age != 20
//        boolQueryBuilder.mustNot(QueryBuilders.matchQuery("age", "20"));

        //3 similar to 'or' in mysql
        boolQueryBuilder.should(QueryBuilders.matchQuery("name", "lisi"));
        boolQueryBuilder.should(QueryBuilders.matchQuery("name", "wangwu"));

        //use bool queryBuilder
        sourceBuilder.query(boolQueryBuilder);
        request.source(sourceBuilder);

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        printSearchResultInfos(response);
    }

    public static void queryByRangeCondition(RestHighLevelClient client) throws IOException {
        SearchRequest request = new SearchRequest();
        request.indices("user");

        RangeQueryBuilder rangeQueryBuilder = new RangeQueryBuilder("age");
        //more than
//        rangeQueryBuilder.gte(20);

        //less than
        rangeQueryBuilder.lte(20);

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(rangeQueryBuilder);
        request.source(sourceBuilder);

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        printSearchResultInfos(response);
    }

    public static void queryByFuzzyMatch(RestHighLevelClient client) throws IOException {
        SearchRequest request = new SearchRequest();
        request.indices("user");

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        //Fuzziness.ONE means can endure error of 1 character
//        sourceBuilder.query(QueryBuilders.fuzzyQuery("name", "wangw").fuzziness(Fuzziness.ONE));
        sourceBuilder.query(QueryBuilders.fuzzyQuery("name", "angwu").fuzziness(Fuzziness.ONE));
        request.source(sourceBuilder);

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        printSearchResultInfos(response);
    }

    public static void queryByHighlight(RestHighLevelClient client) throws IOException {
        SearchRequest request = new SearchRequest();
        request.indices("user");

        HighlightBuilder highlightBuilder = new HighlightBuilder();
        //pre character decoration
        highlightBuilder.preTags("<font color='...'>");
        //behind character decoration
        highlightBuilder.postTags("</font>");
        highlightBuilder.field("name");

        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("name", "zhangsan");

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.highlighter(highlightBuilder);
        sourceBuilder.query(termQueryBuilder);
        request.source(sourceBuilder);

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        printSearchResultInfos(response);
    }

    public static void queryWithAggregationAndGroupBy(RestHighLevelClient client) throws IOException {
        SearchRequest request = new SearchRequest();
        request.indices("user");

        //aggregation-max
        MaxAggregationBuilder maxAggregationBuilder = AggregationBuilders.max("maxAge").field("age");

        //similar to 'group by' in mysql
        TermsAggregationBuilder groupBy = AggregationBuilders.terms("age_groupby").field("age");

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
//        sourceBuilder.aggregation(maxAggregationBuilder);
        sourceBuilder.aggregation(groupBy);

        request.source(sourceBuilder);

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        System.out.println(response);
    }
    //endregion
    //endregion

    //common method
    public static void printSearchResultInfos(SearchResponse response) {
        SearchHits hits = response.getHits();
        System.out.println("took:" + response.getTook());
        System.out.println("timeout:" + response.isTimedOut());
        System.out.println("total:" + hits.getTotalHits());
        System.out.println("MaxScore:" + hits.getMaxScore());
        System.out.print("hits:");
        hits.forEach(x -> System.out.print(x + " "));
    }
}
