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
@Table(name = "theaters")
public class Theater extends Base{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String name;

    @Column
    private String phone;

    @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL)
    private Set<Hall> halls;

    @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL) // For the bidirectional relationship
    private Set<Showtime> showtimes;
}
