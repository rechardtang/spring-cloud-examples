package com.example.feign1.api;

import com.example.feign1.bean.Post;
import com.example.feign1.client.JSONPlaceHolderConfigClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/c2")
public class JSONPlaceHolderFallbackApi {

    private JSONPlaceHolderConfigClient jsonPlaceHolderClient;

    public JSONPlaceHolderFallbackApi(JSONPlaceHolderConfigClient jsonPlaceHolderClient) {
        this.jsonPlaceHolderClient = jsonPlaceHolderClient;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/posts")
    List<Post> getPosts(){
        return jsonPlaceHolderClient.getPosts();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/posts/{postId}", produces = "application/json")
    Post getPostById(@PathVariable("postId") Long postId){
        return jsonPlaceHolderClient.getPostById(postId);
    }

}
