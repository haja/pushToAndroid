/**
 * 
 */
package haja.pta.common.communication;


/**
 * @author Harald Jagenteufel
 *
 */
public class UncheckedCommunicationException extends RuntimeException {

    public UncheckedCommunicationException(Exception e) {
        super(e);
    }

    private static final long serialVersionUID = 2271578717964556771L;

}
