package com.pomometer.PomometerApp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.pomometer.PomometerApp.R;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.LightingColorFilter;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;

public class ProjectListActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_project_list);		
		
		int MAX_LINES_PROJECT_NAME = 2;
		
		//new table layout which will load the table that the data will be placed in
		TableLayout project_list_content = (TableLayout) findViewById(R.id.project_list_content);
		//list of projects, to be retrieved through GSON
		Vector<Project> list_of_projects = new Vector<Project>();
		list_of_projects = populate(); 
		
		//Ians GSON Code to get data here
		/*
		list_of_projects.add(new Project(1,"Fir\nst P\nroject"));
		list_of_projects.add(new Project(2,"Second Project"));
		list_of_projects.add(new Project(3,"Third Project"));
		list_of_projects.add(new Project(5,"Fourth Project"));
		list_of_projects.add(new Project(7,"Fifth Project"));
		list_of_projects.add(new Project(8,"Sixth Project"));
		list_of_projects.add(new Project(9,"Seventh Project"));
		list_of_projects.add(new Project(10,"Eighth Project"));
		list_of_projects.add(new Project(12,"Ninth Project"));
		list_of_projects.add(new Project(13,"Tenth Project"));
		*/
		//end ian gson
		
		for(int i=0;i<list_of_projects.size();i++)
		{
			//New row to be added to table
			TableRow a_row_to_add = new TableRow(this);
			//set background colour of the row
			a_row_to_add.setBackgroundColor(getResources().getColor(R.color.red_foreground));
			
			//Row contains three buttons
			//Project name button, will link to task page for this project
			Button project_name = new Button(this);
			//project_name.setOnClickListener(onClick(project_name));
			//edit and delete buttons to edit or delete the project
			Button edit_button = new Button(this);
			//edit_button.setOnClickListener(onClick(edit_button));
			
			Button delete_button = new Button(this);
			//delete_button.setOnClickListener(onClick(delete_button));

			//set button attributes
			project_name.setText(list_of_projects.get(i).getName());
			project_name.setMaxLines(MAX_LINES_PROJECT_NAME);
			project_name.setTag("project_" + list_of_projects.get(i).getId());
			project_name.setTextColor(getResources().getColor(R.color.white_text));
			project_name.setLayoutParams(new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0.66f));
			
			edit_button.setText("Edit");
			edit_button.setTag("edit_" + list_of_projects.get(i).getId());
			edit_button.setTextColor(getResources().getColor(R.color.white_text));
			edit_button.setLayoutParams(new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0.22f));
			
			delete_button.setText("Delete");
			delete_button.setTag("delete_" + list_of_projects.get(i).getId());
			delete_button.setTextColor(getResources().getColor(R.color.white_text));
			delete_button.setLayoutParams(new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0.22f));
			
			//add the buttons to the current row, then add the row to the table
			a_row_to_add.addView(project_name);
			a_row_to_add.addView(edit_button);
			a_row_to_add.addView(delete_button);	
			project_list_content.addView(a_row_to_add);			
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

	public OnClickListener onClick(Button a_button) {
	    // TODO Auto-generated method stub
	    if(a_button.getTag().toString().contains("project"))
	    {
	    	//index to substring from
	    	int index_to_substring_at = a_button.getTag().toString().indexOf("_");
	    	String id_to_send = a_button.getTag().toString().substring(index_to_substring_at-1);
	    	
	    	//intent to load new activity
	    	Intent i = new Intent(getApplicationContext(), TaskListActivity.class);
	    	i.putExtra("id",id_to_send);
	    	startActivity(i);
	    }
	    else if(a_button.getTag().toString().contains("edit"))
	    {
	    	//handle edit press
	    }
	    else if (a_button.getTag().toString().contains("delete"))
	    {
	    	//handle delete press
	    }
		return null;
	}
}
