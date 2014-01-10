package com.mokeu.ga;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Describing Connection Method Infos
 * @author Don
 *
 */
public enum GAMethod{
	POST_SSL("post","https://ssl.google-analytics.com/collect"),
	POST("post","http://www.google-analytics.com/collect"),
	GET("get","http://www.google-analytics.com/collect"),
	GET_SSL("get","https://ssl.google-analytics.com/collect");
	private String mMethod;
	private URI mUri;
	GAMethod(String method,String uri){
		mMethod=method;
		try {
			mUri=new URI(uri);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getMethod(){
		return mMethod;
	}
	public URI getURI(){
		return mUri;
	}
	@Override
	public String toString(){
		return mMethod+" => "+mUri;
	}
	
}