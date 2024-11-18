package com.io.ziblox.CinePass.models;

import com.io.ziblox.CinePass.enums.SeatStatus;
import com.io.ziblox.CinePass.enums.SeatType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "seats")
public class Seat extends Base{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "seat_row", nullable = false)
    private String seatRow;

    @Column(name = "seat_number", nullable = false)
    private int seatNumber;

    @ManyToOne(optional = false)
    @JoinColumn(name = "hall_id", nullable = false)
    private Hall hall;

    @Enumerated(EnumType.STRING)
    @Column(name = "seat_type")
    private SeatType seatType;

    @Enumerated(EnumType.STRING)  // Crucial for enums!
    @Column(name = "seat_status")
    private SeatStatus seatStatus;

    @OneToMany(mappedBy = "seat") // Add the relationship to tickets
    private Set<Ticket> tickets;
}
