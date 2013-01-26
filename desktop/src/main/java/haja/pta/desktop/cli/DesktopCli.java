package haja.pta.desktop.cli;

import java.util.List;

import haja.pta.common.cli.CliInputReader;
import haja.pta.common.cli.CommandRunnerImpl;
import haja.pta.common.cli.ICommandCallback;
import haja.pta.desktop.cli.commands.EofReachedCommand;
import haja.pta.desktop.cli.commands.IllegalCommand;


public class DesktopCli {

    private CliInputReader _inputReader;
    private CommandRunnerImpl _commandRunner;
    private List<ICommandCallback> _callbacks;

    public DesktopCli() {

    }

    /**
     * Setup logic for the CLI.
     */
    public void init() {
        ICommandCallback illegalCommandCallback = new IllegalCommand();
        ICommandCallback eofReachedCallback = new EofReachedCommand();
        _commandRunner = new CommandRunnerImpl(
                illegalCommandCallback, eofReachedCallback);
        _inputReader = new CliInputReader(_commandRunner);
        
        for(ICommandCallback callback : _callbacks) {
            _commandRunner.registerCommand(callback.getCmd(), callback);
        }
    }

    /**
     * @return the configured {@link CliInputReader}.
     */
    public CliInputReader getReader() {
        return _inputReader;
    }

    public void setCallbacks(List<ICommandCallback> callbacks) {
        _callbacks = callbacks;
    }
}
