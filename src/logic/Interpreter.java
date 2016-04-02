package logic;

import java.util.HashMap;
import java.util.Map;

import logic.server.Arduino;


public class Interpreter {

	private Map<String, Number> _variables;
	private Arduino _arduino;



	public Interpreter(Arduino pArduino, boolean pDebug) {

		_variables = new HashMap<String, Number>();
		_arduino = pArduino;
	}



	/**
	 * Método que permite crear las variables ingresadas por el usuario Se
	 * almacena dentro de un Map el nombre de la variable como clave, y su valor
	 * como un objeto que hereda de la clase Number
	 * 
	 * @param pVar
	 *            Nombre de la variable
	 * @param pValue
	 *            Valor de la variable
	 */
	public void insertVar(String pVar, Number pValue) {
		_variables.put(pVar, pValue);

	}



	/**
	 * Método que permite obtener el valor de una variable
	 * 
	 * @param pVar
	 *            Nombre de la variable
	 * @return Valor de la variable
	 */
	public Number getVarValue(String pVar) {
		return _variables.get(pVar);
	}





}
