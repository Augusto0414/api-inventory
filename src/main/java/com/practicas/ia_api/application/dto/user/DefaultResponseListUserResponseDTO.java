package com.practicas.ia_api.application.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "DefaultResponseListUserResponseDTO", description = "Wrapper para listas de usuarios")
public class DefaultResponseListUserResponseDTO {
    private String message;
    private List<UserResponseDTO> data;
    private boolean success;
}

