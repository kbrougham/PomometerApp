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
import android.widget.TextView;
import android.widget.TableRow.LayoutParams;

public class ProjectListActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_layout);		
		
		final int MAX_LINES_PROJECT_NAME = 2;
		
		//new table layout which will load the table that the data will be placed in
		TableLayout project_list_content = (TableLayout) findViewById(R.id.page_list_content);
		
		//list of projects, to be retrieved through GSON
		Vector<Project> list_of_projects = new Vector<Project>();
		list_of_projects = populate(); 
		
		if (list_of_projects.size() == 0){
			TableRow a_row_to_add = new TableRow(this);
			a_row_to_add.setBackgroundColor(getResources().getColor(R.color.red_foreground));
			TextView notice = new TextView(this);
			notice.setText("There are no projects at this time.");
			notice.setMaxLines(MAX_LINES_PROJECT_NAME);
			notice.setTextColor(getResources().getColor(R.color.white_text));
			notice.setTextSize(20);
			notice.setLayoutParams(new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0.66f));
			a_row_to_add.addView(notice);
			project_list_content.addView(a_row_to_add);
		}else{
			for(int i=0;i<list_of_projects.size();i++)
			{
				//New row to be added to table
				TableRow a_row_to_add = new TableRow(this);
				//set background colour of the row
				a_row_to_add.setBackgroundColor(getResources().getColor(R.color.red_foreground));
				
				//Row contains three buttons
				//Project name button, will link to task page for this project
				Button project_name = new Button(this);

				//set button attributes
				project_name.setText(list_of_projects.get(i).getName());
				project_name.setMaxLines(MAX_LINES_PROJECT_NAME);
				project_name.setTag("project_" + list_of_projects.get(i).getId());
				project_name.setTextColor(getResources().getColor(R.color.white_text));
				project_name.setLayoutParams(new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1f));
				project_name.setOnClickListener(new View.OnClickListener() {
		             public void onClick(View v) {
		            	//index to substring from
		     	    	int index_to_substring_at = v.getTag().toString().indexOf("_");
		     	    	//we substring from +1 because we want everything AFTER the _
		     	    	String id_to_send = v.getTag().toString().substring(index_to_substring_at+1);
		     	    	
		     	    	//intent to load new activity
		     	    	Intent i = new Intent(getApplicationContext(), TaskListActivity.class);
		     	    	i.putExtra("id",id_to_send);
		     	    	startActivity(i);
		             }
		         });
				
				//add the buttons to the current row, then add the row to the table
				a_row_to_add.addView(project_name);
				project_list_content.addView(a_row_to_add);			
			}
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
				startActivity(new Intent(this, DurationAndBreak.class));
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
	
	private Vector<Project> populate() {
	    Vector<Project> projects = new Vector<Project>();
	    JSONObject obj = null;
	    
	        String jsonUrl = "http://pomometer.herokuapp.com/projects.json";
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
					ja = obj.getJSONArray("projects");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	for (int i = 0; i < ja.length(); i++){
	        		JSONObject jo = null;
					try {
						jo = (JSONObject) ja.get(i);
						String name = jo.getString("name");
						int id = Integer.valueOf(jo.getString("id"));
						Project tempProject = new Project(id, name);
						projects.add(tempProject);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        	}
	        
        return projects;
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
		}
		
	}

	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.project_list, menu);
		return true;
	}*/
}
