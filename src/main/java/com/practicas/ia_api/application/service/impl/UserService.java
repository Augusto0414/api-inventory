package com.practicas.ia_api.application.service.impl;

import com.practicas.ia_api.application.dto.user.CreateUserDTO;
import com.practicas.ia_api.application.dto.user.UpdateUserDTO;
import com.practicas.ia_api.application.dto.user.UserResponseDTO;
import com.practicas.ia_api.application.service.interfaces.IUserService;
import com.practicas.ia_api.domain.entity.User;
import com.practicas.ia_api.domain.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UserService implements IUserService {

    private final IUserRepository userRepository;

    @Override
    public UserResponseDTO createUser(CreateUserDTO createUserDTO) {
        log.info("Creando nuevo usuario con email: {}", createUserDTO.getEmail());
        
        if (userRepository.existsByEmail(createUserDTO.getEmail())) {
            throw new IllegalArgumentException("El email ya está registrado");
        }
        
        if (userRepository.existsByUserName(createUserDTO.getUserName())) {
            throw new IllegalArgumentException("El nombre de usuario ya está registrado");
        }

        User user = User.builder()
                .userName(createUserDTO.getUserName())
                .email(createUserDTO.getEmail())
                .password(createUserDTO.getPassword())
                .firstName(createUserDTO.getFirstName())
                .lastName(createUserDTO.getLastName())
                .isActive(true)
                .build();

        User savedUser = userRepository.save(user);
        log.info("Usuario creado exitosamente con ID: {}", savedUser.getId());
        
        return mapToResponseDTO(savedUser);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponseDTO getUserById(UUID id) {
        log.info("Obteniendo usuario con ID: {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con ID: " + id));
        return mapToResponseDTO(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponseDTO getUserByEmail(String email) {
        log.info("Obteniendo usuario con email: {}", email);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con email: " + email));
        return mapToResponseDTO(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponseDTO getUserByUserName(String userName) {
        log.info("Obteniendo usuario con nombre: {}", userName);
        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con nombre: " + userName));
        return mapToResponseDTO(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponseDTO> getAllUsers() {
        log.info("Obteniendo todos los usuarios");
        return userRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDTO updateUser(UUID id, UpdateUserDTO updateUserDTO) {
        log.info("Actualizando usuario con ID: {}", id);
        
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con ID: " + id));

        if (updateUserDTO.getEmail() != null && !updateUserDTO.getEmail().equals(user.getEmail())) {
            if (userRepository.existsByEmail(updateUserDTO.getEmail())) {
                throw new IllegalArgumentException("El email ya está registrado");
            }
            user.setEmail(updateUserDTO.getEmail());
        }

        if (updateUserDTO.getPassword() != null) {
            user.setPassword(updateUserDTO.getPassword());
        }

        if (updateUserDTO.getFirstName() != null) {
            user.setFirstName(updateUserDTO.getFirstName());
        }

        if (updateUserDTO.getLastName() != null) {
            user.setLastName(updateUserDTO.getLastName());
        }

        if (updateUserDTO.getIsActive() != null) {
            user.setIsActive(updateUserDTO.getIsActive());
        }

        user.setUpdatedAt(LocalDateTime.now());
        User updatedUser = userRepository.save(user);
        log.info("Usuario actualizado exitosamente con ID: {}", id);
        
        return mapToResponseDTO(updatedUser);
    }

    @Override
    public void deleteUser(UUID id) {
        log.info("Eliminando usuario con ID: {}", id);
        
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("Usuario no encontrado con ID: " + id);
        }
        
        userRepository.deleteById(id);
        log.info("Usuario eliminado exitosamente con ID: {}", id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByUserName(String userName) {
        return userRepository.existsByUserName(userName);
    }

    private UserResponseDTO mapToResponseDTO(User user) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .userName(user.getUserName())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .isActive(user.getIsActive())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}
