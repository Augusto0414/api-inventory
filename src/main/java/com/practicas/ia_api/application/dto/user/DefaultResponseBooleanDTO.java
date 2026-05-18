package com.practicas.ia_api.application.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "DefaultResponseBooleanDTO", description = "Wrapper para respuestas booleanas")
public class DefaultResponseBooleanDTO {
    private String message;
    private Boolean data;
    private boolean success;
}

