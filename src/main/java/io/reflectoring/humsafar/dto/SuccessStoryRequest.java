package io.reflectoring.humsafar.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.OffsetDateTime;
@Data
@Getter
@Setter
public class SuccessStoryRequest {
    private  String approved;
    private  String location;
    private  String videoUrl;
    private  String photoUrl;
    private  String storyDescription;
    private  String storyTitle;
    private OffsetDateTime marrageDate;
    private Long groomUserId;
    private Long brideUserId;
   ;
}
