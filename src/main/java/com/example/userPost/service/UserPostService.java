package com.example.userPost.service;

import com.example.userPost.client.PostClient;
import com.example.userPost.client.UserClient;
import com.example.userPost.model.Post;
import com.example.userPost.model.User;
import com.example.userPost.model.UserPostDetails;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserPostService {

    private final PostClient postClient;
    private final UserClient userClient;

    public UserPostService(PostClient postClient, UserClient userClient) {
        this.postClient = postClient;
        this.userClient = userClient;
    }

    public List<UserPostDetails> getUserPostDetails() {
        List<User> users = null;
        List<Post> posts = null;

        try {
            users = userClient.getUsers();
            posts = postClient.getPosts();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (users == null || users.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Users not found");
        }

        if (posts == null || posts.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Posts not found");
        }

        Map<Integer, Post> postMap = posts.stream().collect(Collectors.toMap(Post::id, p -> p));


        return users.stream()
                .filter(user -> postMap.containsKey(user.id()))
                .map((user) -> {
                    Post post = postMap.get(user.id());
                    return new UserPostDetails(
                            user.id(),
                            user.address().geo().lat(),
                            user.address().geo().lng(),
                            post.title(),
                            post.body());
                }).toList();
    }
}
