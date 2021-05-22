/**
 * @author  Milad Karami
 * @since   2021-05-22
 */
package com.apsooba.springtest.controllers;

import com.apsooba.springtest.dtos.PostDto;
import com.apsooba.springtest.services.PostService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/post")
public class PostController {

    ObjectMapper objectMapper = new ObjectMapper();
    @Autowired

    PostService postService;

    @PostMapping("")
    ResponseEntity<JsonNode> save(@Valid @RequestBody PostDto postDto) throws JsonProcessingException {
        JSONObject jsonObject = new JSONObject();
        boolean result = postService.save(postDto);
        jsonObject.put("result", result);
        jsonObject.put("message", result ? "save operation successful" : "operation failed");
        JsonNode jsonNode = objectMapper.readTree(jsonObject.toString());
        return ResponseEntity.ok(jsonNode);
    }

    @GetMapping("/{id}")
    PostDto findById(@PathVariable Long id) {
        PostDto postDto = postService.findById(id);
        return postDto;
    }

    @GetMapping("")
    Iterable<PostDto> findAll() {
        Iterable<PostDto> postDtoList = postService.findAll();
        return postDtoList;
    }

    @DeleteMapping("/{id}")
    ResponseEntity<JsonNode> delete(@PathVariable Long id) throws JsonProcessingException {
        JSONObject jsonObject = new JSONObject();
        boolean result = postService.delete(id);
        jsonObject.put("result", result);
        jsonObject.put("message", result ? "delete operation successful" : "operation failed");
        JsonNode jsonNode = objectMapper.readTree(jsonObject.toString());
        return ResponseEntity.ok(jsonNode);
    }

    @DeleteMapping("/all")
    ResponseEntity<JsonNode> deleteAll() throws JsonProcessingException {
        JSONObject jsonObject = new JSONObject();
        boolean result = postService.deleteAll();
        jsonObject.put("result", result);
        jsonObject.put("message", result ? "delete operation successful" : "operation failed");
        JsonNode jsonNode = objectMapper.readTree(jsonObject.toString());
        return ResponseEntity.ok(jsonNode);
    }

    @PutMapping("/{id}")
    ResponseEntity<JsonNode> update(@Valid @RequestBody PostDto postDto, @PathVariable Long id) throws JsonProcessingException {
        boolean result = postService.update(id, postDto);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", result);
        jsonObject.put("message", result ? "update operation successful" : "operation failed");
        JsonNode jsonNode = objectMapper.readTree(jsonObject.toString());
        return ResponseEntity.ok(jsonNode);
    }
}
