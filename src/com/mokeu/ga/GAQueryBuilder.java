package com.mokeu.ga;

public class GAQueryBuilder{
	private static GARequest getDefaultRequest(GATracker tracker){
		GARequest req=new GARequest();
		req.addParameter(GAParameters.VERSION, "1");
		req.addParameter(GAParameters.TRACKING_ID, tracker.getTrackerId());
		req.addParameter(GAParameters.CLIENT_ID, GAnalytics.getClientId());
		String data=GAnalytics.getScreenResolution();
		if(data!="")
			req.addParameter(GAParameters.SCREEN_RESOLUTION,data);
		data=GAnalytics.getUserLanguage();
		if(data!="")
			req.addParameter(GAParameters.USER_LANGUAGE, data);
		req.addParameter(GAParameters.JAVA_ENABLED,GAnalytics.getJavaEnabled());
		return req;
	}
	public static GARequest trackPageView(GATracker tracker,String hostName,String filePath,String title){
		GARequest req=getDefaultRequest(tracker);
		req.addParameter(GAParameters.HIT_TYPE, "pageview");
		req.addParameter(GAParameters.DOCUMENT_HOST_NAME,hostName);
		req.addParameter(GAParameters.DOCUMENT_PATH,filePath);
		req.addParameter(GAParameters.DOCUMENT_TITLE,title);
		return req;
	}
	public static GARequest trackAppView(GATracker tracker,String title){
		GARequest req=new GARequest();
		req.addParameter(GAParameters.VERSION, "1");
		req.addParameter(GAParameters.TRACKING_ID, tracker.getTrackerId());
		req.addParameter(GAParameters.CLIENT_ID, GAnalytics.getClientId());
		req.addParameter(GAParameters.HIT_TYPE, "appview");
		req.addParameter(GAParameters.DOCUMENT_TITLE,title);
		req.addParameter(GAParameters.CONTENT_DESCRIPTION,title);
		return req;
	}
}