package haja.pta.notification;

import haja.pta.PtaAndroidActivity;
import haja.pta.R;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;


public class PtaNotificationManager {

    private static PtaNotificationManager s_instance = new PtaNotificationManager();
    private NotificationManager _notificationManager;
    private Service s_service;

    private static final int COMMUNICATION_NOTIFICATION_ID = 0;

    private PtaNotificationManager() {
        
    }
    
    public void init(Service service) {
        s_service = service;
        _notificationManager =
                (NotificationManager) service.getSystemService(Context.NOTIFICATION_SERVICE);
    }
    
    public static PtaNotificationManager getInstance() {
        return s_instance ;
    }

    public void displayNotification(String title, String message) {
        Intent resultIntent = new Intent(
                s_service,
                PtaAndroidActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder
                .from(s_service);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent intent = stackBuilder.getPendingIntent(0,
                PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(
                s_service);
        builder.setDefaults(Notification.DEFAULT_ALL)
                .setContentTitle(title)
                .setContentText(message)
                .setTicker(message)
                .setContentIntent(intent)
                .setSmallIcon(R.drawable.notification_icon);

        _notificationManager.notify(COMMUNICATION_NOTIFICATION_ID,
                builder.getNotification());
    }


}
