package com.keiro.keiro_backend.service;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.keiro.keiro_backend.dto.TopicRequest;
import com.keiro.keiro_backend.model.Topic;
import com.keiro.keiro_backend.model.User;
import com.keiro.keiro_backend.repository.TopicRepository;
import com.keiro.keiro_backend.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TopicService {

    private final TopicRepository topicRepository;
    private final UserRepository userRepository;

    private User getCurrentUser() {
        String email = SecurityContextHolder.getContext()
                .getAuthentication().getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public Topic createTopic(TopicRequest request) {
        User user = getCurrentUser();

        Topic topic = Topic.builder()
                .name(request.getName())
                .description(request.getDescription())
                .user(user)
                .build();

        return topicRepository.save(topic);
    }

    public List<Topic> getMyTopics() {
        User user = getCurrentUser();
        return topicRepository.findByUserId(user.getId());
    }

    public Topic getTopicById(Long id) {
        User user = getCurrentUser();
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tema no encontrado"));

        if (!topic.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("No tienes permiso para ver este tema");
        }

        return topic;
    }

    public void deleteTopic(Long id) {
        User user = getCurrentUser();
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tema no encontrado"));

        if (!topic.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("No tienes permiso para eliminar este tema");
        }

        topicRepository.delete(topic);
    }
}