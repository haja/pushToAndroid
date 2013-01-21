package haja.pta.communication;

import java.net.Socket;


/**
 * @author Harald Jagenteufel
 *
 */
public class ClientHandler implements Runnable {

    private Socket _socket;

    public ClientHandler(Socket clientSocket) {
        _socket = clientSocket;
    }

    /* (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        // TODO Auto-generated method stub

    }

}
