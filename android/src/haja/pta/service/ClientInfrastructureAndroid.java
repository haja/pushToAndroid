package haja.pta.service;

import java.io.IOException;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import haja.pta.PtaAndroidActivity;
import haja.pta.R;
import haja.pta.common.communication.infrastructure.IClientInfrastructure;


/**
 * @author Harald Jagenteufel
 * 
 */
public class ClientInfrastructureAndroid implements IClientInfrastructure {


    private NotificationManager _notificationManager;
    private Service _service;

    private static final int COMMUNICATION_NOTIFICATION_ID = 0;

    public ClientInfrastructureAndroid(Service service) {
        _service = service;
        _notificationManager =
                (NotificationManager) service.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    public void displayNotification(String title, String message) {

        Intent resultIntent = new Intent(
                _service,
                PtaAndroidActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder
                .from(_service);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent intent = stackBuilder.getPendingIntent(0,
                PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(
                _service);
        builder.setDefaults(Notification.DEFAULT_ALL)
                .setContentTitle(title)
                .setContentText(message)
                .setTicker(message)
                .setContentIntent(intent)
                .setSmallIcon(R.drawable.notification_icon);

        _notificationManager.notify(COMMUNICATION_NOTIFICATION_ID,
                builder.getNotification());
    }

    @Override
    public void playMedia(String url) {
        MediaPlayer mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch(IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch(IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch(IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
