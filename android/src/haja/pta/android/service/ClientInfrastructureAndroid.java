/**
 * 
 */
package haja.pta.android.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import haja.pta.R;
import haja.pta.android.activity.PtaAndroidActivity;
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

    public void displayNotification() {

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
        builder.setContentTitle("desk message")
                .setContentText("message")
                .setContentIntent(intent)
                .setSmallIcon(R.drawable.notification_icon);

        _notificationManager.notify(COMMUNICATION_NOTIFICATION_ID,
                builder.getNotification());
    }
}
