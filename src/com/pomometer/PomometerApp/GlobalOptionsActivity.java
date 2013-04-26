package com.pomometer.PomometerApp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class GlobalOptionsActivity extends Activity{
	
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_global_options);
        
        Spinner spinner = (Spinner) findViewById(R.id.alert_type_spinner);
	     // Create an ArrayAdapter using the string array and a default spinner layout
	     ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
	             R.array.alert_types, android.R.layout.simple_spinner_item);
	     // Specify the layout to use when the list of choices appears
	     adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	     // Apply the adapter to the spinner
	     spinner.setAdapter(adapter);
    }

    /**
     * Callback method defined by the View
     * @param v
     */
    public void finishDialog(View v) {
    	// Do something in response to button
    	Intent intent = new Intent(this, TaskListActivity.class);
    	EditText durationEditText = (EditText) findViewById(R.id.duration_length);
    	EditText breakEditText = (EditText) findViewById(R.id.alert_length);
    	Spinner spinner = (Spinner) findViewById(R.id.alert_type_spinner);
    	
    	String durationLength = durationEditText.getText().toString();
    	String breakLength = breakEditText.getText().toString();
    	String alertType = spinner.getSelectedItem().toString();
    	
    	intent.putExtra("duration_length", durationLength);
    	intent.putExtra("alert_length", breakLength);
    	intent.putExtra("alert_type", alertType);
    	
        GlobalOptionsActivity.this.finish();
    }
}
