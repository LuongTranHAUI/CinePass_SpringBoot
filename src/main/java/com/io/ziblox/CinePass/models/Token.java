package com.io.ziblox.CinePass.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tokens")
public class Token extends Base{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String token;

    @Column(name = "token_type", nullable = false)
    private String tokenType;

    @Column(name = "expiration_date")
    private LocalDateTime expirationDate;  // LocalDateTime for date and time

    @Column(nullable = false)
    private boolean revoked; // Changed to boolean

    @Column(nullable = false)
    private boolean expired; // Changed to boolean

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
