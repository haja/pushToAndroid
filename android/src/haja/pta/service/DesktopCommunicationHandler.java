package haja.pta.service;

import haja.pta.common.dto.IAmAlive;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import android.util.Log;


/**
 * @author Harald Jagenteufel
 * 
 */
public class DesktopCommunicationHandler implements Runnable {

    private static final String TAG = "DesktopCommunicationHandler";
    private Socket _socket;
    private boolean _running = true;

    public DesktopCommunicationHandler(Socket socket) {
        _socket = socket;
    }

    @Override
    public void run() {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(
                    _socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(
                    _socket.getInputStream());
            outputStream.writeObject(new IAmAlive("android"));
            outputStream.flush();

            while(_running) {
                IAmAlive desktopAlive = (IAmAlive) inputStream.readObject();
                Log.i(TAG, desktopAlive.getMessage());
            }
            
        } catch(IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                _socket.close();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }

}
