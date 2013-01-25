package haja.pta.common.communication.commands.server;

import haja.pta.common.communication.IConnectionHandler;
import haja.pta.common.communication.infrastructure.IServerInfrastructure;


public class InfoMessageCommand implements IServerCommand {

    private static final long serialVersionUID = 9124605697841883429L;
    private String _msg;

    public InfoMessageCommand(String message) {
        _msg = message;
    }

    @Override
    public void execute(IServerInfrastructure server, IConnectionHandler handler) {
        server.displayMessage(_msg);
    }

}
