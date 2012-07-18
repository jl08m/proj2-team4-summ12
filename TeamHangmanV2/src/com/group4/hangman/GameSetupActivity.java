package com.group4.hangman;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class GameSetupActivity extends Activity {

	private final int CREATE_GAME = 1;

	Button btn_creategame;
	Button btn_joingame;
	ListView lv_games;
	ListView lv_players;

	String gameID;
	private ClickHandler buttonHandler;
	Intent myintent;
	ArrayList<String> gamelist;
	ArrayList<String> playerlist;
	
	ArrayAdapter<String> gameadapter;
	
	ArrayAdapter<String> playeradapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gamesetup_layout);
		
		btn_creategame = (Button) findViewById(R.id.btn_creategame);
		btn_joingame = (Button) findViewById(R.id.btn_joingame);
		lv_games = (ListView) findViewById(R.id.lv_gameids);
		lv_players = (ListView) findViewById(R.id.lv_players);

		buttonHandler = new ClickHandler();
		btn_creategame.setOnClickListener(buttonHandler);
		btn_joingame.setOnClickListener(buttonHandler);
		
		gameadapter = new ArrayAdapter<String>  (
		  		this, 
				android.R.layout.simple_list_item_1, 
				android.R.id.text1,
				gamelist);
		
		playeradapter = new ArrayAdapter<String>  (
				this, 
				android.R.layout.simple_list_item_1,
				android.R.id.text1,
				playerlist);
		
		//lv_games.setAdapter(gameadapter);
		//lv_players.setAdapter(playeradapter);
		
		lv_games.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				gameID = gamelist.get(position);
				updatePlayers();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activit_main_menu, menu);
		return true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case CREATE_GAME:
			if (resultCode == RESULT_CANCELED) {
				Log.d("gamesetup onactivityresult", "result canceled");
			} else if (resultCode == RESULT_OK) {
				Log.d("gamesetup onactivityresult", "result ok");
				updateGames();
			} else {
				Log.d("gamesetup onactivityresult", "create game return error");
			}
		}
	}

	private class ClickHandler implements OnClickListener {
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btn_creategame:
				myintent = new Intent(getApplicationContext(),
						CreateGameActivity.class);
				startActivityForResult(myintent, CREATE_GAME);
				break;

			case R.id.btn_joingame:
				myintent = new Intent(getApplicationContext(),
						GameMainActivity.class);
				// myintent.putExtra("gameid", gameID);
				startActivity(myintent);
				break;

			default:
				Log.d("GameSetupActivity.ClickHandler", "Error in Switch");
			}
		}
	}

	private void updateGames() {
		// Query for list of games user is in
		// gameslist = database list of games
		gameadapter.notifyDataSetChanged();
	}

	private void updatePlayers() {
		// query gameID in database for players in game
		// update list of players
		// playerlist = database list of players in game
		playeradapter.notifyDataSetChanged();
	}

}
