package haja.pta.desktop.cli.commands;

import haja.pta.common.communication.commands.client.NotificationCommand;
import haja.pta.desktop.communication.IClientConnectionHandler;

import java.io.IOException;


public class NotificationCliCommand extends AbstractUserCliCommand {

    private static final String sf_cmd = "notify";
    private static final int sf_argsCount = 2;

    @Override
    protected void userCall(IClientConnectionHandler client, String[] args)
            throws IOException {
        client.write(new NotificationCommand(args[1]));
    }
    
    @Override
    public String getCmd() {
        return sf_cmd;
    }

    @Override
    public int argCount() {
        return sf_argsCount;
    }

    @Override
    public void helpMessage() {
        System.out.println(sf_cmd + " <user> <message>");
    }
}
