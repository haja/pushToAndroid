package haja.pta.service;

import haja.pta.PtaAndroidActivity;
import haja.pta.R;
import haja.pta.common.communication.GenericStreamTcp;
import haja.pta.common.communication.IGenericStream;
import haja.pta.common.dto.IAmAlive;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;
import android.widget.Toast;

/**
 * @author Harald Jagenteufel
 * 
 */
public class DesktopCommunicationService extends Service {

    private static final String PREFS_NAME = "desktop.config";
    protected static final String TAG = "DesktopCommunicationService";
    protected static final int COMMUNICATION_NOTIFICATION_ID = 0;

    private String _host;
    private int _port;

    private Runnable _serviceRunnable = new Runnable() {

        private boolean _running = true;

        @Override
        public void run() {
            try {
                // open connection to desktop and send/receive some data
                IGenericStream<IAmAlive, IAmAlive> stream = new GenericStreamTcp<IAmAlive, IAmAlive>(
                        InetAddress.getByName(_host), _port);

                stream.write(new IAmAlive("android"));

                NotificationManager notificationManager =
                        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

//                while(_running) {
                    IAmAlive desktopAlive = stream.read();
                    Log.i(TAG, desktopAlive.getMessage());

                    Intent resultIntent = new Intent(
                            DesktopCommunicationService.this,
                            PtaAndroidActivity.class);
                    TaskStackBuilder stackBuilder = TaskStackBuilder
                            .from(DesktopCommunicationService.this);
                    stackBuilder.addNextIntent(resultIntent);
                    PendingIntent intent = stackBuilder.getPendingIntent(0,
                            PendingIntent.FLAG_UPDATE_CURRENT);

                    NotificationCompat.Builder builder = new NotificationCompat.Builder(
                            DesktopCommunicationService.this);
                    builder.setContentTitle("desk message")
                    .setContentText(desktopAlive.getMessage())
                    .setContentIntent(intent)
                    .setSmallIcon(R.drawable.notification_icon);

                    notificationManager.notify(COMMUNICATION_NOTIFICATION_ID,
                            builder.getNotification());
//                }

                stream.close();
            } catch(IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            DesktopCommunicationService.this.stopSelf();
        }
    };

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public void onCreate() {
        Toast.makeText(this, "service starting", Toast.LENGTH_SHORT).show();

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        _host = settings.getString("desktop_host", getResources()
                .getString(R.string.desktop_host_default));
        _port = settings.getInt("desktop_port", getResources()
                .getInteger(R.integer.desktop_port_default));


        Thread thread = new Thread(null, _serviceRunnable, "ptaClient");
        thread.start();
    }

    @Override
    public void onDestroy() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }
}