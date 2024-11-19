package com.io.ziblox.CinePass.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "snacks")
public class Snack extends Base{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column
    private String description;

    @Column(nullable = false)
    private double price;

    @OneToMany(mappedBy = "snack", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<SnackImage> snackImages;
}
