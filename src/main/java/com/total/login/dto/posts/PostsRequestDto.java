package com.total.login.dto.posts;

import com.total.login.domain.posts.Posts;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by kiseokhong on 2019. 3. 10..
 */
@Getter
@Setter
@NoArgsConstructor
public class PostsRequestDto {
    private String title;
    private String content;
    private String author;

    public Posts toEntity(){
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
