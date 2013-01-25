package haja.pta.desktop.cli.commands;

import haja.pta.common.cli.ICommandCallback;


public class EofReachedCommand implements ICommandCallback {

    @Override
    public void call(String... args) {
        System.out.println("reached EOF");
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
