package com.pomometer.PomometerApp;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class GlobalOptionsActivity extends Activity{
	
	public static final String USER_PREFERENCES = "MyPreferences";
	
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
	     EditText durationEditText = (EditText) findViewById(R.id.duration_length);
	     EditText vibrationLengthEditText = (EditText) findViewById(R.id.alert_length);
	     
	     SharedPreferences settings = getSharedPreferences(USER_PREFERENCES, 0);
	     String duration_length = settings.getString("duration", "");
	     durationEditText.setText(duration_length);
	     String vibration_length = settings.getString("vibration_length", "");
	     vibrationLengthEditText.setText(vibration_length);
	     spinner.setSelection(settings.getInt("alert_type_position", 0));
    }

    /**
     * Callback method defined by the View
     * @param v
     */
    public void finishDialog(View v) {
    	// Do something in response to button
    	EditText durationEditText = (EditText) findViewById(R.id.duration_length);
    	EditText alarmLengthEditText = (EditText) findViewById(R.id.alert_length);
    	Spinner spinner = (Spinner) findViewById(R.id.alert_type_spinner);
    	
    	String durationLength = durationEditText.getText().toString();
    	String alarmLength = alarmLengthEditText.getText().toString();
    	String alertType = spinner.getSelectedItem().toString();
    	int alertTypePosition = spinner.getSelectedItemPosition();
    	
    	SharedPreferences settings = getSharedPreferences(USER_PREFERENCES, 0);
    	SharedPreferences.Editor editor = settings.edit();
    	if (durationLength != ""){
    		editor.putString("duration", durationLength);
    	}else{
    		editor.putString("duration", "1");
    	}
    	if (alarmLength != ""){
    		editor.putString("vibration_length", alarmLength);
    	}else{
    		editor.putString("vibration_length", "0");
    	}
    	editor.putString("alert_type", alertType);
    	editor.putInt("alert_type_position", alertTypePosition);
    	editor.commit();
    	
        GlobalOptionsActivity.this.finish();
    }
}
