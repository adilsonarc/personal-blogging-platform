package adilsonarc.portfolio.blog.config;

import adilsonarc.portfolio.blog.article.util.ArticleMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestConfig {

    @Bean
    public ArticleMapper articleMapper() {
        return Mappers.getMapper(ArticleMapper.class);  // replace with the actual implementation class
    }
}