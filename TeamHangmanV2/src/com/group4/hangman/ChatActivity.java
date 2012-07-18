package com.group4.hangman;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class ChatActivity extends Activity {

	String chat_name;
	final String chatTable = "chat";
	
	EditText et_msg;
	Button btn_send;
	ListView msgList;
	ArrayAdapter<String> msgadapter;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_layout);
        
        chat_name = (AccountActivity.chatName == null ? "Teammate" : 
        	AccountActivity.chatName);
        
        //set IDs for UI
        et_msg = (EditText) findViewById(R.id.et_msg);
        btn_send = (Button) findViewById(R.id.btn_sendmsg);
        msgList = (ListView) findViewById(R.id.lv_msgList);
        msgadapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
    
        //Set msg list adapter
        msgList.setAdapter(msgadapter);
        
        //set send button click
        btn_send.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		sendMessage();
        	}
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) 
    {
        getMenuInflater().inflate(R.menu.activit_main_menu, menu);
        return true;
    }

    private void sendMessage() {
    	//get input
    	String newmsg = et_msg.getText().toString();
    	
    	//send message to database
    	//surround in try/catch
    	
    	msgadapter.add("Me: " + newmsg); //add my msg to list
    	et_msg.setText(""); //reset edit text to blank
    }

    private void receiveMessage() {
    	//get array of strings from database?
    	String incomingmsg = null; //put received messages here
    	
    	//put received msg into msglist
    	msgadapter.add(incomingmsg); 
    	
    }
}
