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
	private Map<String,String> mParameter=new HashMap<String,String>();
	public GARequest(){
		
	}
	public void addParameter(String name,String value){
		mParameter.put(name, value);
	}
	@Override
	public void run() {
		URIBuilder builder=new URIBuilder();
		builder.setHost(GAnalytics.ENDPOINT_HOST);
		builder.setPath(GAnalytics.ENDPOINT_PATH);
		builder.setScheme(GAnalytics.ENDPOINT_SCHEME);
		for(String a:mParameter.keySet()){
			builder.setParameter(a, mParameter.get(a));
		}
		System.out.println("===================START");
		URI uri=null;
		try {
			uri = builder.build();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		HttpGet post=new HttpGet(uri);
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