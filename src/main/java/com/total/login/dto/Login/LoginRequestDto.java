package com.total.login.dto.Login;

import com.total.login.domain.login.Login;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Hong Gi Seok on 2016-07-02.
 */
@Setter
@Getter
@NoArgsConstructor
public class LoginRequestDto {

    private String memberName;
    private String socialSectionCode;
    private String socialName;
    private String socialEmail;
    private String socialUniqueId;

    public Login toEntity(){
        return Login.builder()
                .memberName(memberName)
                .socialSectionCode(socialSectionCode)
                .socialName(socialName)
                .socialEmail(socialEmail)
                .socialUniqueId(socialUniqueId)
                .build();
    }

}
