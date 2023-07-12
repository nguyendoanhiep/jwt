package com.tom.mycat.service.impl;

import com.tom.mycat.repository.PostRepository;
import com.tom.mycat.response.Response;
import com.tom.mycat.service.PostService;
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
          return new Response<>(200,"Success",postRepository.getAll(pageable,search));
      }catch (Exception e){
          log.info(e.getMessage());
          return new Response<>(400,"Fail",null);

      }
    }
}
