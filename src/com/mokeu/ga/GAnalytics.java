package com.mokeu.ga;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GAnalytics{
	public static final String ENDPOINT_PATH = "/collect",
			ENDPOINT_HOST="www.google-analytics.com",
			ENDPOINT_SCHEME="http";
	private static String mDefaultTrackerId="";
	private static String mClientId=UUID.randomUUID().toString();
	private static Map<String,GATracker> mRequests=new HashMap<String,GATracker>();
	private static ExecutorService mService = Executors.newFixedThreadPool(1);
	private static String mUserLanguage=null,mJavaEnabled="1",mScreenResolution=null;
	private GAnalytics(){
		
	}
	public static void setDefaultTrackerId(String defaultID){
		mDefaultTrackerId=defaultID;
	}
	public static void setClientId(String id){
		mClientId=id;
	}
	public static GATracker getTracker(){
		if(mDefaultTrackerId==""){
			try {
				throw new Exception("First call init() with a tracker id");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return getTracker(mDefaultTrackerId);
	}
	public static String getClientId(){
		return mClientId;
	}
	public static GATracker getTracker(String id){
		if(!mRequests.containsKey(id))
			mRequests.put(id,new GATracker(id));
		return mRequests.get(id);
	}
	public static void closeTracker(String id){
		if(mRequests.containsKey(id)){
			mRequests.remove(id);
		}
	}
	public static void queue(GARequest request) {
		mService.execute(request);
	}
	public static String getScreenResolution() {
		// TODO Auto-generated method stub
		return mScreenResolution;
	}
	public static String getJavaEnabled() {
		// TODO Auto-generated method stub
		return mJavaEnabled;
	}
	public static String getUserLanguage() {
		// TODO Auto-generated method stub
		return mUserLanguage;
	}
}