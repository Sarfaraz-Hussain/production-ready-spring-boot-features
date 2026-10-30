package com.axway.prod_ready_features.production.services;

import com.axway.prod_ready_features.production.dto.PostDTO;
import com.axway.prod_ready_features.production.entities.PostEntity;
import com.axway.prod_ready_features.production.exceptions.ResourceNotFoundException;
import com.axway.prod_ready_features.production.repos.PostRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;
    private final ModelMapper mapper;

    @Override
    public List<PostDTO> getAllPosts() {
       return postRepository.findAll().stream()
                .map(post -> mapper.map(post, PostDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public PostDTO createNewPost(PostDTO postDTO) {
        return mapper.map(postRepository.save(mapper.map(postDTO, PostEntity.class)), PostDTO.class);
    }

    @Override
    public PostDTO getPostById(Long id) {
        PostEntity postEntity = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post Not Found with: " + id));
        return mapper.map(postEntity, PostDTO.class);
    }

    @Override
    public PostDTO updatePost(Long id, PostDTO postDTO) {
        PostEntity olderPost = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not Found with id: " + id));
        postDTO.setId(olderPost.getId());
        mapper.map(postDTO, olderPost);
        return mapper.map(postRepository.save(olderPost), PostDTO.class);
    }
}
