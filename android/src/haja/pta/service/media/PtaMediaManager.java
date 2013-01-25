package haja.pta.service.media;

import java.io.IOException;

import android.media.AudioManager;
import android.media.MediaPlayer;


public class PtaMediaManager {

    private static PtaMediaManager s_instance = new PtaMediaManager();
    private MediaPlayer _mediaPlayer;
    
    private PtaMediaManager() {
        _mediaPlayer = new MediaPlayer();
        _mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);        
    }

    public static PtaMediaManager getInstance() {
        return s_instance ;
    }

    public void play(String url) {
        try {
            _mediaPlayer.setDataSource(url);
            _mediaPlayer.prepare();
            _mediaPlayer.start();
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

}
