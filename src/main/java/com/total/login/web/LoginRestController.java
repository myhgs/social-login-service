package com.total.login.web;

import com.total.login.service.social.SocialService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kiseokhong on 2019. 3. 10..
 */
@RestController
@AllArgsConstructor
public class LoginRestController {

    private SocialService socialService;

    @GetMapping("/api/getAuthUrl")
    public Map<String, Object> getSocialUrl(){
        return socialService.socialAuthLoginUrl();
    }
}
