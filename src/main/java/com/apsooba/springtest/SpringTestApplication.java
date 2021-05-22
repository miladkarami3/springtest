/**
 * @author  Milad Karami
 * @since   2021-05-22
 */
package com.apsooba.springtest;

import com.apsooba.springtest.dtos.CommentDto;
import com.apsooba.springtest.dtos.PostDto;
import com.apsooba.springtest.services.CommentService;
import com.apsooba.springtest.services.PostService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;


@SpringBootApplication
public class SpringTestApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringTestApplication.class, args);
        scrapingPosts(context);
        scrapingComments(context);
    }

    public static void scrapingPosts(ConfigurableApplicationContext context) {
        RestTemplate restTemplate = context.getBean(RestTemplate.class);
        PostService postService = context.getBean(PostService.class);
        Runnable task = () -> {
            ResponseEntity<PostDto[]> response = restTemplate.getForEntity("https://jsonplaceholder.typicode.com/posts", PostDto[].class);
            List<PostDto> postDtoList = Arrays.asList(response.getBody());
            postService.saveAll(postDtoList);
        };
        task.run();
    }

    public static void scrapingComments(ConfigurableApplicationContext context) {
        RestTemplate restTemplate = context.getBean(RestTemplate.class);
        CommentService commentService = context.getBean(CommentService.class);
        Runnable task = () -> {
            ResponseEntity<CommentDto[]> response = restTemplate.getForEntity("https://jsonplaceholder.typicode.com/comments", CommentDto[].class);
            List<CommentDto> commentdtoList = Arrays.asList(response.getBody());
            commentService.saveAll(commentdtoList);
        };
        task.run();
    }

}
