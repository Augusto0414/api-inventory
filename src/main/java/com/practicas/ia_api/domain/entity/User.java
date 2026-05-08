package com.practicas.ia_api.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.hibernate.annotations.*;

import java.util.UUID;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(generator = "UUID")
    @UuidGenerator
    @Column(name = "id", updatable = false, nullable = false)

    private UUID id;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Email
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false )
    private String password;

}
