package algorithm;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.web.SpringServletContainerInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.IOException;
import java.io.Reader;

/**
 * @author :覃玉锦
 * @create :2021-01-21 20:59:01
 */
@EnableWebMvc
public class Practice {
    public static void main(String args[]) throws IOException {
        Practice p = new Practice();

        String resource = "mybatis-config.xml";
        Reader reader = Resources.getResourceAsReader(resource);

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession session = sqlSessionFactory.openSession();

        session.getMapper(Practice.class);
        Object o = session.selectOne("com.aimer.selectById");

        //region springMVC
        SpringServletContainerInitializer springServletContainerInitializer;
        DispatcherServlet dispatcherServlet;
        ContextLoaderListener contextLoaderListener;
        //endregion
    }
}