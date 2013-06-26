package com.bestweightmanager.example.exampledailynotification.utils;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceManager;

import java.util.Calendar;

import com.bestweightmanager.example.exampledailynotification.MainActivity;
import com.bestweightmanager.example.exampledailynotification.R;

public class DailyNotification extends BroadcastReceiver {

	// Register the alarm and set it at 7am everyday (repeating mode)
	public static void registerAlarm(Context paramContext) {
		Calendar calendar = Calendar.getInstance();
		if (calendar.get(Calendar.HOUR_OF_DAY) >= 7) {
			calendar.add(7, 1);
		}
		calendar.set(Calendar.HOUR_OF_DAY, 07);
		calendar.set(Calendar.MINUTE, 00);
		calendar.set(Calendar.SECOND, 00);

		// PendingIntent that will perform a broadcast
		PendingIntent localPendingIntent = PendingIntent
				.getBroadcast(
						paramContext,
						22341,
						new Intent(
								"com.bestweightmanager.example.exampledailynotification.DAILY_NOTIFICATION"),
						PendingIntent.FLAG_UPDATE_CURRENT);
		// Retrieve an AlarmManager to set a repeating daily alarm
		((AlarmManager) paramContext.getSystemService("alarm")).setRepeating(1,
				calendar.getTimeInMillis(), 24 * 60 * 60 * 1000,
				localPendingIntent);
	}

	// Remove the alarm
	public static void removeAlarm(Context paramContext) {
		// PendingIntent that will perform a broadcast
		PendingIntent localPendingIntent = PendingIntent
				.getBroadcast(
						paramContext,
						22341,
						new Intent(
								"com.bestweightmanager.example.exampledailynotification.DAILY_NOTIFICATION"),
						PendingIntent.FLAG_UPDATE_CURRENT);
		// Retrieve an AlarmManager to cancel the alarm
		((AlarmManager) paramContext.getSystemService("alarm"))
				.cancel(localPendingIntent);
	}

	// Show a notification in the Notification bar with the application icon,
	// the name of the application
	// Notification will launched the EnterWeightActivity once the user touches
	// it
	private void showNotification(Context paramContext) {
		Notification localNotification = new Notification(
				R.drawable.ic_launcher,
				paramContext.getString(R.string.app_name),
				System.currentTimeMillis());
		localNotification.flags = (0x10 | localNotification.flags);
		// PendingIntent that will start the EnterWeightActivity
		PendingIntent localPendingIntent = PendingIntent.getActivity(
				paramContext, 0, new Intent(paramContext, MainActivity.class),
				0);
		localNotification.setLatestEventInfo(paramContext,
				paramContext.getString(R.string.app_name),
				paramContext.getString(R.string.notification),
				localPendingIntent);
		// Retrieve a NotificationManager to show the notification
		((NotificationManager) paramContext.getSystemService("notification"))
				.notify(0, localNotification);
	}

	// Override the onReceive, called when the BroadcastReceiver is receiving an
	// Intent broadcast
	@Override
	public void onReceive(Context paramContext, Intent paramIntent) {
		if (paramIntent.getAction().equals(
				"com.bestweightmanager.example.exampledailynotification.DAILY_NOTIFICATION"))
			showNotification(paramContext);
		if (PreferenceManager.getDefaultSharedPreferences(paramContext)
				.getBoolean("show_notification", false))
			registerAlarm(paramContext);

	}
}