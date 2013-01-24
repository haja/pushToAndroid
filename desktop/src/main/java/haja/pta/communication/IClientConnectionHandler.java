/**
 * 
 */
package haja.pta.communication;

import java.io.IOException;

import haja.pta.common.dto.IAmAlive;


/**
 * @author Harald Jagenteufel
 *
 */
public interface IClientConnectionHandler<RequestT, ResponseT> {

    ResponseT read() throws ClassNotFoundException, IOException;

    void write(RequestT request) throws IOException;

}
