/**
 * 
 */
package haja.pta.service;

import haja.pta.R;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;


/**
 * @author Harald Jagenteufel
 * 
 */
public class DesktopCommunicationService extends Service {

    private static final String PREFS_NAME = "desktop.config";

    private String _host;
    private int _port;


    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // open connection to desktop and send/receive some data
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        _host = settings.getString("desktop_host", getResources()
                .getString(R.string.desktop_host_default));
        _port = settings.getInt("desktop_port", getResources()
                .getInteger(R.integer.desktop_port_default));

        try {
            Socket socket = new Socket(_host, _port);
            new Thread(new DesktopCommunicationHandler(socket)).start();

        } catch(UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch(IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return START_STICKY;
    }

}
