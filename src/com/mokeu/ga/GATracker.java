package com.mokeu.ga;

import java.util.UUID;

public class GATracker{
	private String mTrackerId;
	private boolean mAppTracking;
	private static Settings mSettings=new Settings();
	static public class Settings{
		public String userLanguage;
		public String userAgent="github.gameasurementapi";
		public String clientId=UUID.randomUUID().toString();
		public String userEnabled=null;
		public boolean javaEnabled=true;
		public String screenResolution=null;
		public boolean anonymizeIp=GAnalytics.getAnonymizeIp();
		public String appName="AppName";
		public String appVersion="AppVersion";
		public String contentDescription="";
		public Settings(){
			
		}
	}
	public GATracker(String trackerId){
		this(trackerId,false);
	}
	public GATracker(String trackerId,boolean appTracking){
		mTrackerId=trackerId;  
		mAppTracking=appTracking;
		mSettings=new Settings();
		mSettings.userAgent=GAnalytics.BROWSER+"/"+GAnalytics.VERSION;
		
	}
	public boolean getAppTracking(){
		return mAppTracking;
	}
	public String getTrackerId(){
		return mTrackerId;
	}
	public Settings getSettings(){
		return mSettings;
	}
	public void trackPageView(String locationUrl,String title){
		mSettings.contentDescription=locationUrl;
		GAnalytics.queue(GAQueryBuilder.trackPageView(this,locationUrl,title));
	}
	public void trackPageView(String hostName,String filePath,String title){
		mSettings.contentDescription=hostName+filePath;
		GAnalytics.queue(GAQueryBuilder.trackPageView(this,hostName, filePath, title));
	}
	public void trackAppView(String title){
		mSettings.contentDescription=title;
		GAnalytics.queue(GAQueryBuilder.trackAppView(this, title));
	}
	public void trackEvent(String category,String action,String label,int value){
		GAnalytics.queue(GAQueryBuilder.trackEvent(this,category,action,label,value));
	}
}