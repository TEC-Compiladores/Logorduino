package logic.interpreter;


public class LProgram {

	private LProgram _internalProgram;
	private LPartialOrder _partial;



	public LProgram(LProgram pInternal, LPartialOrder pPartial) {
		_internalProgram = pInternal;
		_partial = pPartial;
	}



	public LProgram(LPartialOrder pPartial) {
		_partial = pPartial;
		_internalProgram = null;
	}



	public void prepare() {
		if (_internalProgram == null)
			_partial.prepare();
		else {
			_internalProgram.prepare();
			_partial.prepare();
		}
	}



	public void execute() {
		if (_internalProgram == null)
			_partial.execute();
		else {
			_internalProgram.execute();
			_partial.execute();
		}
	}

}
