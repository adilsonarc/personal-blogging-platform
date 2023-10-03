package adilsonarc.portfolio.blog.article.controller;

import adilsonarc.portfolio.blog.article.Article;
import adilsonarc.portfolio.blog.article.service.ArticleService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ArticleControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ArticleService articleService;

    @ParameterizedTest(name = "{index} => case: {0}")
    @MethodSource
    void givenArticles_whenGetAllArticles_thenJsonArticles(final String testCase,
                                                           final List<Article> articles
    ) throws Exception {
        assertNotNull(testCase); // avoid unused warning

        given(articleService.findAll()).willReturn(articles);

        mockMvc.perform(get("/articles"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").value(hasSize(articles.size())));
    }

    private static Stream<Arguments> givenArticles_whenGetAllArticles_thenJsonArticles() {
        return Stream.of(
                Arguments.of("No articles", Collections.emptyList()),
                Arguments.of("One article", List.of(
                        Article.builder()
                                .title("loner article")
                                .id(UUID.fromString("c361dfab-8ebb-482e-9ee5-95424cc12283"))
                                .build())),
                Arguments.of("Multiple articles", List.of(
                        Article.builder()
                                .title("one article")
                                .id(UUID.fromString("550e8400-e29b-41d4-a716-446655440000"))
                                .build(),
                        Article.builder()
                                .title("two article")
                                .id(UUID.fromString("38d02959-a684-4804-a0ea-8568058505ba"))
                                .build(),
                        Article.builder()
                                .title("tree article")
                                .id(UUID.fromString("c0b4824a-7a92-4822-a317-550ec4b8f42f"))
                                .build()))
        );
    }

    @Test
    void givenArticle_whenGetArticleById_thenJsonArticle() throws Exception {
        final String id = "a52f4d80-f140-4146-acb5-cb843cc5cb5a";
        final Article article = Article.builder().id(UUID.fromString(id)).title("article with id").build();

        given(articleService.findById(argThat(arg -> StringUtils.equals(arg.toString(), id))))
                .willReturn(Optional.of(article));

        mockMvc.perform(get("/articles/" + id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(article.getId().toString()))
                .andExpect(jsonPath("$.title").value(article.getTitle()));
    }

    @ParameterizedTest(name = "{index} => case: {0}, id:{1}")
    @MethodSource("getInvalidIdsAndRespectiveHttpErrorCodeArguments")
    void givenWrongId_whenGetArticleById_thenHttpErrorCode(final String testCase,
                                                           final String wrongID,
                                                           final int expectedStatus
    ) throws Exception {
        assertNotNull(testCase); // avoid unused warning

        mockMvc.perform(get("/articles/" + wrongID))
                .andExpect(status().is(expectedStatus));
    }

    @Test
    void givenNewArticle_whenCreateArticle_thenJsonNewArticle() throws Exception {
        final String id = "925198d4-6d23-4863-aa3f-f98431a74606";
        final String title = "New Article";
        final String content = "This is a new article.";
        final String body = String.format("{\"title\":\"%s\",\"content\":\"%s\"}", id, title, content);
        final Article article = Article.builder().id(UUID.fromString(id)).title(title).content(content).build();

        given(articleService.create(any(Article.class))).willReturn(article);

        mockMvc.perform(post("/articles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(article.getId().toString()))
                .andExpect(jsonPath("$.title").value(article.getTitle()))
                .andExpect(jsonPath("$.content").value(article.getContent()));
    }

    @Test
    void givenIdAndArticle_whenUpdateArticle_thenUpdatedJsonArticle() throws Exception {
        final String id = "a52f4d80-f140-4146-acb5-cb843cc5cb5a";
        final String title = "Updated Article";
        final String content = "Updated article content.";
        final String body = String.format("{\"title\":\"{%s}\",\"content\":\"{%s}\"}", title, content);
        final Article article = Article.builder().id(UUID.fromString(id)).title(title).content(content).build();

        given(articleService.update(any(Article.class))).willReturn(article);

        mockMvc.perform(put("/articles/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.title").value(article.getTitle()))
                .andExpect(jsonPath("$.content").value(article.getContent()));
    }

    @ParameterizedTest(name = "{index} => case: {0}, id:{1}")
    @MethodSource("getBadRequestIdsAndRespectiveHttpErrorCodeArguments")
    void givenWrongId_whenGetUpdate_thenHttpErrorCode(final String testCase,
                                                      final String wrongID,
                                                      final int expectedStatus
    ) throws Exception {
        assertNotNull(testCase); // avoid unused warning

        mockMvc.perform(put("/articles/" + wrongID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"updated article\",\"content\":\"Updated content.\"}"))
                .andExpect(status().is(expectedStatus));
    }

    @Test
    void givenId_whenDeleteArticle_thenReturnSuccessHttpCode() throws Exception {
        final String id = "a52f4d80-f140-4146-acb5-cb843cc5cb5a";

        mockMvc.perform(delete("/articles/" + id))
                .andExpect(status().isOk());
    }

    private static Stream<Arguments> getBadRequestIdsAndRespectiveHttpErrorCodeArguments() {
        return Stream.of(
                Arguments.of("Null id", null, HttpServletResponse.SC_BAD_REQUEST),
                Arguments.of("Blank id", "  ", HttpServletResponse.SC_BAD_REQUEST),
                Arguments.of("Gibberish uuid id", "Gibberish uuid id", HttpServletResponse.SC_BAD_REQUEST));
    }

    private static Stream<Arguments> getInvalidIdsAndRespectiveHttpErrorCodeArguments() {
        return Stream.concat(getBadRequestIdsAndRespectiveHttpErrorCodeArguments(),
                Stream.of(Arguments.of("Non existing id", "3c0969ac-c6e3-40f2-9fc8-2a59b8987918", HttpServletResponse.SC_NOT_FOUND)));
    }
}