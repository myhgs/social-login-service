package com.total.login.service.posts;

import com.total.login.domain.posts.PostsRepository;
import com.total.login.dto.posts.PostsRequestDto;
import com.total.login.dto.posts.PostsResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by kiseokhong on 2019. 3. 12..
 */

@AllArgsConstructor
@Service
public class PostsServiceImpl implements PostsService{

    PostsRepository postsRepository;

    @Transactional
    @Override
    public Long save(PostsRequestDto dto) {
        return postsRepository.save(dto.toEntity()).getId();
    }

    @Transactional(readOnly = true)
    @Override
    public List<PostsResponseDto> findAllDesc() {
        return postsRepository.findAllDesc()
                .map(PostsResponseDto::new)
                .collect(Collectors.toList());
    }
}
