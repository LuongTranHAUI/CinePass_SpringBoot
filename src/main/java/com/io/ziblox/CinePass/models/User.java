package com.io.ziblox.CinePass.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User extends Base{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String password;

    @Column(unique = true)
    private String phone;

    @Column(nullable = false)
    private boolean status;

    @Column(name = "facebook_account_id")
    private Long facebookAccountId;

    @Column(name = "google_account_id")
    private Long googleAccountId;

    @OneToMany(mappedBy = "user")
    private Set<MovieReview> movieReviews; // Add relationship for MovieReviews

    @OneToMany(mappedBy = "user")
    private Set<Notification> notifications;

    @OneToMany(mappedBy = "user")
    private Set<Order> orders;  // Relationship for Orders

    @OneToMany(mappedBy = "user") // Add for tickets
    private Set<Ticket> tickets;

    @OneToMany(mappedBy = "user")
    private Set<SocialAccount> socialAccounts;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Token> tokens;

    @ManyToMany
    @JoinTable(
            name = "discount_applications",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "discount_id")
    )
    private Set<Discount> discounts;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}
