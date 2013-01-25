package haja.pta.desktop.cli.commands;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import haja.pta.common.cli.ICommandCallback;
import haja.pta.common.communication.commands.client.NotificationCommand;
import haja.pta.desktop.communication.IPhoneCommunicationManagement;


public class NotificationCliCommand implements ICommandCallback {

    private static final String sf_cmd = "notify";
    private static final int sf_argsCount = 2;
    @Autowired
    private IPhoneCommunicationManagement _commManagement;

    /**
     * args: user message
     */
    @Override
    public void call(String... args) {
        try {
            _commManagement.getClient(args[0]).write(new NotificationCommand(args[1]));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

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
