package com.globallogic.trainee.ostefanyshyn.todominitasks;

import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final int NOTIFICATION_ID = 1;
    private static final String CHANNEL_ID = "channel";
    private static final String TITLE = "My notification";
    private static final String CONTENT_TEXT = "Much longer text that cannot fit one line...";

    private Button mShowNotificationBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        mShowNotificationBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        createNotification();
    }

    private void createNotification() {
        NotificationCompat.BigTextStyle textStyle = new NotificationCompat.BigTextStyle().bigText(CONTENT_TEXT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(TITLE)
                .setContentText(CONTENT_TEXT)
                .setStyle(textStyle)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(openAppOnTap())
                .setAutoCancel(true);
        showNotification(builder);
    }

    private void showNotification(NotificationCompat.Builder builder) {
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(NOTIFICATION_ID, builder.build());
    }

    private PendingIntent openAppOnTap() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        return pendingIntent;
    }

    public void initViews() {
        mShowNotificationBtn = findViewById(R.id.button_show_notification);
    }
}
