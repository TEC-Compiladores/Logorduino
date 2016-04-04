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



	/**
	 * Método que prepara el código ingresado por el usuario para ser ejecutado
	 * 
	 * Se llama al método "prepare" el cual, si es el caso, crea variables y
	 * obtiene valores de variables ya existentes
	 */
	public void prepare() {
		if (_internalProgram == null)
			_partial.prepare();
		else {
			_internalProgram.prepare();
			_partial.prepare();


		}
	}



	/**
	 * Método que ejecuta las ordenes ingresadas por el usuario
	 */
	public void execute() {
		if (_internalProgram == null)
			_partial.execute();
		else {
			_internalProgram.execute();
			_partial.execute();


		}
	}

}
