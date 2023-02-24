package com.product.service.productservice.sevice;

import com.product.service.productservice.dao.PostRepository;
import com.product.service.productservice.dto.PostDto;
import com.product.service.productservice.model.Post;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Log4j2
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Post addPost(PostDto postDto) {
        Post post = postConstructor(postDto);
        postRepository.save(post);
        return post;
    }

    public Post getPostById(String id) {
        return postRepository.findById(id).orElseThrow();
    }

    private Post postConstructor(PostDto postDto) {
        Post post = new Post();
        post.setImagesId(postDto.getImagesId()); //todo image checker
        post.setDescriptionImageId(postDto.getDescriptionImageId());
        post.setName(postDto.getName());
        post.setDescription(postDto.getDescription());
        post.setPrice(postDto.getPrice());
        post.setSalePrice(null);
        post.setCreateDate(LocalDateTime.now()); //todo create mapper to normal date/time
        post.setActive(postDto.getActive());
        return post;
    }
}
