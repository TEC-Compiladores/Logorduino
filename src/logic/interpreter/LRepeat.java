package logic.interpreter;



public class LRepeat {

	private LProgram _program;
	private Object _parameter;
	private int _times;



	public LRepeat(Object pTimes, LProgram pProgram) {
		_program = pProgram;
		_parameter = pTimes;
	}



	public void prepare() {
		if (_parameter instanceof String) {
			Number param = Interpreter._variables.get(((String) _parameter));
			if (param != null) {
				_times = (Integer) param;
			}
			else {
				// ERROR
			}
		}
		else {
			_times = (int) _parameter;
		}

		_program.prepare();
	}



	public void execute() {
		this.prepare();

		for (int i = 0; i < _times; i++) {
			_program.execute();
		}
	}

}
