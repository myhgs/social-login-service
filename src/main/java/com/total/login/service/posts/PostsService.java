package com.total.login.service.posts;

import com.total.login.dto.posts.PostsRequestDto;
import com.total.login.dto.posts.PostsResponseDto;

import java.util.List;

/**
 * Created by kiseokhong on 2019. 3. 12..
 */
public interface PostsService {

    Long save(PostsRequestDto dto);

    List<PostsResponseDto> findAllDesc();

}
