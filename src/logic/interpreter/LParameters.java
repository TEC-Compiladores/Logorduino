package logic.interpreter;


public class LParameters {


	private LParameters _params;
	private Object _param;





	public LParameters(LParameters pParams, Object pParam) {
		_params = pParams;
		_param = pParam;
	}



	public LParameters(Object pParam) {
		_param = pParam;
	}


	//
	//
	// private void obtainValues() {
	//
	// for (int i = 0; i < _parametersIn.size(); i++) {
	//
	// if (_parametersIn.get(i) instanceof Number)
	// _parameters[i] = (Number) _parametersIn.get(i);
	// else {
	// Number param = Interpreter._variables.get(((String)
	// _parametersIn.get(i)));
	// _parameters[i] = param;
	// }
	// }
	// }



	// public Object getParameters(boolean pFlag) {
	// if (pFlag)
	// return this._parametersIn;
	// else {
	// this.obtainValues();
	// return this._parameters;
	// }
	// }

}
