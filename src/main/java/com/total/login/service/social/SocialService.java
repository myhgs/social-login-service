package com.total.login.service.social;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface SocialService {

    Map<String, Object> socialAuthLoginUrl();

    String socialProfileInfo(String socialType, HttpServletRequest httpServletRequest);

}
