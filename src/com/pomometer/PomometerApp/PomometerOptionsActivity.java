package com.pomometer.PomometerApp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.NumberPicker;
import android.widget.TextView;

public class PomometerOptionsActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pomometer_options);
		
		final int MAX_DURATION_IN_MINUTES = 60;
		
		NumberPicker duration_picker = (NumberPicker) findViewById(R.id.duration_picker);
		duration_picker.setMaxValue(MAX_DURATION_IN_MINUTES);
		duration_picker.setMinValue(0);
		
		// retrieve the textview reference to change text colour
		TextView npTextView = (TextView) duration_picker.getChildAt(1); // since indexing begins at 0
		npTextView.setTextColor(getResources().getColor(R.color.white_text));
	}
}
