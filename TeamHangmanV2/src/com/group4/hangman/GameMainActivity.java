package com.group4.hangman;

import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class GameMainActivity extends Activity {

	HashMap<String, Button> Letters = new HashMap<String, Button>();

	Button btn_gochat = (Button) findViewById(R.id.btn_enterchat);
	Button btn_guess = (Button) findViewById(R.id.btn_guess);
	TextView tv_phrase = (TextView) findViewById(R.id.tv_guessphrase);
	int turn;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_layout);

		// query current turn
		if (turn == 0) {
			initletters();
		} else {
			updateletters();
			updatephrase();
			updateimage();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activit_main_menu, menu);
		return true;
	}

	private void initletters() {
		Letters.put("A", (Button) findViewById(R.id.btn_A));
		Letters.put("B", (Button) findViewById(R.id.btn_B));
		Letters.put("C", (Button) findViewById(R.id.btn_C));
		Letters.put("D", (Button) findViewById(R.id.btn_D));
		Letters.put("E", (Button) findViewById(R.id.btn_E));
		Letters.put("F", (Button) findViewById(R.id.btn_F));
		Letters.put("G", (Button) findViewById(R.id.btn_G));
		Letters.put("H", (Button) findViewById(R.id.btn_H));
		Letters.put("I", (Button) findViewById(R.id.btn_I));
		Letters.put("J", (Button) findViewById(R.id.btn_J));
		Letters.put("K", (Button) findViewById(R.id.btn_K));
		Letters.put("L", (Button) findViewById(R.id.btn_L));
		Letters.put("M", (Button) findViewById(R.id.btn_M));
		Letters.put("N", (Button) findViewById(R.id.btn_N));
		Letters.put("O", (Button) findViewById(R.id.btn_O));
		Letters.put("P", (Button) findViewById(R.id.btn_P));
		Letters.put("Q", (Button) findViewById(R.id.btn_Q));
		Letters.put("R", (Button) findViewById(R.id.btn_R));
		Letters.put("S", (Button) findViewById(R.id.btn_S));
		Letters.put("T", (Button) findViewById(R.id.btn_T));
		Letters.put("U", (Button) findViewById(R.id.btn_U));
		Letters.put("V", (Button) findViewById(R.id.btn_V));
		Letters.put("W", (Button) findViewById(R.id.btn_W));
		Letters.put("X", (Button) findViewById(R.id.btn_X));
		Letters.put("Y", (Button) findViewById(R.id.btn_Y));
		Letters.put("Z", (Button) findViewById(R.id.btn_Z));
	}

	private void updateletters() {
		// query database for letters played
		// put queries into String guessed
		// String guessed = "";
		// Button bt;
		// for(int i=0; i<guessed.length(); i++) {
		// bt = Letters.get(guessed.substring(i, i+1));
		// bt.setClickable(false);
		//
		// }
	}

	private void updatephrase() {
		// query database for phrase
		// put phrase into String current_phrase
		// String current_phrase = ;
		// tv_phrase.setText(current_phrase);
	}

	private void updateimage() {

	}
}
