package com.keiro.keiro_backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardRequest {

    @NotBlank(message = "La pregunta es requerida")
    private String question;

    @NotBlank(message = "La respuesta es requerida")
    private String answer;

    @NotNull(message = "El tema es requerido")
    private Long topicId;
}
