package haja.pta;

import haja.pta.common.dto.IAmAlive;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Config;
import android.util.Log;

public class PtaAndroidActivity extends Activity
{

    private static final String PREFS_NAME = "desktop.config";
    private static final String TAG = "PtaAndroidActivity";

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        String desktopHost = settings.getString("desktop_host", getResources()
                .getString(R.string.desktop_host_default));
        int desktopPort = settings.getInt("desktop_port", getResources()
                .getInteger(R.integer.desktop_port_default));

        // open connection to desktop and send/receive some data
        try {
            Socket desktop = new Socket(desktopHost, desktopPort);
            ObjectOutputStream outputStream = new ObjectOutputStream(desktop.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(desktop.getInputStream());
            outputStream.writeObject(new IAmAlive("android"));
            outputStream.flush();
            IAmAlive desktopAlive = (IAmAlive) inputStream.readObject();
            Log.i(TAG, desktopAlive.getMessage());
            
            desktop.close();
            Log.i(TAG, "socket to desktop closed");

        } catch(UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch(IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch(ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
