package adilsonarc.portfolio.blog.article.service;

import adilsonarc.portfolio.blog.article.Article;
import adilsonarc.portfolio.blog.article.repository.ArticleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    public Optional<Article> findById(UUID id) {
        return articleRepository.findById(id);
    }

    public Article create(Article article) {
        return articleRepository.save(article);
    }

    public Article update(Article article) {
        return articleRepository.save(article);
    }

    public void delete(UUID id) {
        articleRepository.findById(id)
                .ifPresent(article -> articleRepository.delete(article));
        ;
    }
}
