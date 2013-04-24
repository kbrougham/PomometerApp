package com.pomometer.PomometerApp;

import java.util.Date;
import java.util.Vector;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;

public class ResultListActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_layout);	

		//the project_id sent by the Task list
		String task_id = "";
		
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			task_id = extras.getString("id");
		}
		
		//change title to correspond to task
		TextView page_title = (TextView) findViewById(R.id.page_title);
		page_title.setText("Result List");
		
		final int MAX_LINES_RESULT_NAME = 2;
		
		//new table layout which will load the table that the data will be placed in
		TableLayout result_list_content = (TableLayout) findViewById(R.id.page_list_content);
		
		//Frame layout to add the START POMODORO button
		FrameLayout options_frame = (FrameLayout) findViewById(R.id.page_list_options_frame);
		//a separation line for distinction from table
		LinearLayout a_separator = new LinearLayout(this);
		a_separator.setBackgroundColor(getResources().getColor(R.color.red_background));
		a_separator.setMinimumHeight(1); //set a thin line separator
		options_frame.addView(a_separator);
		//button to add to bottom frame
		Button start_button = new Button(this);
		start_button.setText("Start Pomodoro");
		start_button.setTextColor(getResources().getColor(R.color.white_text));
		start_button.setBackgroundColor(getResources().getColor(R.color.red_foreground));
		start_button.setLayoutParams(new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		start_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
    	    	Intent i = new Intent(getApplicationContext(), PomometerOptionsActivity.class);
    	    	startActivity(i);
            }
        });
		options_frame.addView(start_button);
		
		//list of Tasks, to be retrieved through GSON
		Vector<Result> list_of_results = new Vector<Result>();
		
		//Ians GSON Code to get data here
		Date tempDate = new Date();
		
		list_of_results.add(new Result(1,"First Result"+task_id, tempDate, Integer.parseInt(task_id)));
		list_of_results.add(new Result(2,"Second Result"+task_id, tempDate, Integer.parseInt(task_id)));
		list_of_results.add(new Result(3,"Third Result"+task_id, tempDate, Integer.parseInt(task_id)));
		list_of_results.add(new Result(4,"Fourth Result"+task_id, tempDate, Integer.parseInt(task_id)));
		list_of_results.add(new Result(6,"Fifth Result"+task_id, tempDate, Integer.parseInt(task_id)));
		list_of_results.add(new Result(8,"Sixth Result"+task_id, tempDate, Integer.parseInt(task_id)));
		list_of_results.add(new Result(9,"Seventh Result"+task_id, tempDate, Integer.parseInt(task_id)));
		list_of_results.add(new Result(10,"Eighth Result"+task_id, tempDate, Integer.parseInt(task_id)));
		list_of_results.add(new Result(11,"Ninth Result"+task_id, tempDate, Integer.parseInt(task_id)));
		list_of_results.add(new Result(13,"Tenth Result"+task_id, tempDate, Integer.parseInt(task_id)));
		//end ian gson
		
		for(int i=0;i<list_of_results.size();i++)
		{
			//New row to be added to table
			TableRow a_row_to_add = new TableRow(this);
			//set background colour of the row
			a_row_to_add.setBackgroundColor(getResources().getColor(R.color.red_foreground));
			
			//Row contains three buttons
			//Task name button, will link to task page for this Task
			Button result_name = new Button(this);

			//set button attributes
			result_name.setText(list_of_results.get(i).getGoal());
			result_name.setMaxLines(MAX_LINES_RESULT_NAME);
			result_name.setTag("task_" + list_of_results.get(i).getId());
			result_name.setTextColor(getResources().getColor(R.color.white_text));
			result_name.setLayoutParams(new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1f));
						
			//add the buttons to the current row, then add the row to the table
			a_row_to_add.addView(result_name);
			result_list_content.addView(a_row_to_add);			
		}
	}
}
