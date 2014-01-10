package com.mokeu.ga;

public class GAQueryBuilder{
	private static GARequest getDefaultRequest(GATracker tracker){
		GARequest req=new GARequest();
		req.setBrowserID(tracker.getSettings().userAgent);
		req.addParameter(GAParameters.VERSION, "1");
		req.addParameter(GAParameters.TRACKING_ID, tracker.getTrackerId());
		req.addParameter(GAParameters.CLIENT_ID, tracker.getSettings().clientId);
		String data=tracker.getSettings().screenResolution;
		if(tracker.getSettings().screenResolution!=null)
			req.addParameter(GAParameters.SCREEN_RESOLUTION,data);
		data=tracker.getSettings().userLanguage;
		if(tracker.getSettings().userLanguage!=null)
			req.addParameter(GAParameters.USER_LANGUAGE, data);
		req.addParameter(GAParameters.JAVA_ENABLED,tracker.getSettings().javaEnabled?"1":"0");
		if(tracker.getSettings().anonymizeIp)
			req.addParameter(GAParameters.ANONYMIZE_IP, "1");
		return req;
	}
	public static GARequest trackPageView(GATracker tracker,String hostName,String filePath,String title){
		if(tracker.getAppTracking()){
			try {
				throw new Exception("Tracker must be not setup as App Tracker");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		GARequest req=getDefaultRequest(tracker);
		req.addParameter(GAParameters.HIT_TYPE, "pageview");
		req.addParameter(GAParameters.DOCUMENT_HOST_NAME,hostName);
		req.addParameter(GAParameters.DOCUMENT_PATH,filePath);
		req.addParameter(GAParameters.DOCUMENT_TITLE,title);
		return req;
	}
	public static GARequest trackAppView(GATracker tracker,String title){
		if(!tracker.getAppTracking()){
			try {
				throw new Exception("Tracker must be setup as App Tracker");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		GARequest req=getDefaultRequest(tracker);
		req.addParameter(GAParameters.APP_VERSION, "1");
		req.addParameter(GAParameters.APP_NAME, tracker.getSettings().appName);
		req.addParameter(GAParameters.HIT_TYPE, "appview");
		req.addParameter(GAParameters.DOCUMENT_TITLE,title);
		req.addParameter(GAParameters.CONTENT_DESCRIPTION,title);
		return req;
	}
	public static GARequest trackEvent(GATracker tracker,String category, String action,
			String label, int value) {
		GARequest req=getDefaultRequest(tracker);
		if(tracker.getAppTracking()){
			req.addParameter(GAParameters.APP_NAME,tracker.getSettings().appName);
		}
		if(tracker.getEventPath()!=""){
			req.addParameter(GAParameters.DOCUMENT_PATH, tracker.getEventPath());
		}
		req.addParameter(GAParameters.HIT_TYPE, "event");
		req.addParameter(GAParameters.EVENT_CATEGORY, category);
		req.addParameter(GAParameters.EVENT_ACTION, action);
		req.addParameter(GAParameters.EVENT_LABEL, label);
		req.addParameter(GAParameters.EVENT_VALUE, ""+value);
		return req;
	}
	public static GARequest trackPageView(GATracker tracker,
			String locationUrl, String title) {
		GARequest req=getDefaultRequest(tracker);
		req.addParameter(GAParameters.HIT_TYPE, "pageview");
		req.addParameter(GAParameters.DOCUMENT_LOCATION_URL, locationUrl);
		req.addParameter(GAParameters.DOCUMENT_TITLE, title);
		return req;
	}
	/**
	 * Tracks the User Timing
	 * @param tracker Tracker
	 * @param category Category (required)
	 * @param timing Time in ms (required)
	 * @param variableName Time Variable Name (optional can be null)
	 * @param userTimingLabel Time Label (optional can be null)
	 * @return
	 */
	public static GARequest trackUserTiming(GATracker tracker,
			String category, long timing, String variableName,
			String userTimingLabel) {
		GARequest req=getDefaultRequest(tracker);
		req.addParameter(GAParameters.HIT_TYPE,"timing");
		req.addParameter(GAParameters.USER_TIMING_CATEGORY,category);
		req.addParameter(GAParameters.USER_TIMING_TIME, ""+timing);
		if(variableName!=null)
			req.addParameter(GAParameters.USER_TIMING_VARIABLE_NAME, variableName);
		if(userTimingLabel!=null)
			req.addParameter(GAParameters.USER_TIMING_LABEL, userTimingLabel);
		return req;
	}
	/**
	 * Sends an empty Event-Type with the Session Start Command
	 * @return
	 */
	public static GARequest startSession(GATracker tracker) {
		GARequest req=getDefaultRequest(tracker);
		req.addParameter(GAParameters.HIT_TYPE, "event");
		req.addParameter(GAParameters.SESSION_CONTROL, "start");
		return req;
	}
	public static GARequest stopSession(GATracker tracker) {
		GARequest req=getDefaultRequest(tracker);
		req.addParameter(GAParameters.HIT_TYPE, "event");
		req.addParameter(GAParameters.SESSION_CONTROL, "stop");
		return req;
	}
}