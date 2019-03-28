package com.total.login.domain.login;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by kiseokhong on 2019. 3. 10..
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Login {
    @Id
    @GeneratedValue
    Long memberNumber;

    @Column(length = 50)
    String memberName;

    @Column(length = 10)
    String socialSectionCode;

    @Column(length = 50)
    String socialName;

    @Column(length = 200)
    String socialEmail;

    @Column(length = 100)
    String socialUniqueId;

    @Builder
    public Login(
            String memberName
            ,String socialSectionCode
            ,String socialName
            ,String socialEmail
            ,String socialUniqueId
    ) {
        this.memberName = memberName;
        this.socialSectionCode = socialSectionCode;
        this.socialName = socialName;
        this.socialEmail = socialEmail;
        this.socialUniqueId = socialUniqueId;
    }
}
