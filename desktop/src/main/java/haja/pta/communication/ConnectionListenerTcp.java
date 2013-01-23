/**
 * 
 */
package haja.pta.communication;

import haja.pta.util.Config;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;


/**
 * @author Harald Jagenteufel
 *
 */
public class ConnectionListenerTcp implements IConnectionListener {

    private boolean _keepRunning = true;
    @Autowired
    private Config _config;
    private Logger _log = Logger.getLogger(ConnectionListenerTcp.class);
    
    @Autowired
    private AutowireCapableBeanFactory _beanFactory;

    @Override
    public void run() {
        try {
            _log.info("config: " + _config);
            ServerSocket serverSocket = new ServerSocket(_config.getInt("desktop_port"));
            
            while(_keepRunning) {
                Socket clientSocket = serverSocket.accept();
                ClientSetupHandler clientSetupHandler = new ClientSetupHandler(clientSocket);
                _beanFactory.autowireBean(clientSetupHandler);
                new Thread(clientSetupHandler).start();
            }
            
            serverSocket.close();
        } catch(IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
