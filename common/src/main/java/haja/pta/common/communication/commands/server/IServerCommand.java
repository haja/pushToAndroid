package haja.pta.common.communication.commands.server;

import java.io.Serializable;

import haja.pta.common.communication.IConnectionHandler;
import haja.pta.common.communication.infrastructure.IServerInfrastructure;

public interface IServerCommand extends Serializable {

    void execute(IServerInfrastructure server, IConnectionHandler handler);

}
