package com.keiro.keiro_backend.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.keiro.keiro_backend.dto.CardRequest;
import com.keiro.keiro_backend.model.Card;
import com.keiro.keiro_backend.model.Topic;
import com.keiro.keiro_backend.model.User;
import com.keiro.keiro_backend.repository.CardRepository;
import com.keiro.keiro_backend.repository.TopicRepository;
import com.keiro.keiro_backend.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;
    private final TopicRepository topicRepository;
    private final UserRepository userRepository;

    private User getCurrentUser() {
        String email = SecurityContextHolder.getContext()
                .getAuthentication().getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public Card createCard(CardRequest request) {
        Topic topic = topicRepository.findById(request.getTopicId())
                .orElseThrow(() -> new RuntimeException("Tema no encontrado"));

        Card card = Card.builder()
                .question(request.getQuestion())
                .answer(request.getAnswer())
                .topic(topic)
                .build();

        return cardRepository.save(card);
    }

    public List<Card> getCardsByTopic(Long topicId) {
        return cardRepository.findByTopicId(topicId);
    }

    public List<Card> getCardsForReviewToday() {
        User user = getCurrentUser();
        return cardRepository.findByTopicIdAndNextReviewBefore(
                user.getId(), LocalDateTime.now().plusDays(1));
    }

    public void deleteCard(Long id) {
        cardRepository.deleteById(id);
    }
}
