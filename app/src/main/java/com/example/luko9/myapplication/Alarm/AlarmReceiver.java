package com.example.luko9.myapplication.Alarm;

import android.annotation.TargetApi;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.luko9.myapplication.DB.DBHelper;
import com.example.luko9.myapplication.Email.SendMailTask;
import com.example.luko9.myapplication.Entity.TvShows;
import com.example.luko9.myapplication.R;
import com.example.luko9.myapplication.TvShows.FavouriteShowsListActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AlarmReceiver extends BroadcastReceiver {
    DBHelper dbHelper;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onReceive(Context context, Intent intent) {
        dbHelper = new DBHelper(context);
        String names = "";
        ArrayList<TvShows> shows = dbHelper.getAllFavouriteShows();
        for (TvShows show : shows) {
            names += show.getName() + ", ";

        }

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);

        boolean btnEnabled = preferences.getBoolean("isUsingEmailNotif", false);
        boolean btnPushEnabled = preferences.getBoolean("isUsingPushNotif", false);

        if (btnEnabled) {
            String toEmail = intent.getExtras().getString("toEmail");


            Log.i("SendMailActivity", "Send Button Clicked.");
            String fromEmail = "mytvapp16@gmail.com";
            String fromPassword = "mytvapp16@@";
            String toEmails = toEmail;
            List<String> toEmailList = Arrays.asList(toEmails
                    .split("\\s*,\\s*"));
            Log.i("SendMailActivity", "To List: " + toEmailList);
            String emailSubject = "Notification for your favourite shows!";
            String emailBody = "Have you watched the latest episode of: " + names;
            emailBody = emailBody.substring(0, emailBody.lastIndexOf(","));
            new SendMailTask().execute(fromEmail,
                    fromPassword, toEmailList, emailSubject, emailBody);
        }
        if (btnPushEnabled) {
            long when = System.currentTimeMillis();
            NotificationManager notificationManager = (NotificationManager) context
                    .getSystemService(Context.NOTIFICATION_SERVICE);

            Intent notificationIntent = new Intent(context, FavouriteShowsListActivity.class);
            notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
                    notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);


            Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

            NotificationCompat.Builder mNotifyBuilder = new NotificationCompat.Builder(
                    context).setSmallIcon(R.drawable.launcher_icon)
                    .setContentTitle("Have you watched?")
                    .setContentText("New episodes for:" + names).setSound(alarmSound)
                    .setAutoCancel(true).setWhen(when)
                    .setContentIntent(pendingIntent)
                    .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000});
            notificationManager.notify(0, mNotifyBuilder.build());
        }
    }
}
