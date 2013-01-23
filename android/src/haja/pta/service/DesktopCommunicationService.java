package haja.pta.service;

import haja.pta.R;
import haja.pta.common.dto.IAmAlive;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;


/**
 * @author Harald Jagenteufel
 * 
 */
public class DesktopCommunicationService extends Service {

    private static final String PREFS_NAME = "desktop.config";

    protected static final String TAG = "DesktopCommunicationService";

    private String _host;
    private int _port;

    private Runnable _serviceRunnable = new Runnable() {

        private boolean _running = true;

        @Override
        public void run() {
            try {
                // open connection to desktop and send/receive some data
                Socket socket = new Socket(_host, _port);

                ObjectOutputStream outputStream = new ObjectOutputStream(
                        socket.getOutputStream());
                ObjectInputStream inputStream = new ObjectInputStream(
                        socket.getInputStream());
                outputStream.writeObject(new IAmAlive("android"));
                outputStream.flush();

                while(_running) {
                    IAmAlive desktopAlive = (IAmAlive) inputStream.readObject();
                    Log.i(TAG, desktopAlive.getMessage());
                }

                socket.close();
            } catch(IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch(ClassNotFoundException e) {
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