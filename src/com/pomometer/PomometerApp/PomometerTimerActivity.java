package com.pomometer.PomometerApp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
