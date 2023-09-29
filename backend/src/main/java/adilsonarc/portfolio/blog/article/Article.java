package adilsonarc.portfolio.blog.article;

import adilsonarc.portfolio.blog.article.attributes.Comment;
import adilsonarc.portfolio.blog.article.attributes.StatusEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;
    String title;
    String content;
    //User author;
    String summary;
    LocalDateTime publishedDate;
    LocalDateTime lastUpdated;
    // Set<String> tags;
    StatusEnum status;
    String thumbnailUrl;
    String slug;
    // List<Comment> comments;
    // Set<String> categories;
    Integer views;
    Integer likes;
    String source;
}
