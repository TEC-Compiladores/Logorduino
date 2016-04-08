package logic.interpreter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import logic.ClassGenerator;
import logic.Core;
import logic.arduino.Arduino;
import facade.Facade;

/**
 * 
 * @author Juan Pablo Brenes
 *         3/4/2016
 * 
 */

public class Interpreter implements ConstInterpreter {

	protected static Map<String, Number> _variables;
	private ArrayList<String> _procedures;
	protected static boolean _debug;
	protected static Facade _facade;

	private LexicalParser _lexical;
	private SintacticParser _sintactic;
	private static Arduino _arduino;
	private Core _core;



	public Interpreter(Arduino pArduino, Core pCore, Facade pFacade, boolean pGenerate,
			boolean pDebug) {
		if (pGenerate) ClassGenerator.generate(_debug);
		_debug = pDebug;
		_facade = pFacade;
		_variables = new HashMap<String, Number>();
		_procedures = new ArrayList<String>();
		_lexical = new LexicalParser();
		_sintactic = new SintacticParser(this, _lexical, _debug);
		_arduino = pArduino;
		_core = pCore;
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
		// _lexical.setReader(new StringReader(pEntry));
		_lexical.yyreset(new StringReader(pEntry));
		_sintactic.analizarEntrada();
	}



	/**
	 * Método que analiza el código de un procedimiento
	 * 
	 * @param pProcedure
	 *            Nombre del procedimiento
	 * @return LProgram que representa el "programa" a ejecutar
	 */
	public LProgram parseProcedure(String pProcedure) {
		String procedure = this.loadProcedure(pProcedure);
		LProgram program = null;

		if (procedure == null) {
			return null;
		}
		else {
			LexicalParser lexical = new LexicalParser();
			SintacticParser sintactic = new SintacticParser(this, lexical, true);
			lexical.yyreset(new StringReader(procedure));
			program = sintactic.analizarProc();
		}

		return program;


	}



	/**
	 * Método que carga el código de un procedimiento creado
	 * 
	 * @param pProcedure
	 *            Nombre del procedimiento
	 * @return String con las lineas de codigo del procedimiento
	 */
	public String loadProcedure(String pProcedure) {
		File file = new File(TEMP_FOLDER + pProcedure + ".txt");
		StringBuilder builder = null;
		try {
			Scanner scanner = new Scanner(file);
			scanner.nextLine();
			builder = new StringBuilder();
			while (scanner.hasNextLine()) {
				if (!scanner.hasNext("fin")) {
					builder.append(scanner.nextLine());
				}
				else
					scanner.nextLine();
			}

		} catch (FileNotFoundException e) {
			return null;
		}


		return builder.toString();
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



	/**
	 * Método que indica a la GUI que debe mostrar un mensaje de error
	 * 
	 * @param pMessage
	 *            Mnesaje a mostrar
	 */
	protected static void displayError(String pMessage) {
		_facade.showError(pMessage);
	}



	public void createProcedure(String pProcedure) {
		_facade.createProcedure(pProcedure);

	}





}
