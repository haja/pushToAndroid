package haja.pta.common.dto;

import java.io.Serializable;


/**
 * @author Harald Jagenteufel
 *
 */
public class IAmAlive implements Serializable {

    private static final long serialVersionUID = -9012247136793174582L;
    
    private String _message;

    public IAmAlive(String message) {
        _message = message;
    }

    public String getMessage() {
        return _message;
    }

}
