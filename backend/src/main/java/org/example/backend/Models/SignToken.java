package org.example.backend.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "sign_tokens")
public class SignToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    private User user;

    @Column(name = "sign_token", unique = true)
    private String token;

    @Column(name = "type")
    private String type;

    @Column(name = "issued_at")
    private String issuedAt;

    @Column(name = "expires_at")
    private String expiresAt;

    @Column(name = "used")
    private boolean used;

    @Column(name = "used_at")
    private String usedAt;

}
