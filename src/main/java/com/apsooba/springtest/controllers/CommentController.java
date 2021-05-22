/**
 * @author  Milad Karami
 * @since   2021-05-22
 */
package com.apsooba.springtest.controllers;

import com.apsooba.springtest.dtos.CommentDto;
import com.apsooba.springtest.services.CommentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/comment")
public class CommentController {

    ObjectMapper objectMapper = new ObjectMapper();
    @Autowired

    CommentService commentService;

    @PostMapping("")
    ResponseEntity<JsonNode> save(@Valid @RequestBody CommentDto commentDto) throws JsonProcessingException {
        JSONObject jsonObject = new JSONObject();
        boolean result = commentService.save(commentDto);
        jsonObject.put("result", result);
        jsonObject.put("message", result ? "save operation successful" : "operation failed");
        JsonNode jsonNode = objectMapper.readTree(jsonObject.toString());
        return ResponseEntity.ok(jsonNode);
    }

    @GetMapping("/{id}")
    CommentDto findById(@PathVariable Long id) {
        CommentDto commentDto = commentService.findById(id);
        return commentDto;
    }

    @GetMapping("")
    Iterable<CommentDto> findAll() {
        Iterable<CommentDto> commentDtoList = commentService.findAll();
        return commentDtoList;
    }

    @DeleteMapping("/{id}")
    ResponseEntity<JsonNode> delete(@PathVariable Long id) throws JsonProcessingException {
        JSONObject jsonObject = new JSONObject();
        boolean result = commentService.delete(id);
        jsonObject.put("result", result);
        jsonObject.put("message", result ? "delete operation successful" : "operation failed");
        JsonNode jsonNode = objectMapper.readTree(jsonObject.toString());
        return ResponseEntity.ok(jsonNode);
    }

    @DeleteMapping("/all")
    ResponseEntity<JsonNode> deleteAll() throws JsonProcessingException {
        JSONObject jsonObject = new JSONObject();
        boolean result = commentService.deleteAll();
        jsonObject.put("result", result);
        jsonObject.put("message", result ? "delete operation successful" : "operation failed");
        JsonNode jsonNode = objectMapper.readTree(jsonObject.toString());
        return ResponseEntity.ok(jsonNode);
    }

    @PutMapping("/{id}")
    ResponseEntity<JsonNode> update(@Valid @RequestBody CommentDto CommentDto, @PathVariable Long id) throws JsonProcessingException {
        boolean result = commentService.update(id, CommentDto);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", result);
        jsonObject.put("message", result ? "update operation successful" : "operation failed");
        JsonNode jsonNode = objectMapper.readTree(jsonObject.toString());
        return ResponseEntity.ok(jsonNode);
    }
}
