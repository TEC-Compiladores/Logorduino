package logic.interpreter;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import logic.ClassGenerator;
import logic.Core;
import logic.server.Arduino;

/**
 * 
 * @author Juan Pablo Brenes
 *         3/4/2016
 * 
 */

public class Interpreter {

	protected static Map<String, Number> _variables;
	protected static boolean _debug;

	private LexicalParser _lexical;
	private SintacticParser _sintactic;
	private static Arduino _arduino;
	private Core _core;



	public Interpreter(Arduino pArduino, Core pCore, boolean pGenerate, boolean pDebug) {
		if (pGenerate) ClassGenerator.generate(_debug);
		_debug = pDebug;
		_variables = new HashMap<String, Number>();
		_lexical = new LexicalParser();
		_sintactic = new SintacticParser(this, _lexical, _debug);
		_arduino = pArduino;
		_core = pCore;

		this.parseEntry("repite  [av 90 repite 2 [sl]]");

	}



	/**
	 * Método que permite analizar lexica y gramaticalmente(usando las clases
	 * generadas por JFlex y CUP) un archivo de texto con comandos
	 * 
	 * @param pFileName
	 *            Nombre o ruta donde se encuentra el archivo
	 */
	public void parseTxt(String pFileName) {
		try {
			_lexical.setReader(new FileReader(pFileName));
			_sintactic.analizarTxt("test.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			if (_debug) System.err.println();
		}
	}



	/**
	 * Método que permite analizar lexica y gramaticalmente(usando las clases
	 * generadas por JFlex y CUP) una entrada del usuario por medio de la línea
	 * de comandos de la interfaz gráfica
	 * 
	 * @param pEntry
	 *            Comando ingresado por el usuario en la linea de comandos de la
	 *            interfaz gráfica
	 */
	public void parseEntry(String pEntry) {
		_lexical.setReader(new StringReader(pEntry));
		_sintactic.analizarEntrada();
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
	protected static void insertVar(String pVar, Number pValue) {
		_variables.put(pVar, pValue);

	}



	/**
	 * Método que permite obtener el valor de una variable
	 * 
	 * @param pVar
	 *            Nombre de la variable
	 * @return Valor de la variable
	 */
	protected static Number getVarValue(String pVar) {
		return _variables.get(pVar);
	}



	/**
	 * Método que permite agregar mensajes a la cola de mensajes que deben ser
	 * enviado al arduino
	 * 
	 * @param pMessage
	 *            Mensaje que debe ser enviado
	 */
	protected static void messageForArduino(String pMessage) {
		_arduino.addToQueue(pMessage);
	}



	/**
	 * Método que indica a la clase "Arduino" que debe comenzar a enviar los
	 * mensajes
	 * 
	 * Los mensajes son enviados cierto intervalo de tiempo
	 */
	public void sendToArduino() {
		_arduino.sendMessages();
	}





}
