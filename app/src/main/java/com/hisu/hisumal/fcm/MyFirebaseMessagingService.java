package com.hisu.hisumal.fcm;

import android.app.Notification;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.hisu.hisumal.MyApplication;
import com.hisu.hisumal.R;

import org.jetbrains.annotations.NotNull;

import java.util.Date;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(@NonNull @NotNull RemoteMessage message) {
        super.onMessageReceived(message);

        RemoteMessage.Notification notification = message.getNotification();

        sendNotification(notification.getTitle(), notification.getBody());
    }

    private void sendNotification(String title, String msg) {
        Notification notification = new Notification.Builder(this, MyApplication.CHANNEL_ID)
                .setContentTitle(title)
                .setSmallIcon(R.drawable.login_bg)
                .setContentText(msg).build();

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(getNotificationID(), notification);
    }

    private int getNotificationID() {
        return (int) new Date().getTime();
    }
}