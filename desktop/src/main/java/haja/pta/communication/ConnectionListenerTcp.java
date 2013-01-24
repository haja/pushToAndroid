/**
 * 
 */
package haja.pta.communication;

import haja.pta.common.communication.commands.client.IClientCommand;
import haja.pta.common.communication.commands.server.IServerCommand;
import haja.pta.common.communication.tcp.GenericStreamTcp;
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
            ServerSocket serverSocket = new ServerSocket(_config.getInt("desktop_port"));
            
            while(_keepRunning) {
                Socket clientSocket = serverSocket.accept();
                _log.info("new client connection from " + clientSocket.getInetAddress());
                IClientConnectionHandler clientHandler = new ClientConnectionHandler(new GenericStreamTcp<IClientCommand, IServerCommand>(clientSocket));
                _beanFactory.autowireBean(clientHandler);
            }
            
            serverSocket.close();
        } catch(IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
