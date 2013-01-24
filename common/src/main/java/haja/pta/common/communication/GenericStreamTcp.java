package haja.pta.common.communication;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author Harald Jagenteufel
 * 
 */
public class GenericStreamTcp<RequestT, ResponseT> implements
        IGenericStream<RequestT, ResponseT> {

    private ObjectOutputStream _oos;
    private ObjectInputStream _ois;
    private Socket _socket;

    public GenericStreamTcp(InetAddress host, int port) throws IOException {
        this(new Socket(host, port));
    }

    public GenericStreamTcp(Socket socket) throws IOException {
        _socket = socket;
        _oos = new ObjectOutputStream(_socket.getOutputStream());
        _ois = new ObjectInputStream(
                _socket.getInputStream());
    }

    /*
     * (non-Javadoc)
     * 
     * @see haja.pta.communication.IGenericStream#close()
     */
    @Override
    public void close() throws IOException {
        _socket.close();
    }

    /*
     * (non-Javadoc)
     * 
     * @see haja.pta.communication.IGenericStream#write(RequestT)
     */
    @Override
    public void write(RequestT request) throws IOException {
        _oos.writeObject(request);
        _oos.flush();
    }

    /*
     * (non-Javadoc)
     * 
     * @see haja.pta.communication.IGenericStream#read()
     */
    @Override
    public ResponseT read() throws IOException {
        try {
            return (ResponseT) _ois.readObject();
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
            throw new UncheckedCommunicationException(e);
        }
    }

}
