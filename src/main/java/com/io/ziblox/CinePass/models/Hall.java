package com.io.ziblox.CinePass.models;

import com.io.ziblox.CinePass.enums.HallStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "halls")
public class Hall extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(name = "seat_capacity", nullable = false)
    private short seatCapacity;  // Use short since seatCapacity is SMALLINT

    @Enumerated(EnumType.STRING)
    @Column
    private HallStatus status;  // Use a dedicated enum for hall status

    @ManyToOne(optional = false)
    @JoinColumn(name = "theater_id", nullable = false)
    private Theater theater;

    @OneToMany(mappedBy = "hall", cascade = CascadeType.ALL)
    private Set<Seat> seats;

    @OneToMany(mappedBy = "hall", cascade = CascadeType.ALL)
    private Set<Showtime> showtimes;
}
