package haja.pta.common.communication;

import java.io.IOException;


public interface IGenericStream<RequestT, ResponseT> {

    public void write(RequestT request) throws IOException;

    /**
     * @throws IOException
     * @throws UncheckedCommunicationException
     */
    public ResponseT read() throws IOException;

    public void close() throws IOException;
}