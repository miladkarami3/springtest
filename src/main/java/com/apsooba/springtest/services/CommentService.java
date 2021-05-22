/**
 * @author  Milad Karami
 * @since   2021-05-22
 */
package com.apsooba.springtest.services;

import com.apsooba.springtest.dtos.CommentDto;
import com.apsooba.springtest.repositories.CommentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    private final Logger logger = LoggerFactory.getLogger(getClass());


    public boolean save(CommentDto CommentDto) {
        try {
            commentRepository.save(CommentDto);
            return true;
        } catch (Exception e) {
            logger.error("error occurred in save comment because of :" + e.getMessage());
            return false;
        }

    }

    public CommentDto findById(Long id) {
        try {
            return commentRepository.findById(id).get();
        } catch (Exception e) {
            return null;
        }
    }


    public Iterable<CommentDto> findAll() {
        try {
            return commentRepository.findAll();
        } catch (Exception e) {
            return null;
        }

    }

    public Iterable<CommentDto> saveAll(List<CommentDto> commentDtoList) {
        return commentRepository.saveAll(commentDtoList);
    }

    public boolean delete(Long id) {
        try {
            commentRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            logger.error("error occurred in delete comment because of :" + e.getMessage());
            return false;
        }

    }

    public boolean deleteAll() {
        try {
            commentRepository.deleteAll();
            return true;
        } catch (Exception e) {
            logger.error("error occurred in delete comments because of :" + e.getMessage());
            return false;
        }
    }

    public boolean update(Long id, CommentDto commentDto) {
        try {
            commentRepository.deleteById(id);
            commentRepository.save(commentDto);
            return true;
        } catch (Exception e) {
            logger.error("error occurred in update comment because of :" + e.getMessage());
            return false;

        }
    }
}
