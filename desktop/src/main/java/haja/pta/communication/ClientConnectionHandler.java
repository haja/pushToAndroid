package haja.pta.communication;

import haja.pta.common.communication.IGenericStream;

import java.io.IOException;


/**
 * @author Harald Jagenteufel
 * @param <RequestT>
 * @param <ResponseT>
 * 
 */
public class ClientConnectionHandler<RequestT, ResponseT> implements IClientConnectionHandler<RequestT, ResponseT> {

    private IGenericStream<RequestT, ResponseT> _stream;

    public ClientConnectionHandler(IGenericStream<RequestT,ResponseT> stream) {
        _stream = stream;
    }

    @Override
    public ResponseT read() throws ClassNotFoundException, IOException {
        return _stream.read();
    }

    @Override
    public void write(RequestT request) throws IOException {
        _stream.write(request);
    }

}
