package haja.pta.desktop.cli.commands;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import haja.pta.common.cli.ICommandCallback;
import haja.pta.desktop.communication.IClientConnectionHandler;
import haja.pta.desktop.communication.IPhoneCommunicationManagement;


public abstract class AbstractUserCliCommand implements ICommandCallback {

    @Autowired
    private IPhoneCommunicationManagement _commManagement;

    /**
     * args: user message
     */
    @Override
    public void call(String... args) {
        try {
            IClientConnectionHandler client = _commManagement
                    .getClient(args[0]);
            if(client == null) {
                System.out.println("user " + args[0] + " not found");
                return;
            }
            userCall(client, args);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    protected abstract void userCall(IClientConnectionHandler client, String[] args) throws IOException;
}
