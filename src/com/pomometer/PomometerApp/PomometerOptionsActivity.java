package com.pomometer.PomometerApp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

public class PomometerOptionsActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pomometer_options);
		
		final int MIN_DURATION_IN_MINUTES = 1;
		final int MAX_DURATION_IN_MINUTES = 60;
		
		NumberPicker duration_picker = (NumberPicker) findViewById(R.id.duration_picker);
		duration_picker.setMaxValue(MAX_DURATION_IN_MINUTES);
		duration_picker.setMinValue(MIN_DURATION_IN_MINUTES);
			
		Button start_button = (Button) findViewById(R.id.start_button);
		start_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	//if the edit box where the goal is entered is blank, then tell them to enter something, otherwise, load next screen
            	if (((EditText) findViewById(R.id.goal_entry)).getText().toString().length() < 1)
            	{
            		Toast.makeText(getBaseContext(), "You must enter a goal!", Toast.LENGTH_SHORT).show();
            	}
            	else
            	{
            		Intent i = new Intent(getApplicationContext(), PomometerTimerActivity.class);
            		startActivity(i);
            	}
            }});
		
		// retrieve the textview reference to change text colour
		TextView npTextView = (TextView) duration_picker.getChildAt(1); // since indexing begins at 0
		npTextView.setTextColor(getResources().getColor(R.color.white_text));
	}
}
