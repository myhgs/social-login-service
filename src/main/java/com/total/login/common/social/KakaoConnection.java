package com.total.login.common.social;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.total.login.dto.social.SocialResponseDto.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Component
public class KakaoConnection {
//	public static final String CLIENT_ID = "742d5a8a06d1a3c01c25b9c33aa7023d";
//	public static final String REDIRECT_URI = "http://localhost:8080/apis/member/socials/kakao/login";
//
//	public static final String PDT_CLIENT_ID = "d71988dffdd471bf6db57856743dcd3e";
//	public static final String PDT_REDIRECT_URI = "http://uppp.oneplat.co/apis/member/socials/kakao/login";

	@Value("${social.kakao.clientId}")
	private String KA_CLIENT_ID;
	@Value("${social.kakao.redirectUrl}")
	private String KA_REDIRECT_URI;
	@Value("${social.kakao.mobile.redirectUrl}")
	private String KA_REDIRECT_MOBILE_URI;

	public String getKakaoAuthUrl() {
		String kakaoLoginUrl = "";
		try {
			kakaoLoginUrl = "https://kauth.kakao.com/oauth/authorize?client_id="+KA_CLIENT_ID+
					"&redirect_uri="+URLEncoder.encode(KA_REDIRECT_URI, "UTF-8")+"&response_type=code";
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return kakaoLoginUrl;
	}

	public String getKakaoAccesstoken(String codeParam){
		RestTemplate restTemplate = new RestTemplate();
		MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<String, String>();
		multiValueMap.add("grant_type", "authorization_code");
		multiValueMap.add("client_id", KA_CLIENT_ID);
		multiValueMap.add("redirect_uri", KA_REDIRECT_URI);
		multiValueMap.add("code", codeParam);
		String result = restTemplate.postForObject("https://kauth.kakao.com/oauth/token", multiValueMap, String.class);
		return result;
	}

	public SocialKakao getKakaoProfile(String accessToken){
		ConvertUtil convertUtil = new ConvertUtil();

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer "+accessToken);
		HttpEntity<String> httpEntity = new HttpEntity<String>(headers);

		String result = restTemplate.postForObject("https://kapi.kakao.com/v1/user/me", httpEntity, String.class);

		return kakaoProfileConvert(result);
	}

	public SocialKakao kakaoProfileConvert(String data){
		ObjectMapper mapper = new ObjectMapper();
		SocialKakao socialKakao = new SocialKakao();
		try {
			socialKakao = mapper.readValue(data, SocialKakao.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return socialKakao;
	}
}
