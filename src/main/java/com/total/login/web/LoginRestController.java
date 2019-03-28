package com.total.login.web;

import com.total.login.dto.Login.LoginRequestDto;
import com.total.login.service.login.LoginService;
import com.total.login.service.social.SocialService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by kiseokhong on 2019. 3. 10..
 */
@RestController
@AllArgsConstructor
public class LoginRestController {

    private SocialService socialService;

    private LoginService loginService;

    @GetMapping("/api/getAuthUrl")
    public Map<String, Object> getSocialUrl(){
        return socialService.socialAuthLoginUrl();
    }

    @PostMapping("/api/member")
    public Object saveMember(@RequestBody LoginRequestDto loginRequestDto){
        return loginService.save(loginRequestDto);
    }
}
