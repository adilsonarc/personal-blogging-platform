package adilsonarc.portfolio.blog.article.controller;

import adilsonarc.portfolio.blog.article.controller.model.ArticleReadModel;
import adilsonarc.portfolio.blog.article.controller.model.ArticleWriteModel;
import adilsonarc.portfolio.blog.article.service.ArticleService;
import adilsonarc.portfolio.blog.article.util.ArticleMapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{id}")
    public ArticleReadModel getArticle(@PathVariable Long id) {
        return mapper.map(articleService.findById(id).orElseThrow());
    }

    @PostMapping
    public ArticleReadModel createArticle(@RequestBody ArticleWriteModel article) {
        return mapper.map(articleService.create(mapper.map(article)));
    }

    @PutMapping("/{id}")
    public ArticleReadModel updateArticle(@PathVariable Long id, @RequestBody ArticleWriteModel article) {
        return mapper.map(articleService.update(mapper.map(article)));
    }

    @DeleteMapping("/{id}")
    public void deleteArticle(@PathVariable Long id) {
        articleService.delete(id);
    }
}

