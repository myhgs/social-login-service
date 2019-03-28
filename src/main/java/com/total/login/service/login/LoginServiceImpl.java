package com.total.login.service.login;

import com.total.login.domain.login.LoginRepository;
import com.total.login.dto.Login.LoginRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * The type Member service.
 */
@Service
@AllArgsConstructor
public class LoginServiceImpl implements LoginService {

	LoginRepository loginRepository;

	@Override
	public Long save(LoginRequestDto loginRequestDto) {
		return loginRepository.save(loginRequestDto.toEntity()).getMemberNumber();
	}
}
