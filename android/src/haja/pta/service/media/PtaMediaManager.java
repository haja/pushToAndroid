package haja.pta.service.media;

import java.io.IOException;

import android.app.Service;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiManager.WifiLock;
import android.os.PowerManager;
import android.util.Log;


public class PtaMediaManager {

    public enum STATUS {
        PLAY, STOP
    }

    private static final String TAG = "PtaMediaManager";

    private static PtaMediaManager s_instance = new PtaMediaManager();
    private MediaPlayer _mediaPlayer;
    private Service _service;
    private WifiLock _wifiLock;
    private STATUS _playbackStatus = STATUS.STOP;

    private PtaMediaManager() {
    }

    public static PtaMediaManager getInstance() {
        return s_instance;
    }

    public void init(Service service) {
        _service = service;
    }

    public PtaMediaManager.STATUS getPlaybackStatus() {
        return _playbackStatus;
    }

    public void play(String url) {
        if(_playbackStatus == STATUS.PLAY) {
            Log.w(TAG, "already playing");
            return;
        }
        try {
            _mediaPlayer = new MediaPlayer();
            _mediaPlayer.setWakeMode(_service.getApplicationContext(),
                    PowerManager.PARTIAL_WAKE_LOCK);
            _wifiLock = ((WifiManager) _service
                    .getSystemService(Context.WIFI_SERVICE))
                    .createWifiLock(WifiManager.WIFI_MODE_FULL, "mylock");

            _wifiLock.acquire();

            _mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            _mediaPlayer.setDataSource(url);
            _mediaPlayer.prepare();
            _mediaPlayer.start();
            _playbackStatus = STATUS.PLAY;
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

    public void stop() {
        if(_playbackStatus == STATUS.STOP) {
            Log.w(TAG, "already stopped");
            return;
        }
        _mediaPlayer.stop();
        _mediaPlayer.release();
        _mediaPlayer = null;
        _wifiLock.release();
        _playbackStatus = STATUS.STOP;
    }

}
