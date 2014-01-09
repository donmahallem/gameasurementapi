package com.mokeu.ga;

public class GATracker{
	private String mTrackerId;
	public GATracker(String trackerId){
		mTrackerId=trackerId;  
		
		
		
	}
	public String getTrackerId(){
		return mTrackerId;
	}
	public void trackPageView(String hostName,String filePath,String title){
		GAnalytics.queue(GAQueryBuilder.trackPageView(this,hostName, filePath, title));
	}
	public void trackAppView(String title){
		GAnalytics.queue(GAQueryBuilder.trackAppView(this, title));
	}
}