package adilsonarc.portfolio.blog.article.util;

import adilsonarc.portfolio.blog.article.Article;
import adilsonarc.portfolio.blog.article.controller.model.ArticleReadModel;
import adilsonarc.portfolio.blog.article.controller.model.ArticleWriteModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface ArticleMapper {
    List<ArticleReadModel> map(List<Article> articles);

    ArticleReadModel map(Article article);

    @Mapping(target = "id", ignore = true)
    Article map(ArticleWriteModel article);

    Article map(UUID id, ArticleWriteModel article);
}
