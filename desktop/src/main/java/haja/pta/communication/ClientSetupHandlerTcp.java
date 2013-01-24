package haja.pta.communication;

import haja.pta.common.communication.GenericStreamTcp;
import haja.pta.common.dto.IAmAlive;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author Harald Jagenteufel
 *
 */
public class ClientSetupHandlerTcp implements Runnable {

    private Socket _socket;
    @Autowired
    private IPhoneCommunicationManagement _phoneCommManagement;
    private Logger _log = Logger.getLogger(ClientSetupHandlerTcp.class);

    public ClientSetupHandlerTcp(Socket clientSocket) {
        _socket = clientSocket;
    }

    /* (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        // receive alive and send back
        try {
            IClientConnectionHandler<IAmAlive, IAmAlive> handler = new ClientConnectionHandler<IAmAlive, IAmAlive>(new GenericStreamTcp<IAmAlive, IAmAlive>(_socket));
            _phoneCommManagement.addClient(handler);
            
            IAmAlive clientAlive = handler.read();
            _log.info("client alive: " + clientAlive.getMessage());
            handler.write(new IAmAlive("desktop"));
            _log.info("written to client");
            
            for(int i = 0; i < 3; i++) {
                handler.write(new IAmAlive("message " + i));
            }
            
            _socket.close();
            _log.info("socket to client closed");
        } catch(IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch(ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

}
