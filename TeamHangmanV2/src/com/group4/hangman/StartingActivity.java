package com.group4.hangman;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

public class StartingActivity extends Activity implements OnClickListener {

	Intent myintent;
	// Color for the background
	public static GradientDrawable garnetGoldGradient = null;
	public static Color[] colors = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start_layout);

		findViewById(R.id.start_game).setOnClickListener(this);
		findViewById(R.id.btn_account).setOnClickListener(this);
		findViewById(R.id.btn_exit).setOnClickListener(this);

		colors = new Color[3];

		for (int i = 0; i < colors.length; i++)
			colors[i] = new Color();

		int[] gColor = new int[colors.length];

		gColor[0] = Color.rgb(125, 9, 0); // gold
		gColor[1] = Color.BLACK;
		gColor[2] = Color.rgb(255, 216, 0); // garnet

		garnetGoldGradient = new GradientDrawable(Orientation.BOTTOM_TOP,
				gColor);

		findViewById(R.id.starter_layout) // Set the background of layout
				.setBackgroundDrawable(garnetGoldGradient);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activit_main_menu, menu);
		return true;
	}

	public void onClick(View view) 
	{
		switch(view.getId())
		{
			case R.id.start_game: 
				myintent = new Intent(getApplicationContext(), GameSetupActivity.class);
				startActivity(myintent);
				break;
			
			case R.id.btn_account:
				myintent = new Intent(getApplicationContext(), AccountActivity.class);
				startActivity(myintent);
				break;
		
			case R.id.btn_exit:
				finish();
				break;
		}
	}

}
