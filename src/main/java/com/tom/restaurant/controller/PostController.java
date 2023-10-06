package com.tom.restaurant.controller;

import com.tom.restaurant.response.Response;
import com.tom.restaurant.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    PostService postService;
    @GetMapping("/getAll")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Response<?> getAll(@RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "10") int size,
                              @RequestParam String search
                              ) {
        return postService.getAll(PageRequest.of(page - 1, size), search);
    }
}
