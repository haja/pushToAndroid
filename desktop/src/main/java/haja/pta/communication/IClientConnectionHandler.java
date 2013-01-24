/**
 * 
 */
package haja.pta.communication;

import haja.pta.common.communication.IConnectionHandler;
import haja.pta.common.communication.commands.client.IClientCommand;

import java.io.IOException;

/**
 * @author Harald Jagenteufel
 *
 */
public interface IClientConnectionHandler extends IConnectionHandler {

    void write(IClientCommand request) throws IOException;

}
