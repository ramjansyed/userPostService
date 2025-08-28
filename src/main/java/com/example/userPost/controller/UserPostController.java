package com.example.userPost.controller;

import com.example.userPost.model.UserPostDetails;
import com.example.userPost.service.UserPostService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserPostController {

    private final UserPostService userPostService;

    public UserPostController(UserPostService userPostService) {
        this.userPostService = userPostService;
    }

    @GetMapping("/details")
    public List<UserPostDetails> getUserDetails(){

        return userPostService.getUserPostDetails();

    }
}
