package logic;

import logic.arduino.Arduino;
import logic.interpreter.Interpreter;
import facade.Facade;

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
	private Facade _facade;





	public Core(boolean pGenerate, boolean pDebug) {
		_debug = pDebug;
		_arduino = new Arduino(_debug);
		_facade = new Facade(this);
		_interpreter = new Interpreter(_arduino, this, _facade, pGenerate, _debug);
		_facade.initGUI();
	}



	public void parseInput(String pCommand) {
		_interpreter.parseEntry(pCommand);
	}



	/**
	 * Método que permite desconectar la conexión bluetooth entre la computadora
	 * y el arduino
	 */
	public void disconnect() {
		_arduino.disconnect();
	}



	/**
	 * Método que realiza la conexión con el arduino
	 */
	public boolean makeConnection() {
		boolean flag = _arduino.connect();
		return flag;
	}



}
