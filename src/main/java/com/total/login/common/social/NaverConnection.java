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
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.SecureRandom;

@Component
public class NaverConnection {
//	public static final String CLIENT_ID = "41qs9uEtxBVDvzSFGrON";
//	public static final String CLIENT_SECRET = "aL1bb_d8QH";
//	public static final String REDIRECT_URI = "http://127.0.0.1:8080/apis/socials/naver/login";
//
//	public static final String PDT_CLIENT_ID = "orOwoJazfvEOwtY9dO7y";
//	public static final String PDT_CLIENT_SECRET = "xcOfOClQ1y";
//	public static final String PDT_REDIRECT_URI = "http://uppp.oneplat.co/apis/socials/naver/login";

	@Value("${social.naver.clientId}")
	private String NA_CLIENT_ID;
	@Value("${social.naver.clientSecret}")
	private String NA_CLIENT_SECRET;
	@Value("${social.naver.redirectUrl}")
	private String NA_REDIRECT_URI;

	private String state = "";

	public String generateState()
	{
		SecureRandom random = new SecureRandom();
		return new BigInteger(130, random).toString(32);
	}

	public String getNaverAuthUrl() {
		state = generateState();
		String naverLoginUrl = "";
		try {
			naverLoginUrl = "https://nid.naver.com/oauth2.0/authorize?response_type=code&"+
					"client_id="+NA_CLIENT_ID+
					"&redirect_uri="+URLEncoder.encode(NA_REDIRECT_URI, "UTF-8")+"&state="+state;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return naverLoginUrl;
	}

	public String getNaverAccesstoken(String codeParam, String stateParam){
		String result = "";
//		if(stateParam.equals(state)){
			RestTemplate restTemplate = new RestTemplate();
			MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<String, String>();
			multiValueMap.add("grant_type", "authorization_code");
			multiValueMap.add("client_id", NA_CLIENT_ID);
			multiValueMap.add("client_secret", NA_CLIENT_SECRET);
			multiValueMap.add("code", codeParam);
			multiValueMap.add("state", stateParam);
			result = restTemplate.postForObject("https://nid.naver.com/oauth2.0/token", multiValueMap, String.class);
//		}else{
//			result = "error";
//		}
		return result;
	}

	public SocialNaver getNaverProfile(String accessToken){
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer "+accessToken);
		HttpEntity<String> httpEntity = new HttpEntity<String>(headers);

		String result = null;
		if(accessToken != null){
			result = restTemplate.postForObject("https://openapi.naver.com/v1/nid/me", httpEntity, String.class);
		}
//		Map<String, String> responseData = new HashMap<String, String>();
//		responseData = naverProfileConvert(result);
		return naverProfileConvert(result);
	}

//	public Map<String, String> naverProfileConvert(String data){
//		ObjectMapper mapper = new ObjectMapper();
//		Map<String, String> result = new HashMap<String, String>();
//		try {
//			result = mapper.readValue(data, HashMap.class);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return result;
//	}

	public SocialNaver naverProfileConvert(String data){

		ObjectMapper mapper = new ObjectMapper();
		SocialNaver socialNaver = new SocialNaver();

		if(data == null){
			socialNaver = null;
		}else{
			try {
				socialNaver = mapper.readValue(data, SocialNaver.class);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return socialNaver;
	}

}
