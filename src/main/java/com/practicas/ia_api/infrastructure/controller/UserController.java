package com.practicas.ia_api.infrastructure.controller;

import com.practicas.ia_api.application.dto.DefaultResponse;
import com.practicas.ia_api.application.dto.user.CreateUserDTO;
import com.practicas.ia_api.application.dto.user.UpdateUserDTO;
import com.practicas.ia_api.application.dto.user.UserResponseDTO;
import com.practicas.ia_api.application.dto.user.DefaultResponseUserResponseDTO;
import com.practicas.ia_api.application.dto.user.DefaultResponseListUserResponseDTO;
import com.practicas.ia_api.application.dto.user.DefaultResponseBooleanDTO;
import com.practicas.ia_api.application.service.interfaces.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ArraySchema;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "user-controller", description = "Operaciones sobre usuarios")
public class UserController {

    private final IUserService userService;

    /**
     * Crear un nuevo usuario
     * POST /api/v1/users
     */
    @Operation(summary = "Crear usuario", description = "Crea un nuevo usuario",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Usuario creado",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = DefaultResponseUserResponseDTO.class)))
            })
    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserDTO createUserDTO) {
        log.info("POST /api/v1/users - Creando nuevo usuario");
        try {
            UserResponseDTO userResponseDTO = userService.createUser(createUserDTO);
            DefaultResponse<?> response = DefaultResponse.builder()
                    .success(true)
                    .message("Usuario creado exitosamente")
                    .data(userResponseDTO)
                    .build();
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            log.error("Error al crear usuario: {}", e.getMessage());
            DefaultResponse<?> response = DefaultResponse.builder()
                    .success(false)
                    .message(e.getMessage())
                    .build();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    /**
     * Obtener todos los usuarios
     * GET /api/v1/users
     */
    @Operation(summary = "Obtener todos los usuarios", description = "Devuelve la lista de usuarios",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = DefaultResponseListUserResponseDTO.class)))
            })
    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        log.info("GET /api/v1/users - Obteniendo todos los usuarios");
        try {
            List<UserResponseDTO> users = userService.getAllUsers();
            DefaultResponse<?> response = DefaultResponse.builder()
                    .success(true)
                    .message("Usuarios obtenidos exitosamente")
                    .data(users)
                    .build();
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error al obtener usuarios: {}", e.getMessage());
            DefaultResponse<?> response = DefaultResponse.builder()
                    .success(false)
                    .message("Error al obtener usuarios")
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    /**
     * Obtener usuario por ID
     * GET /api/v1/users/{id}
     */
    @Operation(summary = "Obtener usuario por ID", description = "Devuelve un usuario por su ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = DefaultResponseUserResponseDTO.class))),
                    @ApiResponse(responseCode = "404", description = "No encontrado")
            })
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable UUID id) {
        log.info("GET /api/v1/users/{} - Obteniendo usuario por ID", id);
        try {
            UserResponseDTO userResponseDTO = userService.getUserById(id);
            DefaultResponse<?> response = DefaultResponse.builder()
                    .success(true)
                    .message("Usuario obtenido exitosamente")
                    .data(userResponseDTO)
                    .build();
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            log.error("Usuario no encontrado: {}", e.getMessage());
            DefaultResponse<?> response = DefaultResponse.builder()
                    .success(false)
                    .message(e.getMessage())
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    /**
     * Obtener usuario por email
     * GET /api/v1/users/email/{email}
     */
    @Operation(summary = "Obtener usuario por email", description = "Devuelve un usuario por su email",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = DefaultResponseUserResponseDTO.class))),
                    @ApiResponse(responseCode = "404", description = "No encontrado")
            })
    @GetMapping("/email/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
        log.info("GET /api/v1/users/email/{} - Obteniendo usuario por email", email);
        try {
            UserResponseDTO userResponseDTO = userService.getUserByEmail(email);
            DefaultResponse<?> response = DefaultResponse.builder()
                    .success(true)
                    .message("Usuario obtenido exitosamente")
                    .data(userResponseDTO)
                    .build();
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            log.error("Usuario no encontrado: {}", e.getMessage());
            DefaultResponse<?> response = DefaultResponse.builder()
                    .success(false)
                    .message(e.getMessage())
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    /**
     * Obtener usuario por nombre de usuario
     * GET /api/v1/users/username/{userName}
     */
    @Operation(summary = "Obtener usuario por nombre de usuario", description = "Devuelve un usuario por su nombre de usuario",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = DefaultResponseUserResponseDTO.class))),
                    @ApiResponse(responseCode = "404", description = "No encontrado")
            })
    @GetMapping("/username/{userName}")
    public ResponseEntity<?> getUserByUserName(@PathVariable String userName) {
        log.info("GET /api/v1/users/username/{} - Obteniendo usuario por nombre", userName);
        try {
            UserResponseDTO userResponseDTO = userService.getUserByUserName(userName);
            DefaultResponse<?> response = DefaultResponse.builder()
                    .success(true)
                    .message("Usuario obtenido exitosamente")
                    .data(userResponseDTO)
                    .build();
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            log.error("Usuario no encontrado: {}", e.getMessage());
            DefaultResponse<?> response = DefaultResponse.builder()
                    .success(false)
                    .message(e.getMessage())
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    /**
     * Actualizar usuario
     * PUT /api/v1/users/{id}
     */
    @Operation(summary = "Actualizar usuario", description = "Actualiza un usuario existente",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Usuario actualizado",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = DefaultResponseUserResponseDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Bad request")
            })
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable UUID id, @Valid @RequestBody UpdateUserDTO updateUserDTO) {
        log.info("PUT /api/v1/users/{} - Actualizando usuario", id);
        try {
            UserResponseDTO userResponseDTO = userService.updateUser(id, updateUserDTO);
            DefaultResponse<?> response = DefaultResponse.builder()
                    .success(true)
                    .message("Usuario actualizado exitosamente")
                    .data(userResponseDTO)
                    .build();
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            log.error("Error al actualizar usuario: {}", e.getMessage());
            DefaultResponse<?> response = DefaultResponse.builder()
                    .success(false)
                    .message(e.getMessage())
                    .build();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    /**
     * Eliminar usuario
     * DELETE /api/v1/users/{id}
     */
    @Operation(summary = "Eliminar usuario", description = "Elimina un usuario por su ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Usuario eliminado",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = DefaultResponseUserResponseDTO.class))),
                    @ApiResponse(responseCode = "404", description = "No encontrado")
            })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable UUID id) {
        log.info("DELETE /api/v1/users/{} - Eliminando usuario", id);
        try {
            userService.deleteUser(id);
            DefaultResponse<?> response = DefaultResponse.builder()
                    .success(true)
                    .message("Usuario eliminado exitosamente")
                    .build();
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            log.error("Error al eliminar usuario: {}", e.getMessage());
            DefaultResponse<?> response = DefaultResponse.builder()
                    .success(false)
                    .message(e.getMessage())
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    /**
     * Verificar si existe un usuario por email
     * GET /api/v1/users/exists/email/{email}
     */
    @Operation(summary = "Verificar existencia por email", description = "Verifica si existe un usuario con el email dado",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = DefaultResponseBooleanDTO.class)))
            })
    @GetMapping("/exists/email/{email}")
    public ResponseEntity<?> existsByEmail(@PathVariable String email) {
        log.info("GET /api/v1/users/exists/email/{} - Verificando si existe email", email);
        boolean exists = userService.existsByEmail(email);
        DefaultResponse<?> response = DefaultResponse.builder()
                .success(true)
                .message("Verificación completada")
                .data(exists)
                .build();
        return ResponseEntity.ok(response);
    }

    /**
     * Verificar si existe un usuario por nombre de usuario
     * GET /api/v1/users/exists/username/{userName}
     */
    @Operation(summary = "Verificar existencia por nombre de usuario", description = "Verifica si existe un usuario con el nombre dado",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = DefaultResponseBooleanDTO.class)))
            })
    @GetMapping("/exists/username/{userName}")
    public ResponseEntity<?> existsByUserName(@PathVariable String userName) {
        log.info("GET /api/v1/users/exists/username/{} - Verificando si existe nombre", userName);
        boolean exists = userService.existsByUserName(userName);
        DefaultResponse<?> response = DefaultResponse.builder()
                .success(true)
                .message("Verificación completada")
                .data(exists)
                .build();
        return ResponseEntity.ok(response);
    }
}
