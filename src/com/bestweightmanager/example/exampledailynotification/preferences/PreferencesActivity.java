package com.bestweightmanager.example.exampledailynotification.preferences;

import com.bestweightmanager.example.exampledailynotification.R;
import com.bestweightmanager.example.exampledailynotification.utils.DailyNotification;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.net.Uri;
import android.os.Bundle;
import android.preference.*;

public class PreferencesActivity extends PreferenceActivity  {

	private SharedPreferences sharedPrefs;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences);
		sharedPrefs = getPreferenceScreen().getSharedPreferences();

	}

	// Register or remove the alarm whenever the user check or un-check the
	// Daily Notification preference
	public boolean onPreferenceChange(Preference paramPreference,
			Object paramObject) {
		if (paramPreference.getKey().equals("show_notification")) {
			if (((Boolean) paramObject).booleanValue() == true) {
				DailyNotification.registerAlarm(this);
			} else {
				DailyNotification.removeAlarm(this);
			}
		}
		return false;

	}
}
