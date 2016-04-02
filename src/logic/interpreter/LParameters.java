package logic.interpreter;

import java.util.ArrayList;

/**
 * 
 * @author Juan Pablo Brenes 2/4/2016
 * 
 *         Esta clase se encarga de manejar los parametros que el usuario
 *         ingresa, ya sea constantes o nombres de variables
 *
 */

public class LParameters {

	private ArrayList<Object> _parametersIn;
	private Number[] _parameters;



	/**
	 * Constructor de la clase
	 * 
	 * @param pParams
	 *            Lista de parametros ingresados por el usuario
	 */
	public LParameters(ArrayList<Object> pParams) {
		_parametersIn = pParams;
		_parameters = new Number[_parametersIn.size()];
	}



	/**
	 * Método que analiza las entradas como parametro del usuario
	 * 
	 * Si la entrada corresponde a un objeto del tipo Number, significa que el
	 * usuario ingreso un número (constante).
	 * 
	 * Si corresponde a un string, significa que la entrada del usuario es el
	 * nombre de una variable, por lo que se obtiene su valor.
	 * 
	 * Los valores de los parametros son almacenados en una arreglo para luego
	 * ser enviados al arduino
	 */
	private void obtainValues() {

		for (int i = 0; i < _parametersIn.size(); i++) {

			if (_parametersIn.get(i) instanceof Number)
				_parameters[i] = (Number) _parametersIn.get(i);
			else {
				Number param = Interpreter._variables.get(((String) _parametersIn.get(i)));
				_parameters[i] = param;
			}
		}
	}



	/**
	 * Método para obtener los parametros
	 * 
	 * @param pFlag
	 *            Indica si se deben retornar los parametros de entrada o los
	 *            valores de los parametros
	 * @return
	 */
	public Object getParameters(boolean pFlag) {
		if (pFlag)
			return this._parametersIn;
		else {
			this.obtainValues();
			return this._parameters;
		}
	}

}
