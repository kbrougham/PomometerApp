package com.pomometer.PomometerApp;

import java.util.Vector;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;

public class TaskListActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_layout);	

		//the project_id sent by the Task list
		String project_id = "";
		
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			project_id = extras.getString("id");
		}
		
		//change title to correspond to task
		TextView page_title = (TextView) findViewById(R.id.page_title);
		page_title.setText("Task List");
		
		final int MAX_LINES_TASK_NAME = 2;
		
		//new table layout which will load the table that the data will be placed in
		TableLayout task_list_content = (TableLayout) findViewById(R.id.page_list_content);
		//list of Tasks, to be retrieved through GSON
		Vector<Task> list_of_tasks = new Vector<Task>();
		
		//Ians GSON Code to get data here
		list_of_tasks.add(new Task(1,"First Task"+project_id, "Description", 5, Integer.parseInt(project_id)));
		list_of_tasks.add(new Task(2,"Second Task", "Description", 5, Integer.parseInt(project_id)));
		list_of_tasks.add(new Task(3,"Third Task", "Description", 5, Integer.parseInt(project_id)));
		list_of_tasks.add(new Task(5,"Fourth Task", "Description", 5, Integer.parseInt(project_id)));
		list_of_tasks.add(new Task(7,"Fifth Task", "Description", 5, Integer.parseInt(project_id)));
		list_of_tasks.add(new Task(8,"Sixth Task", "Description", 5, Integer.parseInt(project_id)));
		list_of_tasks.add(new Task(9,"Seventh Task", "Description", 5, Integer.parseInt(project_id)));
		list_of_tasks.add(new Task(10,"Eighth Task", "Description", 5, Integer.parseInt(project_id)));
		list_of_tasks.add(new Task(12,"Ninth Task", "Description", 5, Integer.parseInt(project_id)));
		list_of_tasks.add(new Task(13,"Tenth Task", "Description", 5, Integer.parseInt(project_id)));
		//end ian gson
		
		for(int i=0;i<list_of_tasks.size();i++)
		{
			//New row to be added to table
			TableRow a_row_to_add = new TableRow(this);
			//set background colour of the row
			a_row_to_add.setBackgroundColor(getResources().getColor(R.color.red_foreground));
			
			//Row contains three buttons
			//Task name button, will link to task page for this Task
			Button task_name = new Button(this);

			//set button attributes
			task_name.setText(list_of_tasks.get(i).getName());
			task_name.setMaxLines(MAX_LINES_TASK_NAME);
			task_name.setTag("task_" + list_of_tasks.get(i).getId());
			task_name.setTextColor(getResources().getColor(R.color.white_text));
			task_name.setLayoutParams(new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1f));
			task_name.setOnClickListener(new View.OnClickListener() {
	             public void onClick(View v) {
	            	//index to substring from
	     	    	int index_to_substring_at = v.getTag().toString().indexOf("_");
	     	    	String id_to_send = v.getTag().toString().substring(index_to_substring_at+1);
	     	    	
	     	    	//intent to load new activity
	     	    	Intent i = new Intent(getApplicationContext(), ResultListActivity.class);
	     	    	i.putExtra("id",id_to_send);
	     	    	startActivity(i);
	             }
	         });
			
			//add the buttons to the current row, then add the row to the table
			a_row_to_add.addView(task_name);	
			task_list_content.addView(a_row_to_add);			
		}
	}
}
