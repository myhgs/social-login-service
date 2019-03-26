package com.total.login.web;

import com.total.login.dto.posts.PostsRequestDto;
import com.total.login.dto.posts.PostsResponseDto;
import com.total.login.service.posts.PostsService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * Created by kiseokhong on 2019. 3. 12..
 */
@RestController
@AllArgsConstructor
public class PostRestController {

    private PostsService postsService;
    private Environment env;

    @PostMapping("/posts")
    public Long savePosts(@RequestBody PostsRequestDto dto){
        return postsService.save(dto);
    }

    @GetMapping("/posts")
    public List<PostsResponseDto> getPosts(){
        return postsService.findAllDesc();
    }

    @GetMapping("/profile")
    public String getProfile () {
        return Arrays.stream(env.getActiveProfiles())
                .findFirst()
                .orElse("");
    }
}
