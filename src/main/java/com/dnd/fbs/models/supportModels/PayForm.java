package com.dnd.fbs.models.supportModels;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@ToString
@Getter
@Setter
public class PayForm {
    private String fullname;
    private String email;
    private String phone;
    private String address;
    private String citizenIdentification;
}
