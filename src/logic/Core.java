package logic;

import logic.interpreter.Interpreter;
import logic.server.Arduino;

/**
 * 
 * @author Juan Pablo Brenes
 *         3/4/2016
 * 
 *         Clase principal del programa
 *
 */

public class Core {

	private boolean _debug;
	private Interpreter _interpreter;
	private Arduino _arduino;




	public Core(boolean pGenerate, boolean pDebug) {
		_debug = pDebug;
		_arduino = new Arduino(_debug);
		_interpreter = new Interpreter(_arduino, this, pGenerate, _debug);
	}


}
