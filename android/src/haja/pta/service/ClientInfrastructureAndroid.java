package haja.pta.service;

import java.io.IOException;

import haja.pta.R;
import haja.pta.common.communication.IGenericStream;
import haja.pta.common.communication.commands.client.IClientCommand;
import haja.pta.common.communication.commands.server.IServerCommand;
import haja.pta.common.communication.commands.server.InfoMessageCommand;
import haja.pta.common.communication.infrastructure.IClientInfrastructure;
import haja.pta.notification.PtaNotificationManager;
import haja.pta.service.media.PtaMediaManager;
import android.app.Notification;
import android.app.Service;


/**
 * @author Harald Jagenteufel
 * 
 */
public class ClientInfrastructureAndroid implements IClientInfrastructure {


    private static final int NOTIFICATION_PLAYBACK_ID = 1;
    private Service _service;
    private PtaNotificationManager _ptaNotificationManager;
    private PtaMediaManager _ptaMediaManager;
    private IGenericStream<IServerCommand, IClientCommand> _stream;


    public ClientInfrastructureAndroid(Service service, IGenericStream<IServerCommand, IClientCommand> stream) {
        _service = service;
        _stream = stream;
        _ptaNotificationManager = PtaNotificationManager.getInstance();
        _ptaNotificationManager.init(service);
        _ptaMediaManager = PtaMediaManager.getInstance();
        _ptaMediaManager.init(_service);
    }

    public void displayNotification(String title, String message) {
        _ptaNotificationManager.displayNotification(title, message);
    }
    
    @Override
    public void playMedia(String url) {
        Notification notificationOngoing = _ptaNotificationManager.createOngoingNotification(_service.getResources().getText(R.string.notifictation_title_mediaplayback), url);
        _service.startForeground(NOTIFICATION_PLAYBACK_ID, notificationOngoing);
        _ptaMediaManager.play(url);
        try {
            _stream.write(new InfoMessageCommand("playback started"));
        } catch(IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    @Override
    public void stopMedia() {
        _ptaMediaManager.stop();
        _service.stopForeground(true);
        try {
            _stream.write(new InfoMessageCommand("playback stopped"));
        } catch(IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
