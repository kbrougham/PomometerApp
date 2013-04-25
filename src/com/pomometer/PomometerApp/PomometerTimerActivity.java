package com.pomometer.PomometerApp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Chronometer;

public class PomometerTimerActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pomometer_timer);
		
		Chronometer pomo_timer = (Chronometer) findViewById(R.id.pomo_timer);
		//pomo_timer.setFormat("MM:SS");
		pomo_timer.start();
	}
}
