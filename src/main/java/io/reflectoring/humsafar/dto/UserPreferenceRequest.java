package io.reflectoring.humsafar.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserPreferenceRequest {
    private Long appUserId;
    private Long preferredMaritalStatusTypeId;
    private Long preferredCasteTypeId;
    private Long preferredEducationTypeId;
    private Long preferredOccupationTypeId;
    private Long preferredEmployedInId;
    private String preferredAgeFrom;
    private String preferredAgeTo;
    private String preferredHeightFrom;
    private String preferredHeightTo;
    private String preferredMotherTongue;
    private String preferredAnnualIncome;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
