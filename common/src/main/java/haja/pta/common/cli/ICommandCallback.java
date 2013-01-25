package haja.pta.common.cli;


/**
 * Callback for CLI commands.
 * 
 * @author Harald Jagenteufel
 * 
 */
public interface ICommandCallback {

	/**
	 * do your method call
	 * 
	 * @param args
	 *            arguments given by cli
	 */
	public void call(String... args);

	/**
	 * @return the command
	 */
    public String getCmd();

    /**
     * @return count of arguments expected
     */
    public int argCount();

}
