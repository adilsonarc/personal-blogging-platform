package adilsonarc.portfolio.blog.article.repository;

import adilsonarc.portfolio.blog.article.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ArticleRepository extends JpaRepository<Article, UUID> {

}
