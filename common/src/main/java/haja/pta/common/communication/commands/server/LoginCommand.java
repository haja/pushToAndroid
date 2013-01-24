package haja.pta.common.communication.commands.server;

import haja.pta.common.communication.IConnectionHandler;
import haja.pta.common.communication.infrastructure.IServerInfrastructure;


public class LoginCommand implements IServerCommand {

    private static final long serialVersionUID = 954554561699665711L;
    private String _user;
    
    public LoginCommand(String user) {
        _user = user;
    }

    @Override
    public void execute(IServerInfrastructure server, IConnectionHandler handler) {
        handler.login(_user);
    }
}
