/**
 * 
 */
package haja.pta.communication;

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

}
