package com.keiro.keiro_backend.model;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@Table(name = "topics")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "user_id")
@JsonIgnoreProperties({"topics", "password", "enabled", "authorities", 
    "accountNonLocked", "accountNonExpired", "credentialsNonExpired", "username"})
private User user;

@OneToMany(mappedBy = "topic", cascade = CascadeType.ALL)
@JsonIgnoreProperties("topic")
private List<Card> cards;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}