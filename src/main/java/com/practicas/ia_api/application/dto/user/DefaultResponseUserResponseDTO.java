package com.practicas.ia_api.application.dto.user;

import com.practicas.ia_api.application.dto.DefaultResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "DefaultResponseUserResponseDTO", description = "Wrapper para respuestas de usuario")
public class DefaultResponseUserResponseDTO {
    private String message;
    private UserResponseDTO data;
    private boolean success;
}

