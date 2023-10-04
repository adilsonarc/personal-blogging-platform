package adilsonarc.portfolio.blog.article.controller.model;

import adilsonarc.portfolio.blog.article.attributes.StatusEnum;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ArticleWriteModel {
    String title;
    String content;
    String summary;
    LocalDateTime publishedDate;
    LocalDateTime lastUpdated;
    StatusEnum status;
    String thumbnailUrl;
    String slug;
    Integer views;
    Integer likes;
    String source;
}
