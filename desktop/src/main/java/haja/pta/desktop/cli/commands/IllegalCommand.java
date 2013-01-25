package haja.pta.desktop.cli.commands;

import haja.pta.common.cli.ICommandCallback;


public class IllegalCommand implements ICommandCallback {

    @Override
    public void call(String... args) {
        System.out.println(args[0]);
    }

    @Override
    public String getCmd() {
        return null;
    }

    @Override
    public int argCount() {
        return 0;
    }

    @Override
    public void helpMessage() {
    }

}
