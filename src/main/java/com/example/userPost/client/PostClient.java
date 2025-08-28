package com.example.userPost.client;

import com.example.userPost.model.Post;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
public class PostClient {

    private final WebClient webClient;

    public PostClient(WebClient.Builder webClient) {
        this.webClient = webClient.build();
    }

    public List<Post> getPosts(){
        List<Post> posts = webClient.get()
                .uri("https://jsonplaceholder.typicode.com/posts")
                .retrieve()
                .bodyToFlux(Post.class)
                .collectList()
                .block();

        return posts;
    }
}
