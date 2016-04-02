package logic.interpreter;

import java.util.ArrayList;

import logic.ConstantsLogic;

/**
 * @author Juan Pablo Brenes 2/4/2016
 * 
 *         Clase que representa un comando/orden de LOGO
 * 
 *         (avanza, ponpos, giraderecha, giraizquierda, centro, bajalapiz,
 *         subelapiz, ponrumbo,retrocede)
 *
 */

public class LListOrders implements ConstantsLogic {

	private int _command;
	// private LParameters _parameters;
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



	public void prepare() {

		// if (_command == CMD_HAZ) {
		// if (_parametersIn.get(1) instanceof String) {
		// Number value = Interpreter._variables.get((String)
		// _parametersIn.get(1));
		// if (value != null) {
		// _parametersIn.remove(1);
		// _parametersIn.add(1, value);
		// }
		// else {
		// // ERROR
		// }
		// }
		// }
		//
		// else
		// _parametersValues = (Number[]) _parameters.getParameters(false);
	}



	private void obtainValues() {
		if (_parametersIn != null) {
			_parametersValues = new Number[_parametersIn.size()];
			for (int i = 0; i < _parametersIn.size(); i++) {

				if (_parametersIn.get(i) instanceof Number)
					_parametersValues[i] = (Number) _parametersIn.get(i);
				else {
					Number param = Interpreter._variables.get(((String) _parametersIn.get(i)));
					if (param != null)
						_parametersValues[i] = param;
					else {
						if (_command != CMD_HAZ) {
							System.err.println("Variable: \"" + (String) _parametersIn.get(i)
									+ "\" no definida");
							System.exit(1);
						}
					}
				}
			}
		}
	}



	/**
	 * Método que crea un mensaje (string) con la constante del comando a
	 * ejecutar, junto con sus respectivos parametros
	 * 
	 * Este mensaje se envia al arduino para que se realize la acción
	 */
	public void execute() {
		this.obtainValues();
		StringBuilder message = new StringBuilder(String.valueOf(_command));

		if (_command == CMD_HAZ) {
			Interpreter._variables
					.put((String) _parametersIn.get(0), (Number) _parametersIn.get(1));
		}


		else if (_command != CMD_BL && _command != CMD_SL && _command != CMD_CENTER) {
			if (_command == CMD_POS) {
				message.append("#");
				message.append(_parametersValues[0]);
				message.append("#");
				message.append(_parametersValues[1]);
			}
			else {
				message.append("#");
				message.append(_parametersValues[0]);
			}

			// /ENVIAR MENSAJE AL ARDUINO
		}

		else {
			// ENVIAR MENSAJE
		}

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
