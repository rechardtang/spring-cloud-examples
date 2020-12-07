package com.example.feign1.client;

import com.example.feign1.bean.Post;
import com.example.feign1.fallback.JSONPlaceHolderFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "jplaceholderconfig", url = "https://jsonplaceholder.typicode.com/", fallback = JSONPlaceHolderFallback.class)
public interface JSONPlaceHolderFallbackClient {

    @RequestMapping(method = RequestMethod.GET, value = "/posts")
    List<Post> getPosts();

    @RequestMapping(method = RequestMethod.GET, value = "/posts/{postId}", produces = "application/json")
    Post getPostById(@PathVariable("postId") Long postId);
}
