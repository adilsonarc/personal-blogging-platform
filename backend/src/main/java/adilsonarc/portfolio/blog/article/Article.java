package adilsonarc.portfolio.blog.article;

import adilsonarc.portfolio.blog.article.attributes.Comment;
import adilsonarc.portfolio.blog.article.attributes.StatusEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
public class Article {
    UUID id;
    String title;
    String content;
    //User author;
    String summary;
    LocalDateTime publishedDate;
    LocalDateTime lastUpdated;
    Set<String> tags;
    StatusEnum status;
    String thumbnailUrl;
    String slug;
    List<Comment> comments;
    Set<String> categories;
    Integer views;
    Integer likes;
    String source;
}
