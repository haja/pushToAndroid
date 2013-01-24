package haja.pta.service;

import haja.pta.R;
import haja.pta.common.communication.IGenericStream;
import haja.pta.common.communication.commands.client.IClientCommand;
import haja.pta.common.communication.commands.server.IServerCommand;
import haja.pta.common.communication.commands.server.LoginCommand;
import haja.pta.common.communication.infrastructure.IClientInfrastructure;
import haja.pta.common.communication.tcp.GenericStreamTcp;

import java.io.IOException;
import java.net.InetAddress;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.widget.Toast;

/**
 * @author Harald Jagenteufel
 * 
 */
public class DesktopCommunicationService extends Service {

    private static final String PREFS_NAME = "desktop.config";
    protected static final String TAG = "DesktopCommunicationService";
    
    private IClientInfrastructure _infrastructure;

    private Runnable _serviceRunnable = new Runnable() {

        private boolean _running = true;

        @Override
        public void run() {
            try {
                SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                String host = settings.getString("desktop_host", getResources()
                        .getString(R.string.desktop_host_default));
                int port = settings.getInt("desktop_port", getResources()
                        .getInteger(R.integer.desktop_port_default));
                
                // open connection to desktop and send/receive some data
                IGenericStream<IServerCommand, IClientCommand> stream = new GenericStreamTcp<IServerCommand, IClientCommand>(
                        InetAddress.getByName(host), port);

                stream.write(new LoginCommand("android_user"));

                while(_running) {
                    IClientCommand cmd = stream.read();
                    cmd.execute(DesktopCommunicationService.this._infrastructure);
                }

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
        _infrastructure = new ClientInfrastructureAndroid(this);
        
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