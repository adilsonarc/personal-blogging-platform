package adilsonarc.portfolio.blog.article.attributes;

import adilsonarc.portfolio.blog.user.User;
import lombok.Value;

import java.time.LocalDateTime;

@Value
public class Comment {
    User commenter;
    String content;
    String comment;
    LocalDateTime date;
}
