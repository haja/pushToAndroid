package haja.pta.desktop.cli.commands;

import haja.pta.common.communication.commands.client.MediaPlayCommand;
import haja.pta.desktop.communication.IClientConnectionHandler;

import java.io.IOException;


public class MediaPlayCliCommand extends AbstractUserCliCommand {

    private static final String sf_cmd = "play";
    private static final int sf_argCount = 2;
    
    @Override
    protected void userCall(IClientConnectionHandler client, String[] args)
            throws IOException {
        client.write(new MediaPlayCommand(args[1]));
    }

    @Override
    public String getCmd() {
        return sf_cmd;
    }

    @Override
    public int argCount() {
        return sf_argCount;
    }

    @Override
    public void helpMessage() {
        System.out.println(sf_cmd + " <user> <url>");
    }
}
