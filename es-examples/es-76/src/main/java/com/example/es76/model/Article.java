package com.example.es76.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.InnerField;
import org.springframework.data.elasticsearch.annotations.MultiField;

import java.util.Arrays;
import java.util.List;

import static org.springframework.data.elasticsearch.annotations.FieldType.*;


@Document(indexName = "blog")
public class Article {

    @Id
    private String id;

    @MultiField(mainField = @Field(type = Text, fielddata = true, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word"), otherFields = {@InnerField(suffix = "verbatim", type = Keyword)})
    private String title;

    @Field(type = Nested, includeInParent = true)
    private List<Author> authors;

    @Field(type = Keyword)
    private String[] tags;

    @Field(type = Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String content;

    public Article() {
    }

    public Article(String title) {
        this.title = title;
    }

    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String... tags) {
        this.tags = tags;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", authors=" + authors +
                ", tags=" + Arrays.toString(tags) +
                '}';
    }
}
