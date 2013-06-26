package com.bestweightmanager.example.exampledailynotification;

import com.bestweightmanager.example.exampledailynotification.preferences.PreferencesActivity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	// Add the different option(s) on the menu
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return (actionItemMenu(item) || super.onOptionsItemSelected(item));
	}

	// Action(s) that will be done when an option is pressed
	private boolean actionItemMenu(MenuItem item) {
		int itemId = item.getItemId();
		switch (itemId) {
		case R.id.action_settings:
			Intent intent = new Intent(getApplicationContext(),
					PreferencesActivity.class);
			startActivity(intent);
			break;
		}
		return false;
	}

}
