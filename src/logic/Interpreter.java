package logic;

import java.util.HashMap;
import java.util.Map;


public class Interpreter {

	private Map<String, Number> _variables;



	public Interpreter(boolean pDebug) {

		_variables = new HashMap<String, Number>();
	}



	/**
	 * Método para almacenar el nombre y valor de una variable creada
	 * 
	 * @param pVar
	 *            Nombre de la variable
	 * @param pValue
	 *            Valor de la variable
	 */
	public void insertVar(String pVar, Number pValue) {
		_variables.put(pVar, pValue);

		System.out.println(pVar + ": " + pValue);
	}



	/**
	 * Método que obtener el valor de una variable
	 * 
	 * @param pVar
	 *            Nombre de la variable
	 * @return Valor de la variable
	 */
	public Number getVarValue(String pVar) {
		return _variables.get(pVar);
	}





}
