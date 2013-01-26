package haja.pta.common.cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Read user input from System.in and hand it over to the configured
 * {@link ICommandRunner}.
 * 
 * @author Harald Jagenteufel
 * 
 */
public class CliInputReader {

	private BufferedReader _inputReader;
	private ICommandRunner _commandRunner;

	/**
	 * @param commandRunner
	 *            {@link ICommandRunner} to use for executing commands entered
	 *            by the user.
	 */
	public CliInputReader(ICommandRunner commandRunner) {
		_commandRunner = commandRunner;
		_inputReader = new BufferedReader(new InputStreamReader(System.in));
	}

	/**
	 * reads user input, a line at a time, until eof is reached or an
	 * {@link IOException} is thrown.
	 */
	public void startReadingFromCLI() {
		while(true) {
			String userInput = null;

			try {
				userInput = _inputReader.readLine();
			} catch(IOException e) {
				// TODO run some command instead / throw exc
				return;
			}
			if(userInput == null) {
				_commandRunner.reachedEOF();
				return;
			}

			try {
				_commandRunner.runCommand(new CommandFromString(userInput));
			} catch(IllegalArgumentException ex) {
			}
		}
	}


	/**
	 * method only for testing purposes.
	 */
	public void setBufferedReader(BufferedReader reader) {
		_inputReader = reader;
	}
	
	public void close() {
	    System.out.println("Closing reader");
	    try {
            _inputReader.close();
        } catch(IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
}
