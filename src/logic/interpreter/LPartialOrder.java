package logic.interpreter;


public class LPartialOrder {


	private LOrder _order;



	public LPartialOrder(LOrder pOrder) {
		_order = pOrder;
	}



	public void prepare() {
		_order.prepare();
	}



	public void execute() {
		_order.execute();
	}

}
