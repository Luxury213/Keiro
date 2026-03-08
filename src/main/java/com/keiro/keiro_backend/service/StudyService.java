package com.keiro.keiro_backend.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.keiro.keiro_backend.model.Card;
import com.keiro.keiro_backend.repository.CardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudyService {

    private final CardRepository cardRepository;

    public Card reviewCard(Long cardId, int quality) {
        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new RuntimeException("Card no encontrada"));

        // Algoritmo SM-2
        if (quality >= 3) {
            if (card.getRepetitions() == 0) {
                card.setIntervalDays(1);
            } else if (card.getRepetitions() == 1) {
                card.setIntervalDays(6);
            } else {
                card.setIntervalDays((int) Math.round(card.getIntervalDays() * card.getEaseFactor()));
            }
            card.setRepetitions(card.getRepetitions() + 1);
            double newEase = card.getEaseFactor() + (0.1 - (5 - quality) * (0.08 + (5 - quality) * 0.02));
            card.setEaseFactor(Math.max(1.3, newEase));
        } else {
            card.setRepetitions(0);
            card.setIntervalDays(1);
        }

        card.setNextReview(LocalDateTime.now().plusDays(card.getIntervalDays()));
        return cardRepository.save(card);
    }

    public List<Card> getCardsForReview(Long userId) {
        return cardRepository.findByTopicIdAndNextReviewBefore(
                userId, LocalDateTime.now().plusDays(1));
    }
}
