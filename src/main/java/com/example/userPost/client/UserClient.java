package com.example.userPost.client;

import com.example.userPost.model.User;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
public class UserClient {

    private final WebClient webClient;

    public UserClient(WebClient.Builder webClient) {
        this.webClient = webClient.build();
    }

    public List<User> getUsers(){
        List<User> users = webClient.get()
                .uri("https://jsonplaceholder.typicode.com/users")
                .retrieve()
                .bodyToFlux(User.class)
                .collectList()
                .block();

        return users;
    }
}
