package adilsonarc.portfolio.blog.article.service;

import adilsonarc.portfolio.blog.article.Article;
import adilsonarc.portfolio.blog.article.repository.ArticleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    public Optional<Article> findById(Long id) {
        return articleRepository.findById(id);
    }

    public Article create(Article article) {
        return articleRepository.create(article);
    }

    public Article update(Article article) {
        return articleRepository.update(article);
    }

    public void delete(Long id) {
        articleRepository.delete(id);
    }
}
