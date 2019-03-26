package com.total.login.repository;

import com.total.login.domain.posts.Posts;
import com.total.login.domain.posts.PostsRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;

/**
 * Created by kiseokhong on 2019. 3. 10..
 */
@RunWith(SpringRunner.class)
//@SpringBootTest
public class PostsRepositoryTest {
    @Autowired
    PostsRepository postsRepository;

//    @After
    public void clenup(){
        postsRepository.deleteAll();
    }

//    @Test
    public void test() {
        //given
        postsRepository.save(Posts.builder()
                .title("테스트 게시글")
                .content("테스트 본문")
                .author("jojoldu@gmail.com")
                .build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        Assert.assertThat(posts.getTitle(), is("테스트 게시글"));
        Assert.assertThat(posts.getContent(), is("테스트 본문"));
    }

//    @Test
    public void BaseTimeEntity_등록 () {

        //given
        LocalDateTime now = LocalDateTime.now();
        postsRepository.save(Posts.builder()
                .title("테스트 게시글")
                .content("테스트 본문")
                .author("jojoldu@gmail.com")
                .build());


        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        Assert.assertTrue(posts.getCreatedDate().isAfter(now));
        Assert.assertTrue(posts.getModifiedDate().isAfter(now));
    }

}
