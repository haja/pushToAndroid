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
import haja.pta.notification.PtaNotificationManager;


/**
 * @author Harald Jagenteufel
 * 
 */
public class ClientInfrastructureAndroid implements IClientInfrastructure {


    private Service _service;
    private PtaNotificationManager _ptaNotificationManager;


    public ClientInfrastructureAndroid(Service service) {
        _service = service;
        _ptaNotificationManager = PtaNotificationManager.getInstance();
        _ptaNotificationManager.init(service);
    }

    public void displayNotification(String title, String message) {
        _ptaNotificationManager.displayNotification(title, message);
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
