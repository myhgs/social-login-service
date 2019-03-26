package com.total.login.web;

import com.total.login.service.social.SocialService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by kiseokhong on 2019. 3. 10..
 */
@Controller
@AllArgsConstructor
public class LoginController {

    private SocialService socialService;

    @GetMapping("/login")
    public String test(){
        return "loginTest";
    }

    @GetMapping("/socials/{socialType}/login")
    public String getSocialResult(
            @PathVariable(value = "socialType") String socialType
            , HttpServletRequest request, Model model
    ){
        model.addAttribute("data", socialService.socialProfileInfo(socialType, request));
        return "loginResult";
    }

}
