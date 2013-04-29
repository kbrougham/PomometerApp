package com.pomometer.PomometerApp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.SystemClock;
import android.os.Vibrator;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Chronometer.OnChronometerTickListener;

public class PomometerTimerActivity extends Activity {

	final long TIME_TO_VIBRATE = 2000;
	public static final String USER_PREFERENCES = "MyPreferences";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pomometer_timer);
		
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		 
		final Bundle extras = getIntent().getExtras();
		
		SharedPreferences settings = getSharedPreferences(USER_PREFERENCES, 0);
		final String alarm_type = settings.getString("alert_type", "Silent");
		final int vibration_length = Integer.parseInt(settings.getString("vibration_length", "1"));
		
		final Long sent_duration = (long) (extras.getInt("duration")*60*1000);
		
		final MediaPlayer myMediaPlayer = MediaPlayer.create(getBaseContext(), Settings.System.DEFAULT_RINGTONE_URI);
		
		//display entered duration?
		//display rounding warning?
		
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
            	
            	myMediaPlayer.stop();
            	Intent i = new Intent(getApplicationContext(), PomometerFinishActivity.class);
            	i.putExtra("elapsed_duration", (SystemClock.elapsedRealtime() - pomo_timer.getBase())); //ms elapsed
        		i.putExtra("task_id", extras.getString("task_id"));
        		i.putExtra("goal", extras.getString("goal"));
        		startActivity(i);
            }
           });
		
		Chronometer pomo_timer = (Chronometer) findViewById(R.id.pomo_timer);
		//pomo_timer.setFormat("MM:SS");

		pomo_timer.setBase(extras.getLong("start_time"));
		pomo_timer.start();
		
		
		pomo_timer.setOnChronometerTickListener(new OnChronometerTickListener() {
			@Override
			public void onChronometerTick(Chronometer current_chronometer) {
				//these are handled in milliseconds, so I need to convert duration to ms to compare
				if (SystemClock.elapsedRealtime() - current_chronometer.getBase() > sent_duration)
				{
					
					((Chronometer) findViewById(R.id.pomo_timer)).stop();
					if (alarm_type.equals("Vibrate")){
						//vibration
						((Vibrator)getSystemService(VIBRATOR_SERVICE)).vibrate(vibration_length*1000); //is time in ms
					}else if (alarm_type.equals("Ring+Vibrate")){
						
						//vibration and ring
						
						myMediaPlayer.start();
						((Vibrator)getSystemService(VIBRATOR_SERVICE)).vibrate(vibration_length*1000); //is time in ms
						/*
						Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
						Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
						r.play();
						*/
					}else if (alarm_type.equals("Ring")){
						myMediaPlayer.start();
					}
					//((Vibrator)getSystemService(VIBRATOR_SERVICE)).vibrate(TIME_TO_VIBRATE); //is time in ms
					
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
				startActivity(new Intent(this, GlobalOptionsActivity.class));
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
}
