package com.io.ziblox.CinePass.models;

import com.io.ziblox.CinePass.enums.NotificationStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "notifications")
public class Notification extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Lob  // Recommended for TEXT fields
    @Column(nullable = false)
    private String message;

    @Enumerated(EnumType.STRING)
    @Column
    private NotificationStatus status;

    @Column
    private String type;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
