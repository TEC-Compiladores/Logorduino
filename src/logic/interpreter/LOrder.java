package logic.interpreter;


public class LOrder {

	private LRepeat _repeat;
	private LListOrders _list;



	public LOrder(LRepeat pRepeat) {
		_repeat = pRepeat;
		_list = null;
	}



	public LOrder(LListOrders pList) {
		_list = pList;
		_repeat = null;
	}



	public void prepare() {
		if (_repeat == null)
			_list.prepare();
		else
			_repeat.prepare();
	}



	public void execute() {
		if (_repeat == null)
			_list.execute();
		else
			_repeat.execute();
	}

}
