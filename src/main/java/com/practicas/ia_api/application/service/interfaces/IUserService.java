package com.practicas.ia_api.application.service.interfaces;

import com.practicas.ia_api.application.dto.user.CreateUserDTO;
import com.practicas.ia_api.application.dto.user.UpdateUserDTO;
import com.practicas.ia_api.application.dto.user.UserResponseDTO;

import java.util.List;
import java.util.UUID;

public interface IUserService {
    
    /**
     * Crear un nuevo usuario
     * @param createUserDTO DTO con los datos del usuario a crear
     * @return UserResponseDTO con los datos del usuario creado
     */
    UserResponseDTO createUser(CreateUserDTO createUserDTO);
    
    /**
     * Obtener usuario por ID
     * @param id UUID del usuario
     * @return UserResponseDTO con los datos del usuario
     */
    UserResponseDTO getUserById(UUID id);
    
    /**
     * Obtener usuario por email
     * @param email email del usuario
     * @return UserResponseDTO con los datos del usuario
     */
    UserResponseDTO getUserByEmail(String email);
    
    /**
     * Obtener usuario por nombre de usuario
     * @param userName nombre de usuario
     * @return UserResponseDTO con los datos del usuario
     */
    UserResponseDTO getUserByUserName(String userName);
    
    /**
     * Obtener todos los usuarios
     * @return Lista de UserResponseDTO
     */
    List<UserResponseDTO> getAllUsers();
    
    /**
     * Actualizar usuario
     * @param id UUID del usuario
     * @param updateUserDTO DTO con los datos a actualizar
     * @return UserResponseDTO con los datos del usuario actualizado
     */
    UserResponseDTO updateUser(UUID id, UpdateUserDTO updateUserDTO);
    
    /**
     * Eliminar usuario
     * @param id UUID del usuario
     */
    void deleteUser(UUID id);
    
    /**
     * Verificar si existe un usuario por email
     * @param email email del usuario
     * @return true si existe, false en caso contrario
     */
    boolean existsByEmail(String email);
    
    /**
     * Verificar si existe un usuario por nombre de usuario
     * @param userName nombre de usuario
     * @return true si existe, false en caso contrario
     */
    boolean existsByUserName(String userName);
}
