package adilsonarc.portfolio.blog.article.controller.model;

import adilsonarc.portfolio.blog.article.attributes.StatusEnum;

import java.time.LocalDateTime;

public record ArticleWriteModel(
        String title,
        String content,
        String summary,
        LocalDateTime publishedDate,
        LocalDateTime lastUpdated,
        StatusEnum status,
        String thumbnailUrl,
        String slug,
        Integer views,
        Integer likes,
        String source
) {
}
