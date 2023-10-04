package adilsonarc.portfolio.blog.article.service;

import adilsonarc.portfolio.blog.article.Article;
import adilsonarc.portfolio.blog.article.repository.ArticleRepository;
import adilsonarc.portfolio.blog.util.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;

@Service
@AllArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    public List<Article> findAll() {
        final List<Article> foundArticles = articleRepository.findAll();
        return Collections.unmodifiableList(foundArticles);
    }

    public Optional<Article> findById(final UUID id) {
        return articleRepository.findById(id);
    }

    public Article create(final Article article) {
        return articleRepository.save(article);
    }

    @Transactional
    public Article update(final Article updatedArticle) {
        return findById(updatedArticle.getId())
                .map(article -> articleRepository.save(updatedArticle))
                .orElseThrow(getResourceNotFoundException(updatedArticle.getId()));
    }

    @Transactional
    public void deleteById(final UUID id) {
        Article deletedArticle = findById(id)
                .orElseThrow(getResourceNotFoundException(id));
        articleRepository.delete(deletedArticle);
    }

    private Supplier<ResourceNotFoundException> getResourceNotFoundException(final UUID id) {
        return () -> new ResourceNotFoundException("Article not found with id " + id);
    }
}
