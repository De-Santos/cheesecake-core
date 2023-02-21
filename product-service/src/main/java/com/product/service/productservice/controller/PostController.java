package com.product.service.productservice.controller;

import com.product.service.productservice.dao.PostRepository;
import com.product.service.productservice.dto.PostDto;
import com.product.service.productservice.model.Post;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class PostController {

    private final PostRepository repository;

    @GetMapping("/getAll")
    public List<Post> getAllProducts(){
        return repository.findAll();
    }

    @PostMapping("/add")
    public Post add(@RequestBody PostDto postDto){
        Post post;
        post = new Post(
                "Hire will be UUID photo",
                postDto.getName(),
                postDto.getDescription(),
                postDto.getPrice(),
                java.time.LocalDate.now(),
                postDto.getActive());
        repository.save(post);
        return post;
        // TODO: 2/21/2023 compare with file-receiver
    }
}
