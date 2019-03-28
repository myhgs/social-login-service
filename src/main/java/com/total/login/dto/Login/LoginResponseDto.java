package com.total.login.dto.Login;

import com.total.login.domain.login.Login;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Hong Gi Seok on 2016-07-02.
 */
@Getter
public class LoginResponseDto {

    private String memberName;
    private String socialSectionCode;
    private String socialName;
    private String socialEmail;
    private String socialUniqueId;

    public LoginResponseDto(Login login){
        memberName = login.getMemberName();
        socialSectionCode = login.getSocialSectionCode();
        socialName = login.getSocialName();
        socialEmail = login.getSocialEmail();
        socialUniqueId = login.getSocialUniqueId();
    }

}
