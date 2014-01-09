package com.mokeu.ga;

import java.util.HashMap;
import java.util.Map;

public class GARequest implements Runnable{
	private Map<String,String> mParameter=new HashMap<String,String>();
	public GARequest(){
		
	}
	public void addParameter(String name,String value){
		mParameter.put(name, value);
	}
	@Override
	public void run() {
		System.out.println("===================START");
		for(String a:mParameter.keySet()){
			System.out.println(a+" => "+mParameter.get(a));
		}
		System.out.println("===================STOP");
	}
}