package haja.pta.service;

import haja.pta.common.communication.infrastructure.IClientInfrastructure;
import haja.pta.notification.PtaNotificationManager;
import haja.pta.service.media.PtaMediaManager;
import android.app.Service;


/**
 * @author Harald Jagenteufel
 * 
 */
public class ClientInfrastructureAndroid implements IClientInfrastructure {


    private Service _service;
    private PtaNotificationManager _ptaNotificationManager;
    private PtaMediaManager _ptaMediaManager;


    public ClientInfrastructureAndroid(Service service) {
        _service = service;
        _ptaNotificationManager = PtaNotificationManager.getInstance();
        _ptaNotificationManager.init(service);
        _ptaMediaManager = PtaMediaManager.getInstance();
    }

    public void displayNotification(String title, String message) {
        _ptaNotificationManager.displayNotification(title, message);
    }
    
    @Override
    public void playMedia(String url) {
        _ptaMediaManager.play(url);
    }
}
