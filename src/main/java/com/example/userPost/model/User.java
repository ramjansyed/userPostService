package com.example.userPost.model;

public record User(int id, String name, String userName, String email, Address address,
                   String phone,String website, Company company) {
}
