package io.reflectoring.humsafar.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SuccessStory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "bride_user_id")
    private AppUser brideUser;
    @ManyToOne
    @JoinColumn(name="groom_user_id")
    private  AppUser groomUser;
    private String storyTitle;
    private  String storyDescription;
    private String photoUrl;
    private String videoUrl;
    private  String location;
    private String approved;
    private OffsetDateTime marrageDate;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;

}
