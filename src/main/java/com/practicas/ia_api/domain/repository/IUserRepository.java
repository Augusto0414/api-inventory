package com.practicas.ia_api.domain.repository;

import com.practicas.ia_api.domain.entity.User;

import java.util.Optional;

public interface IUserRepository {
        boolean existsByEmail(String email);
        boolean existsByUsername(String username);
        User save(User user);
        Optional<User> findByEmail(String email);
        Optional<User> findById(String idUser);

}
