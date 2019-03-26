package com.total.login.common.social;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.total.login.dto.social.SocialResponseDto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

@Component
public class FaceBookConnection {
//	public static final String FB_APP_ID = "204945739708685";
//	public static final String FB_APP_SECRET = "28f9cc4c6d1bed375a5f4d47d7bd3227";
//	public static final String REDIRECT_URI = "http://localhost:8080/apis/member/socials/facebook/login";
//
//	public static final String FB_APP_ID = "1706921822910188";
//	public static final String FB_APP_SECRET = "f9bb418e4d75850d441f7ae9f9276129";
//	public static final String REDIRECT_URI = "http://uppp.oneplat.co/apis/member/socials/facebook/login";

//	public String getFBAuthUrl() {
//		String fbLoginUrl = "";
//		try {
//			fbLoginUrl = "http://www.facebook.com/dialog/oauth?" + "client_id="
//					+ FB_APP_ID + "&redirect_uri="
//					+ URLEncoder.encode(FB_REDIRECT_URI, "UTF-8")
//					+ "&scope=email";
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//		return fbLoginUrl;
//	}
	private static final Logger log = LoggerFactory.getLogger(FaceBookConnection.class);

	@Value("${social.facebook.appId}")
	private String FB_APP_ID;
	@Value("${social.facebook.appSecret}")
	private String FB_APP_SECRET;
	@Value("${social.facebook.redirectUrl}")
	private String FB_REDIRECT_URI;
	@Value("${social.facebook.mobile.redirectUrl}")
	private String FB_REDIRECT_MOBILE_URI;

	private String FB_URL = "https://graph.facebook.com/oauth/access_token";

	public String getAuthUrl(){
		return FB_URL+"?client_id="+FB_APP_ID + "&redirect_uri="+FB_REDIRECT_URI+"&scope=email";
	}

	public String getFBGraphUrl(String code) {
		String fbGraphUrl = "";
		try {
			fbGraphUrl = "https://graph.facebook.com/oauth/access_token?"
					+ "client_id=" + FB_APP_ID + "&redirect_uri="
					+ URLEncoder.encode(FB_REDIRECT_URI, "UTF-8")
					+ "&client_secret=" + FB_APP_SECRET + "&code=" + code;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return fbGraphUrl;
	}

	public String getAccessToken(String code) {
		String accessToken = "";
		if ("".equals(accessToken)) {
			URL fbGraphURL;
			try {
				fbGraphURL = new URL(getFBGraphUrl(code));
			} catch (MalformedURLException e) {
				e.printStackTrace();
				throw new RuntimeException("Invalid code received " + e);
			}
			URLConnection fbConnection;
			StringBuffer b = null;
			try {
				fbConnection = fbGraphURL.openConnection();
				log.debug("fbConnection : "+fbConnection);
				BufferedReader in;
				in = new BufferedReader(new InputStreamReader(
						fbConnection.getInputStream()));
				String inputLine;
				b = new StringBuffer();
				while ((inputLine = in.readLine()) != null)
					b.append(inputLine + "\n");
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException("Unable to connect with Facebook "
						+ e);
			}

			accessToken = b.toString();
			if (accessToken.startsWith("{")) {
				throw new RuntimeException("ERROR: Access Token Invalid: "
						+ accessToken);
			}
		}
		return accessToken;
	}

	public String getFBGraph(String accessToken) {
		String graph = null;
		try {
			String g = "https://graph.facebook.com/me?fields=id,name,email,locale,gender,birthday&" + accessToken;
			// "&fields=id,name,email,locale,gender,birthday"
			// &fields=id%2Cname%2Cemail%2Clocale%2Cgender%2Cbirthday&format=json&method=get
			URL u = new URL(g);
			URLConnection c = u.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					c.getInputStream()));
			String inputLine;
			StringBuffer b = new StringBuffer();
			while ((inputLine = in.readLine()) != null)
				b.append(inputLine + "\n");
			in.close();
			graph = b.toString();
			System.out.println(graph);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("ERROR in getting FB graph data. " + e);
		}
		return graph;
	}

	public SocialFacebook facebookProfileConvert(String data){
		ObjectMapper mapper = new ObjectMapper();
		SocialFacebook socialFacebook = new SocialFacebook();
		try {
			socialFacebook = mapper.readValue(data, SocialFacebook.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return socialFacebook;
	}
}
