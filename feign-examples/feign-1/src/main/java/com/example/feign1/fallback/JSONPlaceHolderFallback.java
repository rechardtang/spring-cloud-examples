package com.example.feign1.fallback;

import com.example.feign1.bean.Post;
import com.example.feign1.client.JSONPlaceHolderFallbackClient;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class JSONPlaceHolderFallback implements JSONPlaceHolderFallbackClient {
    @Override
    public List<Post> getPosts() {
        return Collections.emptyList();
    }

    @Override
    public Post getPostById(Long postId) {
        return null;
    }
}
