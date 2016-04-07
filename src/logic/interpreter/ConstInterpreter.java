package logic.interpreter;

import logic.ConstantsLogic;


public interface ConstInterpreter {


	// Debug
	public static final String SIN_FINISH_PARSE_TXT = "Se terminó de analizar el archivo: ";
	public static final String SIN_FINISH_PARSE_ENTRY = "Se terminó de analizar la entrada";


	// Arduino constants
	public static final int CMD_AV = 0;
	public static final int CMD_RE = 1;
	public static final int CMD_GD = 2;
	public static final int CMD_GI = 3;
	public static final int CMD_POS = 4;
	public static final int CMD_RUMBO = 5;
	public static final int CMD_CENTER = 6;
	public static final int CMD_SL = 7;
	public static final int CMD_BL = 8;
	public static final int CMD_HAZ = 9;

	public static final String SEPARATION_CHAR = "#";

	//
	public static final String TEMP_FOLDER = ConstantsLogic.PROJECT_PATH + ConstantsLogic.SLASH
			+ "temp" + ConstantsLogic.SLASH;


}
