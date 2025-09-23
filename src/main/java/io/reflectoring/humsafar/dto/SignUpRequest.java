package io.reflectoring.humsafar.dto;

import lombok.Data;
@Data
public class SignUpRequest {
    private Long profileForId;
    private String email;
    private  String isSubAdmin;


}