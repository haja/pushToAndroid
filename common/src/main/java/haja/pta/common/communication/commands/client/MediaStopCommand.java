package haja.pta.common.communication.commands.client;

import haja.pta.common.communication.infrastructure.IClientInfrastructure;


public class MediaStopCommand implements IClientCommand {

    private static final long serialVersionUID = -185706718367569509L;

    @Override
    public void execute(IClientInfrastructure client) {
        client.stopMedia();
    }

}
