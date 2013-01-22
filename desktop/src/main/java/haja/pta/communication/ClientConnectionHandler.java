package haja.pta.communication;

import java.net.Socket;


/**
 * @author Harald Jagenteufel
 * 
 */
public class ClientConnectionHandler implements IClientConnectionHandler {

    private Socket _socket;

    public ClientConnectionHandler(Socket socket) {
        _socket = socket;
    }

}
