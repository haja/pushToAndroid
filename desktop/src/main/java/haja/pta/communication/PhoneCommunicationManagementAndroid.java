/**
 * 
 */
package haja.pta.communication;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author Harald Jagenteufel
 *
 */
public class PhoneCommunicationManagementAndroid implements
        IPhoneCommunicationManagement {

    private static PhoneCommunicationManagementAndroid s_instance = new PhoneCommunicationManagementAndroid();
    @Autowired
    private IConnectionListener _connectionListener;
    private List<IClientConnectionHandler> _clients = Collections.synchronizedList(new LinkedList<IClientConnectionHandler>());

    private PhoneCommunicationManagementAndroid() {
    }
    
    public static PhoneCommunicationManagementAndroid getInstance() {
        return s_instance;
    }
    
    public void init() {
        
    }
    
    @Override
    public void startListening() {
        new Thread(_connectionListener).start();
    }

    @Override
    public void addClient(IClientConnectionHandler client) {
        _clients.add(client);
    }

}
