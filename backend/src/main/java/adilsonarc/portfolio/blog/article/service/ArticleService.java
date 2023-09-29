package adilsonarc.portfolio.blog.article.service;

import adilsonarc.portfolio.blog.article.Article;
import adilsonarc.portfolio.blog.article.repository.ArticleRepository;
import adilsonarc.portfolio.blog.util.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;

@Service
@AllArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    public Optional<Article> findById(final UUID id) {
        return articleRepository.findById(id);
    }

    public Article create(final Article article) {
        return articleRepository.save(article);
    }

    public Article update(final Article updatedArticle) {
        return articleRepository.findById(updatedArticle.getId())
                .map(article -> articleRepository.save(updatedArticle))
                .orElseThrow(getResourceNotFoundException(updatedArticle.getId()));
    }

    public void delete(final UUID id) {
        Article deletedArticle = findById(id)
                .orElseThrow(getResourceNotFoundException(id));
        articleRepository.delete(deletedArticle);
    }

    private Supplier<ResourceNotFoundException> getResourceNotFoundException(final UUID id) {
        return () -> new ResourceNotFoundException("Article not found with id " + id);
    }
}
