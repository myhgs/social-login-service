package com.total.login.service.social;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.total.login.common.social.*;
import com.total.login.dto.Common;
import com.total.login.dto.social.SocialResponseDto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Member service.
 */
@Service
public class SocialServiceImpl implements SocialService {

//	private FaceBookConnection fbConnection = new FaceBookConnection();
//	private NaverConnection naverConnection = new NaverConnection();
//	private KakaoConnection kakaoConnection = new KakaoConnection();
	private ConvertUtil convertUtil = new ConvertUtil();

	@Autowired
	FaceBookConnection fbConnection;
	@Autowired
	NaverConnection naverConnection;
	@Autowired
	KakaoConnection kakaoConnection;

	private static final Logger log = LoggerFactory.getLogger(SocialServiceImpl.class);

//	@Value("#{config['social.facebook.appId']}")
//	private String FB_APP_ID;
//	@Value("#{config['social.facebook.appSecret']}")
//	private String FB_APP_SECRET;
//	@Value("#{config['social.facebook.redirectUrl']}")
//	private String FB_REDIRECT_URI;
//	@Value("#{config['social.facebook.mobile.redirectUrl']}")
//	private String FB_REDIRECT_MOBILE_URI;
//
//	@Value("#{config['social.kakao.clientId']}")
//	private String KA_CLIENT_ID;
//	@Value("#{config['social.kakao.redirectUrl']}")
//	private String KA_REDIRECT_URI;
//	@Value("#{config['social.kakao.mobile.redirectUrl']}")
//	private String KA_REDIRECT_MOBILE_URI;
//
//	@Value("#{config['social.naver.clientId']}")
//	private String NA_CLIENT_ID;
//	@Value("#{config['social.naver.clientSecret']}")
//	private String NA_CLIENT_SECRET;
//	@Value("#{config['social.naver.redirectUrl']}")
//	private String NA_REDIRECT_URI;
//	@Value("#{config['social.naver.mobile.redirectUrl']}")
//	private String NA_REDIRECT_MOBILE_URI;
//
//	@Value("#{config['database.name']}")
//	private String databaseName;

	@Override
	public Map<String, Object> socialAuthLoginUrl() {
		Map<String, Object> result = new HashMap<>();
		result.put(SocialConstants.FACEBOOK, fbConnection.getAuthUrl());
		result.put(SocialConstants.NAVER, naverConnection.getNaverAuthUrl());
		result.put(SocialConstants.KAKAO, kakaoConnection.getKakaoAuthUrl());
		return result;
	}

	@Override
	public String socialProfileInfo(String socialType, HttpServletRequest httpServletRequest) {
		Map<String, Object> result = new HashMap<>();
 		if (socialType.equals(SocialConstants.FACEBOOK)){
			result.put("data", faceBookResult(httpServletRequest));
		}else if(socialType.equals(SocialConstants.NAVER)){
			result.put("data", naverResult(httpServletRequest));
		}else if(socialType.equals(SocialConstants.KAKAO)){
			result.put("data", kakaoResult(httpServletRequest));
		}else{
 			return null;
		}
		return objectToString(result);
	}

	//Object To String
	public String objectToString(Object object){
		ObjectMapper mapper = new ObjectMapper();
		String result = "";
		try {
			result = mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return result;
	}

	//페이스북 소셜 결과
	public SocialFacebook faceBookResult(HttpServletRequest httpServletRequest){
		String code = httpServletRequest.getParameter("code");
		if(code == null){
			//취소버튼일시
			Common common = new Common();
			common.setStatus("401");
			common.setMessage("소셜 서비스를 동의해야 이용하실 수 있습니다.");
//			return common;

			return null;
		}else{
//			String redirectUrl = FB_REDIRECT_URI;
//			if(isMobile){
//				redirectUrl = FB_REDIRECT_MOBILE_URI;
//			}
			String accessToken = fbConnection.getAccessToken(code);

			SocialFacebook socialFacebook  = fbConnection.facebookProfileConvert(fbConnection.getFBGraph(accessToken));
			log.debug("Facebook ok====================================");
//			MemberSocialResponse memberSocialResponse = new MemberSocialResponse();
//			memberSocialResponse.setSocialUniqueId(socialFacebook.getId());
//			memberSocialResponse.setSocialSectionCode("BM_SOCIAL_SECTION_01");
//			memberSocialResponse.setSocialEmail(socialFacebook.getEmail());
//			memberSocialResponse.setSocialNumber(memberMapper.selectSocialNumber(socialName));
//			memberSocialResponse.setMemberName("facebook_"+socialFacebook.getId());
//			if(socialFacebook.getName() != null && !"".equals(socialFacebook.getName()))
//				memberSocialResponse.setMemberName(socialFacebook.getName());
//
//			return getMemberSocial(memberSocialResponse, httpServletRequest);

			return socialFacebook;
		}
	}

	//네이버 소셜 결과
	public SocialNaver naverResult(HttpServletRequest httpServletRequest){
		Map<String, String> accessData = new HashMap<String, String>();

		String code = httpServletRequest.getParameter("code");
		String state = httpServletRequest.getParameter("state");

		String accessToken = naverConnection.getNaverAccesstoken(code, state);
		if(!accessToken.equals("error")){
			accessData = convertUtil.getConvertData(accessToken);
		}

		SocialNaver socialNaver = naverConnection.getNaverProfile(accessData.get("access_token"));
		if(socialNaver == null){
			//취소버튼일시
			Common common = new Common();
			common.setStatus("401");
			common.setMessage("소셜 서비스를 동의해야 이용하실 수 있습니다.");

			return null;
		}else{
			String id = socialNaver.getResponse().getId();
			String email =socialNaver.getResponse().getEmail();

			log.debug("Naver ok====================================");

//			MemberSocialResponse memberSocialResponse = new MemberSocialResponse();
//			memberSocialResponse.setSocialUniqueId(socialNaver.getResponse().getId());
//			memberSocialResponse.setSocialEmail(socialNaver.getResponse().getEmail());
//			memberSocialResponse.setSocialSectionCode("BM_SOCIAL_SECTION_02");
//			memberSocialResponse.setSocialNumber(memberMapper.selectSocialNumber(socialName));
//			memberSocialResponse.setMemberName("naver_"+socialNaver.getResponse().getId());
//			if(socialNaver.getResponse().getName() != null && !"".equals(socialNaver.getResponse().getName()))
//				memberSocialResponse.setMemberName(socialNaver.getResponse().getName());
//
//			return getMemberSocial(memberSocialResponse, httpServletRequest);

			return socialNaver;
		}
	}

	//카카오 소셜 결과
	public SocialKakao kakaoResult(HttpServletRequest httpServletRequest){
 		Map<String, String> accessData = new HashMap<String, String>();
		String code = httpServletRequest.getParameter("code");
		if(code == null){
			//취소버튼일시
			Common common = new Common();
			common.setStatus("401");
			common.setMessage("소셜 서비스를 동의해야 이용하실 수 있습니다.");

			return null;
		}else{

			String accessToken = kakaoConnection.getKakaoAccesstoken(code);
			if(!accessToken.equals("error")){
				accessData = convertUtil.getConvertData(accessToken);
			}

			SocialKakao profile = kakaoConnection.getKakaoProfile(accessData.get("access_token"));

			log.debug("kakao ok====================================");
//			MemberSocialResponse memberSocialResponse = new MemberSocialResponse();
//			memberSocialResponse.setSocialUniqueId(profile.getId());
//			memberSocialResponse.setSocialSectionCode("BM_SOCIAL_SECTION_03");
//			memberSocialResponse.setSocialNumber(memberMapper.selectSocialNumber(socialName));
//			memberSocialResponse.setMemberName("kakao_"+profile.getId());
//			if(profile.getProperties() != null){
//				if(profile.getProperties().getNickname() != null && !"".equals(profile.getProperties().getNickname()))
//					memberSocialResponse.setMemberName(profile.getProperties().getNickname());
//			}
//			return getMemberSocial(memberSocialResponse, httpServletRequest);
			return profile;
		}
	}

}
