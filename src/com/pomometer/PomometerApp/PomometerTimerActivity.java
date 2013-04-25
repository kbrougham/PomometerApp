package com.pomometer.PomometerApp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.os.Vibrator;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.NumberPicker;
import android.widget.Chronometer.OnChronometerTickListener;

public class PomometerTimerActivity extends Activity {
	
	final long start_time = SystemClock.elapsedRealtime();
	final long TIME_TO_VIBRATE = 2000;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pomometer_timer);
		
		final Bundle extras = getIntent().getExtras();
		
		final int sent_duration = extras.getInt("duration");
		
		Button cancel_button = (Button) findViewById(R.id.cancel_button);
		Button confirm_button = (Button) findViewById(R.id.confirm_button);
		
		cancel_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	finish();
            }
           });
		confirm_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Chronometer pomo_timer = (Chronometer) findViewById(R.id.pomo_timer);
            	
            	Intent i = new Intent(getApplicationContext(), PomometerFinishActivity.class);
            	i.putExtra("elapsed_duration", (SystemClock.elapsedRealtime() - pomo_timer.getBase())); //ms elapsed
        		i.putExtra("task_id", extras.getInt("task_id"));
        		i.putExtra("goal", extras.getString("goal"));
        		i.putExtra("started_at", extras.getString("formattedStartDate"));
        		i.putExtra("ended_at", extras.getString("formattedEndDate"));
        		startActivity(i);
            }
           });
		
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
	
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.project_list, menu);
	    return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
	    //respond to menu item selection
		switch (item.getItemId()) {
			case R.id.durationAndBreakLength:
				startActivity(new Intent(this, DurationAndBreak.class));
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
}
