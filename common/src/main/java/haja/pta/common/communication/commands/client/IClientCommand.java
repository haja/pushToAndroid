package haja.pta.common.communication.commands.client;

import java.io.Serializable;

import haja.pta.common.communication.infrastructure.IClientInfrastructure;


public interface IClientCommand extends Serializable {

    public void execute(IClientInfrastructure client);

}
