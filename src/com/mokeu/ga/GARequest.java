package com.mokeu.ga;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class GARequest implements Runnable{
	private long mQueryTime=System.currentTimeMillis();
	private static int mCacheBuster=0;
	private Map<String,String> mParameter=new HashMap<String,String>();
	private String mBrowser=GAnalytics.BROWSER+"/"+GAnalytics.VERSION;
	public GARequest(){
		
	}
	public void setBrowserID(String id){
		mBrowser=id;
	}
	public void addParameter(String name,String value){
		mParameter.put(name, value);
	}
	@Override
	public void run() {
		URIBuilder builder=new URIBuilder(GAnalytics.getMethod().getURI());
		for(String a:mParameter.keySet()){
			builder.setParameter(a, mParameter.get(a));
		}
		builder.setParameter(GAParameters.CACHE_BUSTER, ""+mCacheBuster);
		builder.setParameter(GAParameters.QUEUE_TIME, ""+(System.currentTimeMillis()-mQueryTime));
		mCacheBuster++;
		System.out.println("===================START");
		URI uri=null;
		try{
			uri = builder.build();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		HttpGet post=new HttpGet(uri);
		post.setHeader("User-Agent",mBrowser);
		System.out.println(uri.toString());
		CloseableHttpClient client=HttpClients.createDefault();
		CloseableHttpResponse resp=null;
		try {
			resp=client.execute(post);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("CODE: "+resp.getStatusLine().getStatusCode());
		System.out.println("PHRASE: "+resp.getStatusLine().getReasonPhrase());
		System.out.println("===================STOP");
		mParameter.clear();
		mParameter=null;
	}
}