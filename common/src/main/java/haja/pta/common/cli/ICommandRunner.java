package haja.pta.common.cli;

/**
 * Interface for running commands from a CLI.
 * 
 * @author Harald Jagenteufel
 * 
 */
public interface ICommandRunner {

	/**
	 * will be called with given command.
	 */
	void runCommand(CommandFromString cmd);

	/**
	 * will be called when EOF is reached.
	 */
	void reachedEOF();
}
