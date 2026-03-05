package com.keiro.keiro_backend.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cards")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String question;

    @Column(nullable = false)
    private String answer;

    // Campos del algoritmo SM-2
    @Builder.Default
    @Column(name = "ease_factor")
    private Double easeFactor = 2.5;
    @Builder.Default
    @Column(name = "interval_days")
    private Integer intervalDays = 1;
    @Builder.Default
    private Integer repetitions = 0;

    @Column(name = "next_review")
    private LocalDateTime nextReview;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;

    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL)
    private List<StudySession> sessions;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        nextReview = LocalDateTime.now();
    }
}