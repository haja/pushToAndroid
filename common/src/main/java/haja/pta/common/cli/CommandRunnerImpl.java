package haja.pta.common.cli;

import java.util.HashMap;
import java.util.Map;

/**
 * Implements {@link ICommandRunner} interface using a Map to store links
 * between commands and callbacks.
 * 
 * @author Harald Jagenteufel
 * 
 */
public class CommandRunnerImpl implements ICommandRunner {

	private Map<String, ICommandCallback> _commands = new HashMap<String, ICommandCallback>();
	private ICommandCallback _illegalCommandCallback;
	private ICommandCallback _eofReachedCallback;


	public CommandRunnerImpl(ICommandCallback illegalCommandCallback,
	        ICommandCallback eofReachedCallback) {
		_illegalCommandCallback = illegalCommandCallback;
		_eofReachedCallback = eofReachedCallback;
	}


	@Override
	public void runCommand(CommandFromString cmd) {
		ICommandCallback callback = _commands.get(cmd.cmd());
		if(callback == null) {
			_illegalCommandCallback.call(cmd.cmd());
			return;
		}
		if(cmd.argCount() != callback.argCount()) {
		    _illegalCommandCallback.call(cmd.cmd() + " invalid arguments count");
		}
		callback.call(cmd.args());
	}

	@Override
	public void reachedEOF() {
		_eofReachedCallback.call();
	}

	/**
	 * register a callback for a command
	 */
	public void registerCommand(String cmd, ICommandCallback callback) {
		_commands.put(cmd, callback);
	}

}
