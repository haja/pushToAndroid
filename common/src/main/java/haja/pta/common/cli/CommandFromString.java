package haja.pta.common.cli;

import java.util.ArrayList;

/**
 * Splits user input into it's command and arguments.
 * 
 * @author Harald Jagenteufel
 * 
 */
public class CommandFromString {

	private String _cmd;
	private ArrayList<String> _args = new ArrayList<String>();

	/**
	 * @throws IllegalArgumentException
	 *             if input doesn't contain a command
	 */
	public CommandFromString(String input) {
		String[] cmdArray = input.split("( )+");
		if(cmdArray.length < 1) {
			throw new IllegalArgumentException(
			        "Input doesn't contain a command.");
		}
		_cmd = cmdArray[0];

		for(int i = 1; i < cmdArray.length; i++) {
			_args.add(cmdArray[i]);
		}
	}

	/**
	 * @return the command
	 */
	public String cmd() {
		return _cmd;
	}

	/**
	 * @param position
	 *            position of the command (starts with 0)
	 * @return argument at position
	 */
	public String arg(int position) {
		return _args.get(position);
	}

	/**
	 * @return all arguments as an array
	 */
	public String[] args() {
		return _args.toArray(new String[_args.size()]);
	}

	/**
	 * @return count of arguments
	 */
	public int argCount() {
		return _args.size();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_args == null) ? 0 : _args.hashCode());
		result = prime * result + ((_cmd == null) ? 0 : _cmd.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		CommandFromString other = (CommandFromString) obj;
		if(_args == null) {
			if(other._args != null)
				return false;
		} else if(!_args.equals(other._args))
			return false;
		if(_cmd == null) {
			if(other._cmd != null)
				return false;
		} else if(!_cmd.equals(other._cmd))
			return false;
		return true;
	}

}