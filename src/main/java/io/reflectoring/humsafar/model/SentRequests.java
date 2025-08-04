package io.reflectoring.humsafar.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
@Entity
@Getter
@Setter
public class SentRequests {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "from_user_id")
    private AppUser fromUser;
    @ManyToOne
    @JoinColumn(name = "to_user_id")
    private AppUser toUser;

    private Status status;
    private LocalDateTime createdAt;


    enum Status {
        PENDING,
        ACCEPTED,
        REJECTED
    }


}
