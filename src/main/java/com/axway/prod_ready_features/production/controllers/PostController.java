package com.axway.prod_ready_features.production.controllers;

import com.axway.prod_ready_features.production.dto.PostDTO;
import com.axway.prod_ready_features.production.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public List<PostDTO> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping(path = "/{postId}")
    public PostDTO getPostById(@PathVariable(name = "postId") Long id) {
        return postService.getPostById(id);
    }

    @PostMapping
    public PostDTO createPost(@RequestBody PostDTO postDTO) {
        return postService.createNewPost(postDTO);
    }

    @PutMapping(path = "/{postId}")
    public PostDTO updatePost(@RequestBody PostDTO postDTO, @PathVariable(name = "postId") Long id) {
        return postService.updatePost(id,postDTO);
    }
}
