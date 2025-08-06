package io.reflectoring.humsafar.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShortListedProfileRequest {

    private Long appUserId;
    private Long shortListedUserId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
