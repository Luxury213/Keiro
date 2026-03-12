package com.keiro.keiro_backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;

import com.keiro.keiro_backend.dto.TopicRequest;
import com.keiro.keiro_backend.model.Topic;
import com.keiro.keiro_backend.service.TopicService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/topics")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TopicController {

    private final TopicService topicService;

    @PostMapping
    public ResponseEntity<Topic> createTopic(@Valid @RequestBody TopicRequest request) {
        return ResponseEntity.ok(topicService.createTopic(request));
    }

    @GetMapping
    public ResponseEntity<List<Topic>> getMyTopics() {
        return ResponseEntity.ok(topicService.getMyTopics());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Topic> getTopicById(@PathVariable Long id) {
        return ResponseEntity.ok(topicService.getTopicById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTopic(@PathVariable Long id) {
        topicService.deleteTopic(id);
        return ResponseEntity.ok().build();
    }
@PutMapping("/{id}")
public ResponseEntity<Topic> updateTopic(@PathVariable Long id, @Valid @RequestBody TopicRequest request) {
    return ResponseEntity.ok(topicService.updateTopic(id, request));
}


}