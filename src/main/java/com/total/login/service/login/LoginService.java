package com.total.login.service.login;

import com.total.login.dto.Login.LoginRequestDto;

public interface LoginService {

    Long save(LoginRequestDto loginRequestDto);

}
