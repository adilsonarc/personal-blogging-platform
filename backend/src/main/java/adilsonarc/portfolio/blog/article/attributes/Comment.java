package adilsonarc.portfolio.blog.article.attributes;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class Comment {
    String content;
    LocalDateTime date;
}
