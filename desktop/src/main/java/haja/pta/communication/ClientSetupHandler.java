package haja.pta.communication;

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
public class ClientSetupHandler implements Runnable {

    private Socket _socket;
    @Autowired
    private IPhoneCommunicationManagement _phoneCommManagement;
    private Logger _log = Logger.getLogger(ClientSetupHandler.class);

    public ClientSetupHandler(Socket clientSocket) {
        _socket = clientSocket;
    }

    /* (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        IClientConnectionHandler handler = new ClientConnectionHandler(_socket);
        _phoneCommManagement.addClient(handler);
        
        // receive alive and send back
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(_socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(_socket.getInputStream());
            IAmAlive clientAlive = (IAmAlive) inputStream.readObject();
            _log.info("client alive: " + clientAlive.getMessage());
            outputStream.writeObject(new IAmAlive("desktop"));
            outputStream.flush();
            _log.info("written to client");
            
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
