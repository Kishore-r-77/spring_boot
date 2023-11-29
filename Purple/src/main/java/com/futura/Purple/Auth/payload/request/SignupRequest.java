package com.futura.Purple.Auth.payload.request;


import javax.persistence.Lob;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;
 
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;
    
    @Lob
	private String profile;
	
	private String verificationCode;

    private Long studentId;
    
    private Long teacherId;

    private Long userGroupId;
    
    private Long statusId;
    
    private Long companyId;
}
