package github.hmasum18.brackgroundservicedemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import github.hmasum18.brackgroundservicedemo.databinding.ActivityMainBinding;

/**
 *
 * @author Hasan Masum
 * @since 22 oct 2021
 */
public class MainActivity extends AppCompatActivity {
    private PowerButtonActionReceiver receiver;
    public static final String NOTIFICATION_CHANNEL_ID = "TEST_CHANNEL";

    // all views
    ActivityMainBinding mVB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mVB = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mVB.getRoot());

        createNotificationChannel();

        mVB.startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startEmergencyService();
            }
        });

        mVB.stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopEmergencyService();
            }
        });
    }

    private void stopEmergencyService() {
        Intent intent = new Intent(this, LockScreenService.class);
        stopService(intent);
    }

    private void startEmergencyService(){
        Intent intent = new Intent(this, LockScreenService.class);
        ContextCompat.startForegroundService(this, intent);
    }

    private void createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel(
                   NOTIFICATION_CHANNEL_ID,
                   "Our test channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );

            NotificationManager manager = super.getSystemService(NotificationManager.class);
            manager.createNotificationChannel(notificationChannel);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}