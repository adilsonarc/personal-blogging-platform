package adilsonarc.portfolio.blog.article.controller;

import adilsonarc.portfolio.blog.article.controller.model.ArticleReadModel;
import adilsonarc.portfolio.blog.article.controller.model.ArticleWriteModel;
import adilsonarc.portfolio.blog.article.service.ArticleService;
import adilsonarc.portfolio.blog.article.util.ArticleMapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/articles")
@AllArgsConstructor
public class ArticleController {
    private ArticleMapper mapper;
    private ArticleService articleService;

    @GetMapping
    public List<ArticleReadModel> getAllArticles() {
        return mapper.map(articleService.findAll());
    }

    @GetMapping("/{articleId}")
    public ArticleReadModel getArticleById(@PathVariable UUID articleId) {
        return mapper.map(articleService.findById(articleId).orElseThrow());
    }

    @PostMapping
    public ArticleReadModel createArticle(@RequestBody ArticleWriteModel article) {
        return mapper.map(articleService.create(mapper.map(article)));
    }

    @PutMapping("/{articleId}")
    public ArticleReadModel updateArticle(@PathVariable UUID articleId, @RequestBody ArticleWriteModel article) {
        return mapper.map(articleService.update(mapper.map(articleId, article)));
    }

    @DeleteMapping("/{articleId}")
    public void deleteArticle(@PathVariable UUID articleId) {
        articleService.delete(articleId);
    }
}

