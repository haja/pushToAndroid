package haja.pta.desktop.cli.commands;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import haja.pta.common.cli.ICommandCallback;
import haja.pta.common.communication.commands.client.MediaPlaybackCommand;
import haja.pta.desktop.communication.IClientConnectionHandler;
import haja.pta.desktop.communication.IPhoneCommunicationManagement;


public class MediaPlaybackCliCommand implements ICommandCallback {

    private static final String sf_cmd = "play";
    private static final int sf_argCount = 2;
    @Autowired
    private IPhoneCommunicationManagement _commManagement;

    @Override
    public void call(String... args) {
        try {
            IClientConnectionHandler client = _commManagement
                    .getClient(args[0]);
            if(client == null) {
                System.out.println("user " + args[0] + " not found");
                return;
            }
            client.write(new MediaPlaybackCommand(args[1]));
        } catch(IOException e) {
            e.printStackTrace();
        }
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
