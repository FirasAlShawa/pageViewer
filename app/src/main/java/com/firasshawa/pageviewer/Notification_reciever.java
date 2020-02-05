package com.firasshawa.pageviewer;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;

import androidx.core.app.NotificationCompat;

import static com.firasshawa.pageviewer.App.CHANNEL_1_ID;
import static com.firasshawa.pageviewer.App.CHANNEL_2_ID;

public class Notification_reciever extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager notificationManager =(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent repeating_intent = new Intent(context,MainActivity.class);

        repeating_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(context,100,repeating_intent,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,CHANNEL_2_ID)
                .setSmallIcon(R.drawable.notifyiconsmile48)
                .setContentTitle("Bahja time")
                .setContentText(intent.getExtras().getString("quoteOfTheDay"))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),R.drawable.notifyiconross96))
                .setStyle(new NotificationCompat.BigTextStyle()
                        .setBigContentTitle("وقت البهجة")
                        .bigText(intent.getExtras().getString("quoteOfTheDay"))
                );

        notificationManager.notify(300,builder.build());

    }
}
