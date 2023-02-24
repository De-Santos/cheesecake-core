package com.product.service.productservice.controller;

import com.product.service.productservice.dto.PostDto;
import com.product.service.productservice.model.Post;
import com.product.service.productservice.sevice.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {
    private final PostService postService;

    @PostMapping("/add")
    public ResponseEntity<Post> addPost(@RequestBody PostDto postDto) {
        log.info("add post in mongo database");
        log.debug(postDto);
        Post postResponse = postService.addPost(postDto);
        log.debug("added post: {}", postResponse);
        return ResponseEntity.ok(postResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPost(@PathVariable String id) {
        log.info("get product by id: " + id);
        return ResponseEntity.ok(postService.getPostById(id));
    }
}
