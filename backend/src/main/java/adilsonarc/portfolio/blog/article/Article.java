package adilsonarc.portfolio.blog.article;

import adilsonarc.portfolio.blog.article.attributes.StatusEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;
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
