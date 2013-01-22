package haja.pta.communication;

import java.net.Socket;

import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author Harald Jagenteufel
 *
 */
public class ClientSetupHandler implements Runnable {

    private Socket _socket;
    @Autowired
    private IPhoneCommunicationManagement _phoneCommManagement;

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
    }

}
