package com.example.es76;

import com.example.es76.model.Article;
import com.example.es76.model.Author;
import com.example.es76.repository.ArticleRepository;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.document.Document;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static java.util.Arrays.asList;
import static org.elasticsearch.index.query.Operator.AND;
import static org.elasticsearch.index.query.QueryBuilders.*;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Es76ApplicationTests {

    @Autowired
    private ElasticsearchRestTemplate elasticsearchTemplate;

    @Autowired
    private ArticleRepository articleRepository;

    private final Author johnSmith = new Author("John Smith");
    private final Author johnDoe = new Author("John Doe");

//    @Before
//    public void setUp() {
//        Article article = new Article("Spring Data Elasticsearch");
//        article.setAuthors(asList(johnSmith, johnDoe));
//        article.setTags("elasticsearch", "spring data");
//        articleRepository.save(article);
//
//        article = new Article("Search engines");
//        article.setAuthors(asList(johnDoe));
//        article.setTags("search engines", "tutorial");
//        articleRepository.save(article);
//
//        article = new Article("Second Article About Elasticsearch");
//        article.setAuthors(asList(johnSmith));
//        article.setTags("elasticsearch", "spring data");
//        articleRepository.save(article);
//
//        article = new Article("Elasticsearch Tutorial");
//        article.setAuthors(asList(johnDoe));
//        article.setTags("elasticsearch");
//        articleRepository.save(article);
//    }

//    @After
//    public void tearDown() {
//        articleRepository.deleteAll();
//    }


    @Test
    public void givenArticleService_whenSaveArticle_thenIdIsAssigned() {
        final List<Author> authors = asList(new Author("John Smith"), johnDoe);

        Article article = new Article("Making Search Elastic");
        article.setAuthors(authors);

        article = articleRepository.save(article);
        assertNotNull(article.getId());
    }

    @Test
    public void givenPersistedArticles_whenSearchByAuthorsName_thenRightFound() {
        final Page<Article> articleByAuthorName = articleRepository.findByAuthorsName(johnSmith.getName(), PageRequest.of(0, 10));
        assertEquals(2L, articleByAuthorName.getTotalElements());
    }

    @Test
    public void givenCustomQuery_whenSearchByAuthorsName_thenArticleIsFound() {
        final Page<Article> articleByAuthorName = articleRepository.findByAuthorsNameUsingCustomQuery("Smith", PageRequest.of(0, 10));
        assertEquals(2L, articleByAuthorName.getTotalElements());
    }

    @Test
    public void givenTagFilterQuery_whenSearchByTag_thenArticleIsFound() {
        final Page<Article> articleByAuthorName = articleRepository.findByFilteredTagQuery("elasticsearch", PageRequest.of(0, 10));
        assertEquals(3L, articleByAuthorName.getTotalElements());
    }

    @Test
    public void givenTagFilterQuery_whenSearchByAuthorsName_thenArticleIsFound() {
        final Page<Article> articleByAuthorName = articleRepository.findByAuthorsNameAndFilteredTagQuery("Doe", "elasticsearch", PageRequest.of(0, 10));
        assertEquals(2L, articleByAuthorName.getTotalElements());
    }

    @Test
    public void givenPersistedArticles_whenUseRegexQuery_thenRightArticlesFound() {
        final Query searchQuery = new NativeSearchQueryBuilder().withFilter(regexpQuery("title", ".*data.*")).build();

        final SearchHits<Article> articles = elasticsearchTemplate.search(searchQuery, Article.class, IndexCoordinates.of("blog"));

        assertEquals(1, articles.getTotalHits());
    }

    @Test
    public void givenSavedDoc_whenTitleUpdated_thenCouldFindByUpdatedTitle() {
        final Query searchQuery = new NativeSearchQueryBuilder().withQuery(fuzzyQuery("title", "serch")).build();
        final SearchHits<Article> articles = elasticsearchTemplate.search(searchQuery, Article.class, IndexCoordinates.of("blog"));

        assertEquals(1, articles.getTotalHits());

        final Article article = articles.getSearchHit(0).getContent();
        final String newTitle = "Getting started with Search Engines";
        article.setTitle(newTitle);
        articleRepository.save(article);

        assertEquals(newTitle, articleRepository.findById(article.getId()).get().getTitle());
    }

    @Test
    public void givenSavedDoc_whenDelete_thenRemovedFromIndex() {
        final String articleTitle = "Spring Data Elasticsearch";

        final Query searchQuery = new NativeSearchQueryBuilder().withQuery(matchQuery("title", articleTitle).minimumShouldMatch("75%")).build();
        final SearchHits<Article> articles = elasticsearchTemplate.search(searchQuery, Article.class, IndexCoordinates.of("blog"));

        assertEquals(1, articles.getTotalHits());
        final long count = articleRepository.count();

        articleRepository.delete(articles.getSearchHit(0).getContent());

        assertEquals(count - 1, articleRepository.count());
    }

    @Test
    public void givenSavedDoc_whenOneTermMatches_thenFindByTitle() {
        final Query searchQuery = new NativeSearchQueryBuilder().withQuery(matchQuery("title", "Search engines").operator(AND)).build();
        final SearchHits<Article> articles = elasticsearchTemplate.search(searchQuery, Article.class, IndexCoordinates.of("blog"));
        assertEquals(1, articles.getTotalHits());
    }


    @Test
    public void chineseAnalyzer() {
//        Article article1 = new Article("今天是个阳光明媚的日子");
//        article1.setAuthors(asList(new Author("张三"), new Author("李四")));
//        article1.setTags("阳光", "天气");
//        articleRepository.save(article1);
//
//        Article article2 = new Article("美国疫情乱象频出");
//        article2.setAuthors(asList(new Author("新闻")));
//        article2.setTags("疫情");
//        articleRepository.save(article2);

//        Article article3 = new Article("天道酬勤", "不积跬步无以至千里");
//        article3.setAuthors(asList(new Author("天道")));
//        article3.setTags("天道", "酬勤");
//        articleRepository.save(article3);
//
//        final Page<Article> articleByAuthorName = articleRepository.findByTitle("千里", PageRequest.of(0, 10));
//        assertEquals(1L, articleByAuthorName.getTotalElements());
//
//        QueryBuilder query = QueryBuilders.matchQuery("content", "天道").analyzer("ik_max_word");
//        Query searchQuery = new NativeSearchQueryBuilder().withQuery(query)
//                .withHighlightFields(new HighlightBuilder.Field("content").preTags("<em>").postTags("</em>")).build();
//        SearchHits<Article> articleSearchHits = elasticsearchTemplate.search(searchQuery, Article.class, IndexCoordinates.of("blog"));
//        Article content = articleSearchHits.getSearchHit(0).getContent();
//        System.out.println(content);
        Page<Article> page = articleRepository.findByTitle("千里", PageRequest.of(0, 10));
        System.out.println(page);

    }
}
