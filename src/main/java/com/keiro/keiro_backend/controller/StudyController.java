package com.keiro.keiro_backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.keiro.keiro_backend.model.Card;
import com.keiro.keiro_backend.model.User;
import com.keiro.keiro_backend.repository.UserRepository;
import com.keiro.keiro_backend.service.StudyService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/study")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class StudyController {

    private final StudyService studyService;
    private final UserRepository userRepository;

    @PostMapping("/review/{cardId}")
    public ResponseEntity<Card> reviewCard(
            @PathVariable Long cardId,
            @RequestParam int quality) {
        return ResponseEntity.ok(studyService.reviewCard(cardId, quality));
    }

    @GetMapping("/due")
    public ResponseEntity<List<Card>> getDueCards() {
        String email = SecurityContextHolder.getContext()
                .getAuthentication().getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return ResponseEntity.ok(studyService.getCardsForReview(user.getId()));
    }
}
