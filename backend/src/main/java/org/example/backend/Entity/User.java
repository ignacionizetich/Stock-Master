package org.example.backend.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="usuarios")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "lastname")
    private String lastname;


    @Column(name = "email", unique = true)
    @Email(message = "El formato debe ser de tipo email")
    private String email;


    @Column(name = "password")
    @NotNull
    private String password;


    @Column(name = "role")
    private String role;

}
