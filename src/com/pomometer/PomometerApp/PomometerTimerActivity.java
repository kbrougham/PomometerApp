package com.pomometer.PomometerApp;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.os.Vibrator;
import android.widget.Chronometer;
import android.widget.Chronometer.OnChronometerTickListener;

public class PomometerTimerActivity extends Activity {
	
	final long start_time = SystemClock.elapsedRealtime();
	final long TIME_TO_VIBRATE = 2000;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pomometer_timer);
		
		Bundle extras = getIntent().getExtras();
		
		final int sent_duration = extras.getInt("duration");
		
		Chronometer pomo_timer = (Chronometer) findViewById(R.id.pomo_timer);
		//pomo_timer.setFormat("MM:SS");
		pomo_timer.setBase(start_time);
		pomo_timer.start();
		
		pomo_timer.setOnChronometerTickListener(new OnChronometerTickListener() {
			@Override
			public void onChronometerTick(Chronometer current_chronometer) {
				//these are handled in milliseconds, so I need to convert duration to ms to compare
				if (SystemClock.elapsedRealtime() - current_chronometer.getBase() > sent_duration*60*1000)
				{
					((Chronometer) findViewById(R.id.pomo_timer)).stop();
					((Vibrator)getSystemService(VIBRATOR_SERVICE)).vibrate(TIME_TO_VIBRATE); //is time in ms
				}
				
        		//Toast.makeText(getBaseContext(), ((Long)(SystemClock.elapsedRealtime() - current_chronometer.getBase())).toString(), Toast.LENGTH_SHORT).show();
			}			
		});
	}
	
	protected void cancelPressed() {
		finish();
	}
	
	protected void completePressed() {
		
	}
}
