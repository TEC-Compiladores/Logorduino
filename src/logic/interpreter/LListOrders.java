package logic.interpreter;

import java.util.ArrayList;

/**
 * @author Juan Pablo Brenes 2/4/2016
 * 
 *         Clase que representa un comando/orden de LOGO
 * 
 *         (avanza, ponpos, giraderecha, giraizquierda, centro, bajalapiz,
 *         subelapiz, ponrumbo,retrocede)
 *
 */

public class LListOrders implements ConstInterpreter {

	private int _command;
	private Number[] _parametersValues;
	private ArrayList<Object> _parametersIn;



	/**
	 * Constructor de la clase
	 * 
	 * @param pCommand
	 *            Constante que indica el comando a ejecutar por el arduino
	 * @param pParams
	 *            Lista de parametros para el comando (puede estar vacía)
	 */
	public LListOrders(int pCommand, ArrayList<Object> pParams) {
		_command = pCommand;
		_parametersIn = pParams;
	}



	/**
	 * Método que se ejecuta cuando la orden ingresada por el usuario es el
	 * comando "haz" que permite crear una variable
	 * 
	 * Se validan los siguiente casos:
	 * ** Si la variable a crear no existe:
	 * -----Si el valor es un número se inserta el par nombre/valor
	 * -----Si el valor es una variable se comprueba si existe y se copia su
	 * -----valor a la nueva variable, sino es un error
	 * 
	 * ** Si la variable a crear existe:
	 * -----Se comprueban los dos casos anteriores con la diferencia que la
	 * -----variable se sobrescribe
	 */
	public void commandHaz() {
		String varName = (String) _parametersIn.get(0);
		Object varValue = _parametersIn.get(1);


		// Si la variable no existe
		if (Interpreter._variables.get(varName) == null) {
			if (varValue instanceof Number) {// Si el valor es un numero
				Interpreter._variables.put(varName, ((Number) varValue));
			}
			else {// Si el valor es el valor de otra variable
				Number referValue = Interpreter._variables.get(((String) varValue));
				if (referValue == null)// Si la variable del valor no existe
					System.err.println("Error: Variable " + ((String) varValue) + " no existe");
				else
					// Si la variable del valor existe
					Interpreter._variables.put(varName, referValue);
			}
		}

		else { // Si la variable existe se reemplaza su valor
			if (varValue instanceof Number) // Si el valor es un número
				Interpreter._variables.replace(varName, ((Number) varValue));
			else {// Si el valor es el valor de otra variable
				Number referValue = Interpreter._variables.get(((String) varValue));
				if (referValue == null) // Si la variable del valor no existe
					System.err.println("Error: Variable " + ((String) varValue) + " no existe");
				else
					// Si la variable del valor existe
					Interpreter._variables.replace(varName, referValue);
			}

		}
	}



	/**
	 * Método que prepara el comando a ejecutar
	 * Obtiene los parámetros (si necesita) necesarios para que el comando se
	 * ejecute correctamente
	 */
	public void prepare() {
		if (_parametersIn != null) {

			if (_command == CMD_HAZ) {
				this.commandHaz();
				return;
			}

			_parametersValues = new Number[_parametersIn.size()];
			for (int i = 0; i < _parametersIn.size(); i++) {

				if (_parametersIn.get(i) instanceof Number)
					_parametersValues[i] = (Number) _parametersIn.get(i);

				else {
					Number param = Interpreter._variables.get(((String) _parametersIn.get(i)));
					if (param != null)
						_parametersValues[i] = param;
					else {
						System.err.println("Variable: \"" + (String) _parametersIn.get(i)
								+ "\" no definida");
						System.exit(1); // //////////////////////////////////////////////////////////////////CAMBIAR
					}
				}
			}
		}
	}



	/**
	 * Método que crea un mensaje (string) con la constante del comando a
	 * ejecutar, junto con sus respectivos parámetros
	 * 
	 * El mensaje creado se agrega a la cola de mensajes que deben ser enviados
	 * al arduino para que este los ejecute
	 */
	public void execute() {
		StringBuilder message = new StringBuilder(String.valueOf(_command));

		if (_command != CMD_HAZ) {

			if (_command != CMD_BL && _command != CMD_SL && _command != CMD_CENTER) {
				if (_command == CMD_POS) {
					message.append(SEPARATION_CHAR);
					message.append(_parametersValues[0]);
					message.append(SEPARATION_CHAR);
					message.append(_parametersValues[1]);
				}
				else {
					message.append(SEPARATION_CHAR);
					message.append(_parametersValues[0]);
				}
			}

			Interpreter.messageForArduino(message.toString());
		}

		if (Interpreter._debug) this.printDebug();



	}



	/**
	 * Imprime información de debug
	 */
	private void printDebug() {
		switch (_command) {
			case CMD_AV:
				System.out.println("EJECUTE: Avanzar " + _parametersValues[0]);
				break;
			case CMD_BL:
				System.out.println("EJECUTE: Bajar Lapiz");
				break;
			case CMD_CENTER:
				System.out.println("EJECUTE: Centro");
				break;
			case CMD_GD:
				System.out.println("EJECUTE: Gira derecha");
				break;
			case CMD_GI:
				System.out.println("EJECUTE: Gira izquierda");
				break;
			case CMD_HAZ:
				System.out.println("EJECUTE: Crear variable");
				break;
			case CMD_POS:
				System.out.println("EJECUTE: Ponpos");
				break;
			case CMD_RE:
				System.out.println("EJECUTE: Retrocede");
				break;
			case CMD_RUMBO:
				System.out.println("EJECUTE: Ponrumbo");
				break;
			case CMD_SL:
				System.out.println("EJECUTE: Sube Lapiz");
				break;
		}
	}
}
