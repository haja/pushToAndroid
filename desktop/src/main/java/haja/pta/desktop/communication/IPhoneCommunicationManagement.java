/**
 * 
 */
package haja.pta.desktop.communication;

import haja.pta.common.communication.infrastructure.IServerInfrastructure;


/**
 * @author Harald Jagenteufel
 *
 */
public interface IPhoneCommunicationManagement extends IServerInfrastructure {

    public void startListening();

    public void addClient(String user, IClientConnectionHandler client);

    public IClientConnectionHandler getClient(String user);

}
