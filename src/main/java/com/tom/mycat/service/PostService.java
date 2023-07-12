package com.tom.mycat.service;

import com.tom.mycat.entity.Post;
import com.tom.mycat.response.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {
    Response<?> getAll(Pageable pageable , String search);
}
