package logic.interpreter;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import logic.ClassGenerator;
import logic.server.Arduino;


public class Interpreter {

	protected static Map<String, Number> _variables;

	private boolean _debug;
	private LexicalParser _lexical;
	private SintacticParser _sintactic;
	private Arduino _arduino;



	public Interpreter(Arduino pArduino, boolean pGenerate, boolean pDebug) {
		if (pGenerate) ClassGenerator.generate(_debug);
		_debug = pDebug;
		_variables = new HashMap<String, Number>();
		_lexical = new LexicalParser();
		_sintactic = new SintacticParser(this, _lexical, _debug);
		_arduino = pArduino;

		this.parseTxt("test.txt");

	}



	public void parseTxt(String pFileName) {
		try {
			_lexical.setReader(new FileReader(pFileName));
			_sintactic.analizarTxt("test.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

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
	public static void insertVar(String pVar, Number pValue) {
		_variables.put(pVar, pValue);

	}



	/**
	 * Método que permite obtener el valor de una variable
	 * 
	 * @param pVar
	 *            Nombre de la variable
	 * @return Valor de la variable
	 */
	public static Number getVarValue(String pVar) {
		return _variables.get(pVar);
	}





}
