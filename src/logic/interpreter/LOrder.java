package logic.interpreter;

/**
 * 
 * @author Juan Pablo Brenes
 *         3/4/2016
 * 
 *         Clase que representa el no terminal "orden" de la gramatica del
 *         interprete
 *
 */

public class LOrder {

	private LRepeat _repeat;
	private LListOrders _list;



	/**
	 * Constructor de la clase
	 * 
	 * @param pRepeat
	 *            Clase que representa el comando "repite"
	 */
	public LOrder(LRepeat pRepeat) {
		_repeat = pRepeat;
		_list = null;
	}



	/**
	 * Constructor de la clase
	 * 
	 * @param pList
	 *            Clase que representa el no terminal "listaOrdenes"
	 */
	public LOrder(LListOrders pList) {
		_list = pList;
		_repeat = null;
	}



	/**
	 * Método que prepara los comandos de la clase LRepeat o LListOrders (según
	 * como halla sido instanciada la clase) para ser ejecutados
	 */
	public void prepare() {
		if (_repeat == null)
			_list.prepare();
		else
			_repeat.prepare();
	}



	/**
	 * Método que ejecuta los comando de la clase LRepeat o LListOrders (según
	 * como halla sido instanciada la clase)
	 */
	public void execute() {
		if (_repeat == null)
			_list.execute();
		else
			_repeat.execute();
	}

}
