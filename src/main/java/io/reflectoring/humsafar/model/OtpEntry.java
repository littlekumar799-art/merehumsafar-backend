package io.reflectoring.humsafar.model;


import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OtpEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String otp;
   private OffsetDateTime createdAt;
//    @Column(name = "created_at", columnDefinition = "TIMESTAMP(6) WITHOUT TIME ZONE")
//    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;




    public boolean isExpired() {
        return createdAt.plusMinutes(5).isBefore(OffsetDateTime.now());
    }
}