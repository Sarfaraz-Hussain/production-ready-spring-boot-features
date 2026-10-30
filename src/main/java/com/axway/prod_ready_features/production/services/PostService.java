package com.axway.prod_ready_features.production.services;

import com.axway.prod_ready_features.production.dto.PostDTO;

import java.util.List;

public interface PostService {
    List<PostDTO> getAllPosts();
    PostDTO createNewPost(PostDTO postDTO);

    PostDTO getPostById(Long id);

    PostDTO updatePost(Long id, PostDTO postDTO);
}