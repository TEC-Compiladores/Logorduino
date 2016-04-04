package logic.interpreter;

/**
 * 
 * @author Juan Pablo Brenes
 *         3/4/2016
 * 
 *         Clase que representa al comando "repite"
 *
 */

public class LRepeat {

	private LProgram _program;
	private Object _parameter;
	private int _times;



	/**
	 * Constructor de la clase
	 * 
	 * @param pTimes
	 *            Número de veces a ejecutar el programa parámetro
	 * @param pProgram
	 *            Lista de ordenes que se deben ejecutar
	 */
	public LRepeat(Object pTimes, LProgram pProgram) {
		_program = pProgram;
		_parameter = pTimes;
	}



	/**
	 * Método que prepara el "repite" para ser ejecutado
	 * Obtiene el número de veces que se debe ejecutar
	 */
	public void prepare() {
		if (_parameter instanceof String) {
			Number param = Interpreter._variables.get(((String) _parameter));
			if (param != null) {
				_times = (Integer) param;
			}
			else {
				System.err.println("Variable del repite no existe");// ERROR
			}
		}
		else {
			_times = (int) _parameter;
		}

		_program.prepare();
	}



	/**
	 * Método que ejecuta las instrucciones del repite el número de veces
	 * especificadas por el usuario
	 */
	public void execute() {
		this.prepare();

		for (int i = 0; i < _times; i++) {
			_program.execute();
		}
	}


}
