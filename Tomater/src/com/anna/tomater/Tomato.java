package com.anna.tomater;

import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;

import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.os.CountDownTimer; 

public class Tomato extends Activity implements OnClickListener {
	
	TextView tomatoTimer; 
	MyCount timerCount;
	int hours;
	int minutes;
	int seconds;
	Ringtone alarm;
	LinearLayout screen;
	boolean isNoticed = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tomato);
		initialize();
		  
	}
	
	private void initialize()
	{
		timerCount =  new MyCount(5  * 1000, 1000); 
		setTime(5);
		timerCount.start();
		tomatoTimer = (TextView) findViewById(R.id.TV_tomatoTimer);
		Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
    	alarm = RingtoneManager.getRingtone(getApplicationContext(), sound);
    	screen = (LinearLayout) findViewById(R.id.LL_screen);
    	screen.setOnClickListener(this);
    	
		
	}
	
	  private void setTime(long time) {
      	seconds = (int) (time % 60);
      	time -= seconds;
      	
      	minutes = (int) (time % 60);
      	time -= minutes;
      	
      	hours = (int) time;
      	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tomato, menu);
		return true;
	}

	
	  public class MyCount extends CountDownTimer {

	     public MyCount(long millisInFuture, long countDownInterval) {
	            super(millisInFuture, countDownInterval);
	   
	        }

	      
	        /*
	         * updates hours, minutes, seconds, by subtracting a second, returns string of new time
	         */
	        private String updateTime() {
	        	
	        	if(seconds > 0)
	        	{
	        		seconds--;
	        	}
	        	else if(minutes > 0)
	        	{
	        		minutes--;
	        		seconds = 59;
	        	}
	        	else if(hours > 0)
	        	{
	        		hours--;
	        		minutes = 59;
	        		seconds = 59;
	        	}
	        	
	        	return hours+":"+minutes+":"+seconds;
	        
	        }   

	        @Override
	        public void onFinish() {
	        	System.out.println("finish");
	        	tomatoTimer.setText("00:00:00");
	        	isNoticed = false;
	        //	while(!isNoticed)
	        //	{
	        		alarm.play();
	        //	}
	        	
	        	 
	        
	        }

	        @Override
	        public void onTick(long millisUntilFinished) {
	        	
	        	String  time = updateTime();
	        	tomatoTimer.setText(time);
	        	System.out.println("tick");
	        	


	        }
	    }


	@Override
	public void onClick(View arg0) {
		isNoticed = true;
	} 
	}

