package com.io.ziblox.CinePass.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "discounts")
public class Discount extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String code;

    @Column
    private String description;

    @Column(name = "discount_percent", nullable = false)
    private Double discountPercent;

    @Column(name = "expiration_date", nullable = false)
    private LocalDate expirationDate;

    @JsonIgnore
    @ManyToMany(mappedBy = "discounts")
    private Set<User> users;

    @JsonIgnore
    @ManyToMany(mappedBy = "discounts")
    private Set<Ticket> tickets;
}
