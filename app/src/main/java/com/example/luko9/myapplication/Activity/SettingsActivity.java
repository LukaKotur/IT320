package com.example.luko9.myapplication.Activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.luko9.myapplication.Alarm.AlarmReceiver;
import com.example.luko9.myapplication.R;

import java.util.Calendar;


public class SettingsActivity extends AppCompatActivity {
    Toast mToast;
    SwitchCompat aSwitch, bSwitch;
    EditText toEmail;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        aSwitch = (SwitchCompat) findViewById(R.id.aSwitch);
        bSwitch = (SwitchCompat) findViewById(R.id.bSwitch);
        toEmail = (EditText) findViewById(R.id.toEmail);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        boolean btnEnabled = preferences.getBoolean("isUsingEmailNotif", false);
        boolean btnPushEnabled = preferences.getBoolean("isUsingPushNotif", false);
        if (btnEnabled) {
            aSwitch.setChecked(true);
        }
        if (btnPushEnabled) {
            bSwitch.setChecked(true);
        }
        String email = preferences.getString("email", "looko95@gmail.com");
        toEmail.setText(email);

        setTitle("Settings");

    }

    public void clickedEmailNotifications(View view) {
        if (!aSwitch.isChecked()) {

            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(SettingsActivity.this);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("email", toEmail.getText().toString());
            editor.putBoolean("isUsingEmailNotif", false);
            editor.commit();

            Intent intent = new Intent(SettingsActivity.this, AlarmReceiver.class);
            intent.putExtra("toEmail", toEmail.getText().toString());
            PendingIntent sender = PendingIntent.getBroadcast(SettingsActivity.this, 0,
                    intent, PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
            am.cancel(sender);


            if (mToast != null) {
                mToast.cancel();
            }
            mToast = Toast.makeText(SettingsActivity.this, "Weekly notifications turned off!",
                    Toast.LENGTH_LONG);
            mToast.show();

        } else {

            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(SettingsActivity.this);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("email", toEmail.getText().toString());
            editor.putBoolean("isUsingEmailNotif", true);
            editor.commit();


            Intent intent = new Intent(SettingsActivity.this, AlarmReceiver.class);
            intent.putExtra("toEmail", toEmail.getText().toString());
            PendingIntent sender = PendingIntent.getBroadcast(SettingsActivity.this, 0,
                    intent, PendingIntent.FLAG_UPDATE_CURRENT);


            long firstTime = SystemClock.elapsedRealtime();
            firstTime += 15 * 1000;
            long week = 7 * 24 * 60 * 60 * 1000L;

            // Schedule the alarm!
            AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
            am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, firstTime,
                    week, sender);


            if (mToast != null) {
                mToast.cancel();
            }
            mToast = Toast.makeText(SettingsActivity.this, "Weekly notifications turned on!",
                    Toast.LENGTH_LONG);
            mToast.show();
        }
    }

    public void clickedPushNotifications(View view) {
        if (!bSwitch.isChecked()) {

            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(SettingsActivity.this);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("isUsingPushNotif", false);
            editor.commit();

            Intent intent = new Intent(SettingsActivity.this, AlarmReceiver.class);
            PendingIntent sender = PendingIntent.getBroadcast(SettingsActivity.this, 0,
                    intent, PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
            am.cancel(sender);


            if (mToast != null) {
                mToast.cancel();
            }
            mToast = Toast.makeText(SettingsActivity.this, "Weekly notifications turned off!",
                    Toast.LENGTH_LONG);
            mToast.show();
        } else {

            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(SettingsActivity.this);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("isUsingPushNotif", true);
            editor.commit();

            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, 11);
            calendar.set(Calendar.MINUTE, 32);
            calendar.set(Calendar.SECOND, 0);
            Intent intent1 = new Intent(this, AlarmReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
            am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY * 7, pendingIntent);

            if (mToast != null) {
                mToast.cancel();
            }
            mToast = Toast.makeText(SettingsActivity.this, "Weekly notifications turned on!",
                    Toast.LENGTH_LONG);
            mToast.show();
        }

    }

}

