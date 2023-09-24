package adilsonarc.portfolio.blog.article.repository;

import adilsonarc.portfolio.blog.article.Article;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ArticleRepository {
    public List<Article> findAll() {
        return List.of();
    }

    public Optional<Article> findById(Long id) {
        return Optional.empty();
    }

    public Article create(Article article) {
        return new Article();
    }

    public Article update(Article article) {
        return new Article();
    }

    public void delete(Long id) {
    }
}
