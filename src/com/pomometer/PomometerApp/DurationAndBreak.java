package com.pomometer.PomometerApp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

public class DurationAndBreak extends Activity{
	
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
    	Intent intent = new Intent(this, TaskListActivity.class);
    	EditText durationEditText = (EditText) findViewById(R.id.duration_length);
    	EditText breakEditText = (EditText) findViewById(R.id.break_length);
    	String durationLength = durationEditText.getText().toString();
    	String breakLength = breakEditText.getText().toString();
    	intent.putExtra("duration_length", durationLength);
    	intent.putExtra("break_length", breakLength);
        DurationAndBreak.this.finish();
    }
}
