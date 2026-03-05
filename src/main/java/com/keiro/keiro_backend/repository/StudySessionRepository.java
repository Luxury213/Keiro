package com.keiro.keiro_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.keiro.keiro_backend.model.StudySession;

public interface StudySessionRepository extends JpaRepository<StudySession, Long> {
    List<StudySession> findByUserId(Long userId);
    List<StudySession> findByCardId(Long cardId);
}
