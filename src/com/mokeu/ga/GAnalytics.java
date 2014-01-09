package com.mokeu.ga;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GAnalytics{
	private static String mDefaultTrackerId="";
	private static String mClientId=UUID.randomUUID().toString();
	private static Map<String,GATracker> mRequests=new HashMap<String,GATracker>();
	private static ExecutorService mService = Executors.newFixedThreadPool(1);
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
}