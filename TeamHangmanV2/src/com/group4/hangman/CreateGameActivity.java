package com.group4.hangman;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mobdb.android.GetRowData;
import com.mobdb.android.MobDB;
import com.mobdb.android.MobDBResponseListener;

public class CreateGameActivity extends Activity {

	Button btn_back;
	Button btn_invite;
	Button btn_creategame;

	TextView tv_usernamemsg;
	EditText et_finduser;
	ListView lv_players;

	Intent returnintent;
	ArrayList<String> playerlist;
	String gameID = null;

	ArrayAdapter<String> playerlistadapter;
	
	private ClickHandler buttonHandler;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.creategame_layout);

		// set IDs
		btn_back = (Button) findViewById(R.id.btn_back);
		btn_invite = (Button) findViewById(R.id.btn_invite);
		btn_creategame = (Button) findViewById(R.id.btn_creategame);
		tv_usernamemsg = (TextView) findViewById(R.id.tv_username_error);
		et_finduser = (EditText) findViewById(R.id.et_finduser);
		lv_players = (ListView) findViewById(R.id.lv_invited);

		// set button handlers
		buttonHandler = new ClickHandler();
		btn_back.setOnClickListener(buttonHandler);
		btn_invite.setOnClickListener(buttonHandler);
		btn_creategame.setOnClickListener(buttonHandler);

		//set adapter stuff
		playerlistadapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
		lv_players.setAdapter(playerlistadapter);
	}

	private class ClickHandler implements OnClickListener {
		public void onClick(View v) {
			switch (v.getId()) {

			case R.id.btn_back:
				returnintent = new Intent();
				setResult(RESULT_CANCELED);
				finish();
				break;
			case R.id.btn_invite:
				String in_username = et_finduser.getText().toString().trim();
				if (in_username.equals("")) {
					tv_usernamemsg.setText(getString(R.string.note_username_empty));
				}
				
				GetRowData getRowData = new GetRowData(AccountActivity.tableName);
				
				MobDB.getInstance().execute(AccountActivity.mobDbKey, getRowData, null, false,
						new MobDBResponseListener() {
							
							@Override
							public void mobDBSuccessResponse() {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void mobDBResponse(Vector<HashMap<String, Object[]>> result) {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void mobDBResponse(String jsonObj) {
								try 
								{
									JSONObject response = new JSONObject(jsonObj);
									
									int baseLength = response.getJSONArray("row").length();
									String user = et_finduser.getText().toString();
									
									for(int i = 0; i < baseLength; i++)
									{
										if(user.equals(response.getJSONArray("row").getJSONObject(i)
												.getString(AccountActivity.USER_NAME)))
										{
											tv_usernamemsg.setText(getString(R.string.note_username_found));
											Toast.makeText(getApplicationContext(), "Made it", 
													Toast.LENGTH_LONG).show();
										}
									}
								} 
								catch (JSONException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							
							@Override
							public void mobDBFileResponse(String fileName, byte[] fileData) {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void mobDBErrorResponse(Integer errValue, String errMsg) {
								// TODO Auto-generated method stub
								
							}
						});
				// query for user
				// if found in database
				// tv_usernamemsg.setText(R.string.tv_username_found);
				
				// add to list of players
				//playerlistadapter.add(user);
				//playerlist.add(user);
				
				//enable create game since >1 users invited
				//btn_creategame.setClickable(true);
				// else
				// set error msg for username
				// tv_usernamemsg.setText(R.string.tv_username_notfound);
				// clear edit text
				// et_finduser.setText("");

				break;

			case R.id.btn_creategame:
				// send playerlist to database
				
				// get or create? game id
				// gameID = created game id
				
				returnintent = new Intent();
				returnintent.putExtra("gameid", gameID);
				setResult(RESULT_OK, returnintent);
				finish();
				break;

			default:
				Log.d("CreateGameActivity.ClickHandler", "Error in Switch");
				break;
			}
		}
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activit_main_menu, menu);
		return true;
	}

}
