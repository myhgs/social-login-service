package com.total.login.dto.social;

import lombok.Getter;

/**
 * Created by Hong Gi Seok on 2016-07-02.
 */
@Getter
public class SocialResponseDto {

    @Getter
    public static class SocialFacebook{
        String id;
        String email;
        String first_name;
        String gender;
        String last_name;
        String link;
        String locale;
        String name;
        String timezone;
        String updated_time;
        boolean verified;
    }

    @Getter
    public static class SocialKakao{
        String id;
        SocialKakaoProperties properties;
    }

    @Getter
    public static class SocialKakaoProperties{
        String nickname;
        String thumbnail_image;
        String profile_image;
    }

    @Getter
    public static class SocialNaver {
        String resultcode;
        String message;
        SocialNaverResponse response;
    }

    @Getter
    public static class SocialNaverResponse {
        String email;
        String nickname;
        String enc_id;
        String profile_image;
        String age;
        String gender;
        String id;
        String name;
        String birthday;
    }
}
