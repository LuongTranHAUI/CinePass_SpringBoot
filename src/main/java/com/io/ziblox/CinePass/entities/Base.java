package com.io.ziblox.CinePass.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class Base {
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void createdAt() {
        createdAt = updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void updatedAt() {
        updatedAt = LocalDateTime.now();
    }
}
