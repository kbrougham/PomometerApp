package com.pomometer.PomometerApp;

import android.app.Activity;
import android.os.Bundle;

public class TaskListActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_project_list);	

		Bundle extras = getIntent().getExtras();
		if (extras != null) {
		    String value = extras.getString("new_variable_name");
		}
	}
}
