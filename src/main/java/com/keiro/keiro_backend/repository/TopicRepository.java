package com.keiro.keiro_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.keiro.keiro_backend.model.Topic;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    List<Topic> findByUserId(Long userId);
}