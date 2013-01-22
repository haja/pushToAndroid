/**
 * 
 */
package haja.pta.communication;

import haja.pta.util.Config;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author Harald Jagenteufel
 *
 */
public class ConnectionListenerTcp implements IConnectionListener {

    private boolean _keepRunning = true;
    @Autowired
    private Config _config;

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(_config.getInt("desktop_port"));
            
            while(_keepRunning) {
                Socket clientSocket = serverSocket.accept();
                new Thread(new ClientSetupHandler(clientSocket)).start();
            }
            
            serverSocket.close();
        } catch(IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
