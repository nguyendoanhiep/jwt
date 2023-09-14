package com.tom.restaurant.service.impl;

import com.tom.restaurant.repository.PostRepository;
import com.tom.restaurant.response.Response;
import com.tom.restaurant.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PostServiceImpl implements PostService {
    @Autowired
    PostRepository postRepository;
    @Override
    public Response<?> getAll(Pageable pageable, String search) {
      try{
          return Response.SUCCESS(postRepository.getAll(pageable,search));
      } catch (Exception e) {
          e.printStackTrace();
          return Response.FAIL();
      }
    }
}
