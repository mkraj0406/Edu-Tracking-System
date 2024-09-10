package com.jsp.ets.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OtpRequestDTO {
    private String email;
    private  int otp;
}
