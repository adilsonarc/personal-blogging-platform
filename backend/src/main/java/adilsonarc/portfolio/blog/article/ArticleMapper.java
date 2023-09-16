package adilsonarc.portfolio.blog.article;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ArticleMapper {
    List<ArticleReadModel> map(List<Article> articles);

    ArticleReadModel map(Article article);

    Article map(ArticleWriteModel article);
}
