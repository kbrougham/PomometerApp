package com.pomometer.PomometerApp;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PomometerFinishActivity extends Activity {
	String notes = "";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pomometer_finish);
		
		Bundle extras = getIntent().getExtras();
		
		final String sent_goal = extras.getString("goal");
		
		final Long sent_duration = extras.getLong("elapsed_duration"); //in ms
		double decimal_duration = sent_duration/1000; //converts to seconds
		decimal_duration /= 60; //converts to minutes
		final int calculated_duration = (int)Math.ceil(decimal_duration); //round up to nearest minute
		final String started_at = extras.getString("started_at");
		final String ended_at = extras.getString("ended_at");
		final int task_id = extras.getInt("task_id");
		
		//set title to Complete: goal.  No strings.xml entry as this is dynamic
		((TextView) findViewById(R.id.finish_title)).setText("Complete: " + sent_goal);
		
		Button confirm_button = (Button) findViewById(R.id.final_submit_button);
		confirm_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				//json to commit to webserver
				JSONObject result = new JSONObject();
				
				try {
					result.put("id", 99);
					result.put("goal", sent_goal);
					result.put("notes", notes);
					result.put("duration", calculated_duration);
					result.put("started_at", started_at);
					result.put("ended_at", ended_at);
					result.put("task_id", task_id);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				new Write().execute(result);
				
				/*
				 * sent_goal
				 * (EditText) findViewById(R.id.notes_edit_text)
				 * calculated_duation
				 * started_at
				 * ended_at
				 * task_id
				 */
				
        		//Toast.makeText(getBaseContext(), ((Integer)calculated_duration).toString(), Toast.LENGTH_SHORT).show();

				Intent i = new Intent(getApplicationContext(), ResultListActivity.class);
				i.putExtra("task_id", task_id); 
				i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        		startActivity(i);
			}			
		});
	}
	
	public class Write extends AsyncTask<JSONObject, Integer, Boolean>{

		@Override
		protected Boolean doInBackground(JSONObject... params) {
			// TODO Auto-generated method stub
			
			DefaultHttpClient client = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost("http://pomometer.herokuapp.com/results.json");
			
			try {
				StringEntity JSONResult = new StringEntity(params[0].toString());
				httpPost.setEntity(JSONResult);
				httpPost.setHeader("Content-Type","application/json");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			HttpResponse response = null;
			
			try {
		        response = client.execute(httpPost);
		    } catch (ClientProtocolException e) {
		        e.printStackTrace();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }	
			
			return true;
		}
		
	}
}
