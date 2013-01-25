package haja.pta.common.communication.commands.client;

import haja.pta.common.communication.infrastructure.IClientInfrastructure;


public class NotificationCommand implements IClientCommand {

    private static final long serialVersionUID = -968318645341310984L;
    private String _message;

    public NotificationCommand(String message) {
        _message = message;
    }

    @Override
    public void execute(IClientInfrastructure client) {
        client.displayNotification("title", _message);
    }

}
