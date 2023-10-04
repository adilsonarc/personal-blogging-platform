package adilsonarc.portfolio.blog.article.service;

import adilsonarc.portfolio.blog.article.Article;
import adilsonarc.portfolio.blog.article.repository.ArticleRepository;
import adilsonarc.portfolio.blog.util.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@SpringBootTest
class ArticleServiceTest {

    @Mock
    private ArticleRepository articleRepository;

    @InjectMocks
    private ArticleService articleService;

    private final UUID articleId1 = UUID.fromString("cbc7c921-5aed-499f-93eb-068cf2dbd629");
    private final UUID articleId2 = UUID.fromString("1bf84713-1c1f-4fd6-be1d-fbc7469892fe");
    private final UUID articleIdNonExistent = UUID.fromString("1625226d-7991-4cc8-8d08-b0994438e896");
    private final Article article1 = Article.builder().id(articleId1).title("Title 1").content("Content 1").build();
    private final Article article2 = Article.builder().id(articleId2).title("Title 2").content("Content 2").build();

    @Test
    void givenArticles_whenFindAll_thenReturnAllArticles() {
        given(articleRepository.findAll()).willReturn(List.of(article1, article2));

        List<Article> articles = articleService.findAll();

        assertNotNull(articles);
        assertEquals(2, articles.size());

        verify(articleRepository).findAll();
    }

    @Test
    void givenId_whenFindById_thenReturnArticle() {
        given(articleRepository.findById(articleId1)).willReturn(Optional.of(article1));

        Optional<Article> foundArticle = articleService.findById(articleId1);

        assertTrue(foundArticle.isPresent());
        assertEquals(article1, foundArticle.get());

        verify(articleRepository).findById(articleId1);
    }

    @Test
    void givenNonExistentId_whenFindById_thenEmpty() {
        Optional<Article> foundArticle = articleService.findById(articleIdNonExistent);

        assertFalse(foundArticle.isPresent());

        verify(articleRepository).findById(articleIdNonExistent);
    }

    @Test
    void givenArticle_whenCreate_thenReturnCreatedArticle() {
        given(articleRepository.save(article1)).willReturn(article1);

        Article createdArticle = articleService.create(article1);

        assertNotNull(createdArticle);
        assertEquals(article1, createdArticle);

        verify(articleRepository).save(article1);
    }

    @Test
    void givenArticle_whenUpdatedArticle_thenReturnUpdatedArticle() {
        Article updatedArticle = article1.toBuilder().title("UpdatedTitle").build();

        given(articleRepository.findById(articleId1)).willReturn(Optional.of(article1));
        given(articleRepository.save(updatedArticle)).willReturn(updatedArticle);

        Article result = articleService.update(updatedArticle);

        assertNotNull(result);
        assertEquals("UpdatedTitle", result.getTitle());

        verify(articleRepository).save(updatedArticle);

    }

    @Test
    void givenNoArticle_whenUpdatedArticle_thenThrowException() {
        Article updatedArticle = article1.toBuilder().title("UpdatedTitle").build();

        given(articleRepository.findById(articleIdNonExistent)).willReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> articleService.update(updatedArticle));

        verify(articleRepository, never()).save(updatedArticle);

    }

    @Test
    void givenArticle_whenDeleteArticle_thenSuccessCall() {
        given(articleRepository.findById(articleId1)).willReturn(Optional.of(article1));

        articleService.deleteById(articleId1);

        verify(articleRepository).delete(article1);
    }

    @Test
    void givenNoArticle_whenDeleteArticle_thenThrowException() {
        given(articleRepository.findById(articleIdNonExistent)).willReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> articleService.deleteById(articleIdNonExistent));

        verify(articleRepository, never()).delete(any(Article.class));
    }
}
