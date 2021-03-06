package com.pomometer.PomometerApp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Vector;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;

public class TaskListActivity extends Activity {
	
	String project_id = "";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_layout);	
		
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			project_id = extras.getString("project_id");
		}
		
		//change title to correspond to task
		TextView page_title = (TextView) findViewById(R.id.page_title);
		page_title.setText(R.string.task_list_title);
		
		final int MAX_LINES_TASK_NAME = 2;
		
		//new table layout which will load the table that the data will be placed in
		TableLayout task_list_content = (TableLayout) findViewById(R.id.page_list_content);
		//list of Tasks, to be retrieved through GSON
		Vector<Task> list_of_tasks = new Vector<Task>();
		list_of_tasks = populate();		
		
		if (list_of_tasks.size() == 0){
			TableRow a_row_to_add = new TableRow(this);
			a_row_to_add.setBackgroundColor(getResources().getColor(R.color.red_foreground));
			
			TextView notice = new TextView(this);
			notice.setText(R.string.no_tasks);
			notice.setMaxLines(MAX_LINES_TASK_NAME);
			notice.setTextColor(getResources().getColor(R.color.white_text));
			notice.setTextSize(20);
			notice.setLayoutParams(new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0.66f));
			
			a_row_to_add.addView(notice);
			task_list_content.addView(a_row_to_add);
		}
		else{
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
				task_name.setTag("Task_" + list_of_tasks.get(i).getId());
				task_name.setTextColor(getResources().getColor(R.color.white_text));
				task_name.setLayoutParams(new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0.66f));
				task_name.setOnClickListener(new View.OnClickListener() {
		             public void onClick(View v) {
		            	//index to substring from
		     	    	int index_to_substring_at = v.getTag().toString().indexOf("_");
		     	    	String id_to_send = v.getTag().toString().substring(index_to_substring_at+1);
		     	    	
		     	    	//intent to load new activity
		     	    	Intent i = new Intent(getApplicationContext(), ResultListActivity.class);
		     	    	i.putExtra("task_id",id_to_send);
		     	    	startActivity(i);
		             }
		         });
				//add the buttons to the current row, then add the row to the table
				a_row_to_add.addView(task_name);
				task_list_content.addView(a_row_to_add);			
			}
		}
		
		
	}
	
	private Vector<Task> populate() {
	    Vector<Task> tasks = new Vector<Task>();
	    JSONObject obj = null;
	    
	        String jsonUrl = getResources().getString(R.string.json_task_list) + project_id + ".json";
	        
	        try {
				obj = new Read().execute(jsonUrl).get();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        	JSONArray ja = null;
				try {
					ja = obj.getJSONArray("tasks");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	for (int i = 0; i < ja.length(); i++){
	        		JSONObject jo = null;
					try {
						jo = (JSONObject) ja.get(i);
						
						String name = jo.getString("name");
						String description = jo.getString("description");
						int id = Integer.valueOf(jo.getString("id"));
						int effort = Integer.valueOf(jo.getString("effort"));
						int proj_id = Integer.valueOf(project_id);
						
						Task tempTask = new Task(id, name, description, effort, proj_id);
						tasks.add(tempTask);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        	}
	        
        return tasks;
    }
	
	public class Read extends AsyncTask<String, Integer, JSONObject>{

		@Override
		protected JSONObject doInBackground(String... params) {
			// TODO Auto-generated method stub
			URL jsonUrl = null;
			URLConnection transport = null;
			InputStreamReader isr = null;
			String line = null;
			JSONObject tempObj = null;
			
			
			try {
				jsonUrl = new URL(params[0]);
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			try {
				transport = jsonUrl.openConnection();
				isr = new InputStreamReader(transport.getInputStream());
				BufferedReader in = new BufferedReader(isr);
				line = in.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			try {
				tempObj = new JSONObject(line);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return tempObj;
			//set button attributes			
		}
		
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
				startActivity(new Intent(this, GlobalOptionsActivity.class));
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}

}
