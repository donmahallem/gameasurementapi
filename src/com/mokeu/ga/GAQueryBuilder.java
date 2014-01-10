package com.mokeu.ga;

public class GAQueryBuilder{
	private static GARequest getDefaultRequest(GATracker tracker){
		GARequest req=new GARequest();
		req.addParameter(GAParameters.VERSION, "1");
		req.addParameter(GAParameters.TRACKING_ID, tracker.getTrackerId());
		req.addParameter(GAParameters.CLIENT_ID, GAnalytics.getClientId());
		String data=GAnalytics.getSettings().screenResolution;
		if(GAnalytics.getSettings().screenResolution!=null)
			req.addParameter(GAParameters.SCREEN_RESOLUTION,data);
		data=GAnalytics.getSettings().userLanguage;
		if(GAnalytics.getSettings().userLanguage!=null)
			req.addParameter(GAParameters.USER_LANGUAGE, data);
		req.addParameter(GAParameters.JAVA_ENABLED,GAnalytics.getSettings().javaEnabled?"1":"0");
		if(GAnalytics.getSettings().anonymizeIp)
			req.addParameter(GAParameters.ANONYMIZE_IP, GAnalytics.getSettings().anonymizeIp?"1":"0");
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
		GARequest req=getDefaultRequest(tracker);
		req.addParameter(GAParameters.APP_VERSION, "1");
		req.addParameter(GAParameters.APP_NAME, "AppName");
		req.addParameter(GAParameters.HIT_TYPE, "appview");
		req.addParameter(GAParameters.DOCUMENT_TITLE,title);
		req.addParameter(GAParameters.CONTENT_DESCRIPTION,title);
		return req;
	}
	public static GARequest trackEvent(GATracker tracker,String category, String action,
			String label, int value) {
		GARequest req=getDefaultRequest(tracker);
		req.addParameter(GAParameters.HIT_TYPE, "event");
		req.addParameter(GAParameters.EVENT_CATEGORY, category);
		req.addParameter(GAParameters.EVENT_ACTION, action);
		req.addParameter(GAParameters.EVENT_LABEL, label);
		req.addParameter(GAParameters.EVENT_VALUE, ""+value);
		return req;
	}
}