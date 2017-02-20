package com.example.mpandroidchart_demo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;

import android.app.Activity;
import android.app.Application;

public class MyApplication extends Application {
	private List<Activity> activityList=new LinkedList<Activity>();
	private static MyApplication instance;
	public MyApplication() {
		super();
		
	}
	public static MyApplication getInstance(){
		if(null==instance){
			instance=new MyApplication();
		}		
		return instance;		
	}
	public void addActivity(Activity activity){
		activityList.add(activity);
	}
	
	public void exit() {
		for(Activity activity:activityList){
			activity.finish();
		}
		System.exit(0);
	}

}
