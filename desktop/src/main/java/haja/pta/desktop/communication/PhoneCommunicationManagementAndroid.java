/**
 * 
 */
package haja.pta.desktop.communication;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
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
    private Map<String, IClientConnectionHandler> _clients = Collections.synchronizedMap(new HashMap<String, IClientConnectionHandler>());
    private Logger _log = Logger.getLogger(PhoneCommunicationManagementAndroid.class);

    private PhoneCommunicationManagementAndroid() {
    }
    
    public static PhoneCommunicationManagementAndroid getInstance() {
        return s_instance;
    }
    
    public void init() {
        
    }
    
    @Override
    public void startListening() {
        _log.info("startListening");
        new Thread(_connectionListener).start();
    }

    @Override
    public void addClient(String user, IClientConnectionHandler client) {
        _log.info("adding user " + user);
        _clients.put(user, client);
    }

    @Override
    public IClientConnectionHandler getClient(String user) {
        return _clients.get(user);
    }

    @Override
    public void displayMessage(String message) {
        _log.info(message);
    }
    
    @Override
    public void shutdown() {
        _log.info("comunicationManagemt shutting down");
        _connectionListener.close();
        for(IClientConnectionHandler client: _clients.values()) {
            client.close();
        }
    }
}
