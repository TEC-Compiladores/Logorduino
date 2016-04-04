package logic.interpreter;

/**
 * 
 * @author Juan Pablo Brenes
 *         3/4/2016
 * 
 *         Clase que representa el no terminal "orderParcial" de la gramatica
 *         del interprete
 *
 */

public class LPartialOrder {


	private LOrder _order;



	/**
	 * Constructor de la clase
	 * 
	 * @param pOrder
	 *            Clase que representa el no terminal "orden"
	 */
	public LPartialOrder(LOrder pOrder) {
		_order = pOrder;
	}



	/***
	 * Método que prepara los comandos de la clase LOrder para ser ejecutados
	 */
	public void prepare() {
		_order.prepare();
	}



	/**
	 * Método que ejecuta los comandos de la clase LOrder
	 * 
	 */
	public void execute() {
		_order.execute();
	}

}
