/**
 * @author  Milad Karami
 * @since   2021-05-22
 */
package com.apsooba.springtest.services;

import com.apsooba.springtest.dtos.PostDto;
import com.apsooba.springtest.repositories.PostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    private final Logger logger = LoggerFactory.getLogger(getClass());


    public boolean save(PostDto PostDto) {
        try {
            postRepository.save(PostDto);
            return true;
        } catch (Exception e) {
            logger.error("error occurred in save post because of :"+ e.getMessage());
            return false;
        }

    }

    public PostDto findById(Long id) {
        try {
            return postRepository.findById(id).get();
        } catch (Exception e) {
            return null;
        }
    }

    public Iterable<PostDto> findAll() {
        try {
            return postRepository.findAll();
        } catch (Exception e) {
            return null;
        }
    }

    public Iterable<PostDto> saveAll(List<PostDto> PostDtoList) {
        return postRepository.saveAll(PostDtoList);
    }

    public boolean delete(Long id) {
        try {
            postRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            logger.error("error occurred in delete post because of :"+ e.getMessage());
            return false;
        }

    }

    public boolean deleteAll() {
        try {
            postRepository.deleteAll();
            return true;
        } catch (Exception e) {
            logger.error("error occurred in delete posts because of :"+ e.getMessage());
            return false;
        }
    }

    public boolean update(Long id, PostDto PostDto) {
        try {
            postRepository.deleteById(id);
            postRepository.save(PostDto);
            return true;
        } catch (Exception e) {
            logger.error("error occurred in update post because of :"+ e.getMessage());
            return false;
        }
    }
}
