package com.pomometer.PomometerApp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

public class DurationAndBreak extends Activity{
	
	public static final String USER_PREFERENCES = "MyPreferences"; 
	
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.duration_and_break_length);
    }

    /**
     * Callback method defined by the View
     * @param v
     */
    public void finishDialog(View v) {
    	// Do something in response to button
    	EditText durationEditText = (EditText) findViewById(R.id.duration_length);
    	String durationLength = durationEditText.getText().toString();
    	
    	SharedPreferences settings = getSharedPreferences(USER_PREFERENCES, 0);
    	SharedPreferences.Editor editor = settings.edit();
    	editor.putString("duration", durationLength);
    	editor.commit();
    	
        DurationAndBreak.this.finish();
    }
}
