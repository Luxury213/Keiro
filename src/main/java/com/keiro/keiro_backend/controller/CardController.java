package com.keiro.keiro_backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.keiro.keiro_backend.dto.CardRequest;
import com.keiro.keiro_backend.model.Card;
import com.keiro.keiro_backend.service.CardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/cards")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CardController {

    private final CardService cardService;

    @PostMapping
    public ResponseEntity<Card> createCard(@RequestBody CardRequest request) {
        return ResponseEntity.ok(cardService.createCard(request));
    }

    @GetMapping("/topic/{topicId}")
    public ResponseEntity<List<Card>> getCardsByTopic(@PathVariable Long topicId) {
        return ResponseEntity.ok(cardService.getCardsByTopic(topicId));
    }

    @GetMapping("/review")
    public ResponseEntity<List<Card>> getCardsForReview() {
        return ResponseEntity.ok(cardService.getCardsForReviewToday());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCard(@PathVariable Long id) {
        cardService.deleteCard(id);
        return ResponseEntity.ok().build();
    }
}