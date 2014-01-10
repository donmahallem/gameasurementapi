package com.mokeu.ga;

public class GATracker{
	private String mEventPath="";
	private boolean mAppTracking;
	private static Settings mSettings=new Settings();
	static public class Settings{
		public String userLanguage=GAnalytics.getSettings().userLanguage;
		public String userAgent=GAnalytics.getSettings().userAgent;
		public String clientId=GAnalytics.getSettings().clientId;
		public String trackerId="";
		public boolean javaEnabled=GAnalytics.getSettings().javaEnabled;
		public String screenResolution=GAnalytics.getSettings().screenResolution;
		public boolean anonymizeIp=GAnalytics.getSettings().anonymizeIp;
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
		mAppTracking=appTracking;
		mSettings=new Settings();
		mSettings.trackerId=trackerId;
		
	}
	public String getEventPath() {
		// TODO Auto-generated method stub
		return mEventPath;
	}
	public boolean getAppTracking(){
		return mAppTracking;
	}
	/**
	 * Equal to {@ling #Settings.trackerId}
	 * @return
	 */
	public String getTrackerId(){
		return mSettings.trackerId;
	}
	public Settings getSettings(){
		return mSettings;
	}
	public void trackPageView(String locationUrl,String title){
		mEventPath=locationUrl;
		GAnalytics.queue(GAQueryBuilder.trackPageView(this,locationUrl,title));
	}
	public void trackPageView(String hostName,String filePath,String title){
		mEventPath=filePath;
		GAnalytics.queue(GAQueryBuilder.trackPageView(this,hostName, filePath, title));
	}
	public void trackAppView(String title){
		mEventPath=title;
		GAnalytics.queue(GAQueryBuilder.trackAppView(this, title));
	}
	public void trackEvent(String category,String action,String label,int value){
		GAnalytics.queue(GAQueryBuilder.trackEvent(this,category,action,label,value));
	}
}