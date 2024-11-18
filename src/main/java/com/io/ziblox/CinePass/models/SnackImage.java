package com.io.ziblox.CinePass.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "snack_images")
public class SnackImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "image_url")
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY) // Use LAZY fetching for better performance
    @JoinColumn(name = "snack_id", nullable = false)
    private Snack snack;
}
