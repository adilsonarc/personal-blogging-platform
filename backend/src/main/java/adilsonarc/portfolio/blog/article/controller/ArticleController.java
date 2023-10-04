package adilsonarc.portfolio.blog.article.controller;

import adilsonarc.portfolio.blog.article.Article;
import adilsonarc.portfolio.blog.article.controller.model.ArticleReadModel;
import adilsonarc.portfolio.blog.article.controller.model.ArticleWriteModel;
import adilsonarc.portfolio.blog.article.service.ArticleService;
import adilsonarc.portfolio.blog.article.util.ArticleMapper;
import adilsonarc.portfolio.blog.util.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/articles")
@AllArgsConstructor
public class ArticleController {
    private ArticleMapper articleMapper;
    private ArticleService articleService;

    @GetMapping
    public List<ArticleReadModel> getAllArticles() {
        return articleMapper.map(articleService.findAll());
    }

    @GetMapping("/{articleId}")
    public ArticleReadModel getArticleById(@PathVariable UUID articleId) {
        final Article foundArticle = articleService.findById(articleId)
                .orElseThrow(() -> new ResourceNotFoundException("Article not found with id " + articleId));
        return articleMapper.map(foundArticle);

    }

    @PostMapping
    public ArticleReadModel createArticle(@RequestBody ArticleWriteModel article) {
        return articleMapper.map(articleService.create(articleMapper.map(article)));
    }

    @PutMapping("/{articleId}")
    public ArticleReadModel updateArticle(@PathVariable UUID articleId,
                                          @RequestBody ArticleWriteModel article) {
        return articleMapper.map(articleService.update(articleMapper.map(articleId, article)));
    }

    @DeleteMapping("/{articleId}")
    public void deleteById(@PathVariable UUID articleId) {
        articleService.deleteById(articleId);
    }
}

