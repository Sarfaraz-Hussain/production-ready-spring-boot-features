package com.axway.prod_ready_features.production.services;

import com.axway.prod_ready_features.production.dto.PostDTO;
import com.axway.prod_ready_features.production.entities.PostEntity;
import com.axway.prod_ready_features.production.exceptions.ResourceNotFoundException;
import com.axway.prod_ready_features.production.repos.PostRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;
    private final ModelMapper mapper;
    private final Logger log = LoggerFactory.getLogger(PostServiceImpl.class);

    @Override
    public List<PostDTO> getAllPosts() {
        try {
            log.debug("Fetching all posts");
            List<PostDTO> collect = postRepository.findAll().stream()
                    .map(post -> mapper.map(post, PostDTO.class))
                    .collect(Collectors.toList());
            log.debug("All posts fetched successfully");
            log.trace("The fetched posts are: {}", collect);
            return collect;
        }catch(Exception e){
            log.error("Exception occurred in PostServiceImpl", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public PostDTO createNewPost(PostDTO postDTO) {
        return mapper.map(postRepository.save(mapper.map(postDTO, PostEntity.class)), PostDTO.class);
    }

    @Override
    public PostDTO getPostById(Long id) {
        log.trace("Trying to fetch post by id with id: {}", id);
        try {
            PostEntity postEntity = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post Not Found with: " + id));
            log.debug("Post fetched successfully");
            log.trace("The fetched post is: {}", mapper.map(postEntity, PostDTO.class));
            return mapper.map(postEntity, PostDTO.class);
        }catch(Exception e){
            log.error("Exception occurred while fetching the post", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public PostDTO updatePost(Long id, PostDTO postDTO) {
        PostEntity olderPost = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not Found with id: " + id));
        postDTO.setId(olderPost.getId());
        mapper.map(postDTO, olderPost);
        return mapper.map(postRepository.save(olderPost), PostDTO.class);
    }
}
