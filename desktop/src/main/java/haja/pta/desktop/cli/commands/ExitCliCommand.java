package haja.pta.desktop.cli.commands;

import org.springframework.beans.factory.annotation.Autowired;

import haja.pta.common.cli.ICommandCallback;
import haja.pta.desktop.cli.DesktopCli;
import haja.pta.desktop.communication.IPhoneCommunicationManagement;


public class ExitCliCommand implements ICommandCallback {
    
    private static final String sf_cmd = "exit";
    private static final int sf_argCount = 0;
    
    @Autowired
    private IPhoneCommunicationManagement _commManagement;
    @Autowired
    private DesktopCli _cli;
    
    @Override
    public void call(String... args) {
       _cli.getReader().close();
       _commManagement.shutdown();
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
       System.out.println(sf_cmd);
    }

}
