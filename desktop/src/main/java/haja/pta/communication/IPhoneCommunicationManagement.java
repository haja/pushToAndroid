/**
 * 
 */
package haja.pta.communication;


/**
 * @author Harald Jagenteufel
 *
 */
public interface IPhoneCommunicationManagement {

    public void startListening();

    public void addClient(IClientConnectionHandler client);

}
