package com.keiro.keiro_backend.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.keiro.keiro_backend.model.Card;

public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findByTopicId(Long topicId);
    List<Card> findByTopicIdAndNextReviewBefore(Long topicId, LocalDateTime date);
}