package com.group4.hangman;

import java.util.HashMap;
import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mobdb.android.GetRowData;
import com.mobdb.android.InsertRowData;
import com.mobdb.android.MobDB;
import com.mobdb.android.MobDBResponseListener;

public class AccountActivity extends Activity {
	public final static String mobDbKey = "ii1oo5-r01-44Ysp6JkEReeYy58bqLovoaK-xcx8bqpODDB1t109O1KkK";
	public final static String tableName = "logininfo";
	public final static String USER_NAME = "username";
	public final static String USER_PASSWORD = "password";
	public static String chatName;

	private final int MAXUSERNAME = 20;
	private final int MAXPASS = 7;
	private Button btn_signUp;
	Button btn_login;
	Button btn_done;

	private ClickHandler buttonHandler;

	private EditText et_userName;
	EditText et_userPassword;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.account_layout);

		btn_signUp = (Button) findViewById(R.id.btn_signup);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_done = (Button) findViewById(R.id.btn_done);

		buttonHandler = new ClickHandler();
		btn_signUp.setOnClickListener(buttonHandler);
		btn_login.setOnClickListener(buttonHandler);
		btn_done.setOnClickListener(buttonHandler);

		et_userName = (EditText) findViewById(R.id.et_username);
		et_userPassword = (EditText) findViewById(R.id.et_password);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activit_main_menu, menu);
		return true;
	}

	public class ClickHandler implements OnClickListener {
		MobDBHandler mobDBListener;

		public void onClick(View v) {
			final String qUsername = et_userName.getText().toString().trim();
			final String qPassword = et_userPassword.getText().toString().trim();

			switch (v.getId()) {
			case R.id.btn_signup: {
				if (qUsername.length() > 0 && qUsername.length() <= MAXUSERNAME
						&& qPassword.length() > 0
						&& qPassword.length() <= MAXPASS) {
					Toast.makeText(getApplicationContext(), "Made it!",
							Toast.LENGTH_LONG).show();
					mobDBListener = new MobDBHandler();

					InsertRowData insertRowData = new InsertRowData(tableName);

					insertRowData.setValue(USER_NAME, qUsername);
					insertRowData.setValue(USER_PASSWORD, qPassword);
					insertRowData.setValue("id", 2);

					MobDB.getInstance().execute(mobDbKey, insertRowData, null,
							false, mobDBListener);
					chatName = qUsername;
				} else {
					if (qUsername.length() == 0)
						et_userName.setError(getString(R.string.note_username_empty));
					if (qUsername.length() > MAXUSERNAME)
						et_userName
								.setError(getString(R.string.note_username_toolong));

					if (qPassword.length() == 0)
						et_userPassword.setError(getString(R.string.note_password_empty));
					if (qPassword.length() > MAXPASS)
						et_userPassword
								.setError(getString(R.string.note_password_toolong));
				}
			}
				break;

			case R.id.btn_login: {
				GetRowData getRowData = new GetRowData(tableName);

				MobDB.getInstance().execute(mobDbKey, getRowData, null, false,
						new MobDBResponseListener() {

							public void mobDBSuccessResponse() {
								// TODO Auto-generated method stub

							}

							public void mobDBResponse(
									Vector<HashMap<String, Object[]>> result) {
								// TODO Auto-generated method stub

							}

							public void mobDBResponse(String jsonObj) {
								try {
									JSONObject response = new JSONObject(
											jsonObj);

									int baseLength = response.getJSONArray(
											"row").length();
									String user = qUsername;
									String passw = qPassword;

									for (int i = 0; i < baseLength; i++) {
										if (user.equals(response
												.getJSONArray("row")
												.getJSONObject(i)
												.getString(USER_NAME))
												&& passw.equals(response
														.getJSONArray("row")
														.getJSONObject(i)
														.getString(
																USER_PASSWORD)))
											LoginSuccess();
									}

									Toast.makeText(getApplicationContext(),
											"UnSuccessful Login",
											Toast.LENGTH_LONG).show();

									// If successful login, site the errors and
									// re-do login

								} catch (JSONException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}

							public void mobDBFileResponse(String fileName,
									byte[] fileData) {
								// TODO Auto-generated method stub

							}

							public void mobDBErrorResponse(Integer errValue,
									String errMsg) {
								// TODO Auto-generated method stub
								Toast.makeText(getApplicationContext(),
										"Error", Toast.LENGTH_LONG).show();
							}
						});

				chatName = qUsername;
			}
				break;

			case R.id.btn_done:
				Intent myintent = new Intent();
				setResult(2, myintent);
				finish();
				break;

			default:
				Log.d("AccountActivity.ClickHandler", "Error in Switch");
				break;
			}
		}

		private void LoginSuccess() {
			Toast.makeText(getApplicationContext(), "Successful Login",
					Toast.LENGTH_LONG).show(); // send to next activity
			TextView currentUser = (TextView) findViewById(R.id.tv_currentuser);
			currentUser.setText("Current User: " + chatName);
		}
	} /* End of ClickHandler inner class */

	private class MobDBHandler implements MobDBResponseListener {
		public void mobDBSuccessResponse() {
			// TODO Auto-generated method stub
			Toast.makeText(getApplicationContext(), "Success",
					Toast.LENGTH_LONG).show();
		}

		public void mobDBErrorResponse(Integer errValue, String errMsg) {
			// TODO Auto-generated method stub
			Toast.makeText(getApplicationContext(), "Failure",
					Toast.LENGTH_LONG).show();

		}

		public void mobDBResponse(String jsonObj) {
			try {
				JSONObject response = new JSONObject(jsonObj);

				Toast.makeText(getApplicationContext(), jsonObj,
						Toast.LENGTH_LONG).show();
				Log.d("RESULTS", jsonObj);
			} catch (JSONException e) {
				Toast.makeText(getApplicationContext(),
						"User inserted but couldn't get id", Toast.LENGTH_LONG)
						.show();
			}
		}

		public void mobDBFileResponse(String fileName, byte[] fileData) {
			// TODO Auto-generated method stub

		}

		public void mobDBResponse(Vector<HashMap<String, Object[]>> result) {
			// TODO Auto-generated method stub

		}

	}/* End of inner class MobHandler */
}/* End of overall AccountActivity main class */
