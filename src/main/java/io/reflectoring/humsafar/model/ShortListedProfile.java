package io.reflectoring.humsafar.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
public class ShortListedProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "short_listed_user_id")
    private AppUser shortListedUser;
    //auth user
    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser user;


}
