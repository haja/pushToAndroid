/**
 * 
 */
package haja.pta.communication;


/**
 * @author Harald Jagenteufel
 *
 */
public class PhoneCommunicationManagementAndroid implements
        IPhoneCommunicationManagement {

    private static PhoneCommunicationManagementAndroid s_instance = new PhoneCommunicationManagementAndroid();

    private PhoneCommunicationManagementAndroid() {
    }
    
    public static PhoneCommunicationManagementAndroid getInstance() {
        return s_instance;
    }
    
    public void init() {
        
    }
    
    @Override
    public void startListening() {
        // TODO Auto-generated method stub
        
    }

}
