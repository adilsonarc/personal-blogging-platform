package adilsonarc.portfolio.blog.config;

import adilsonarc.portfolio.blog.article.util.ArticleMapper;
import adilsonarc.portfolio.blog.article.util.ArticleMapperImpl;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestConfig {

    @Bean
    public ArticleMapper articleMapper() {
        return new ArticleMapperImpl();  // replace with the actual implementation class
    }
}