package com.practicas.ia_api.infrastructure.controller;

import com.practicas.ia_api.application.dto.DefaultResponse;
import com.practicas.ia_api.application.dto.user.CreateUserDTO;
import com.practicas.ia_api.application.dto.user.UpdateUserDTO;
import com.practicas.ia_api.application.dto.user.UserResponseDTO;
import com.practicas.ia_api.application.service.interfaces.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final IUserService userService;

    /**
     * Crear un nuevo usuario
     * POST /api/v1/users
     */
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
