package logic;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;


public interface ConstantsLogic {


	// ######### CLASS SINTACTIC PARSER #########

	// Debug
	public static final String SIN_FINISH_PARSE_TXT = "Se terminó de analizar el archivo: ";
	public static final String SIN_FINISH_PARSE_ENTRY = "Se terminó de analizar la entrada";


	// ######### CLASS CLASSGENERATOR #########

	//
	public static final String CLASS_GENERATOR = "GENERATOR >> ";
	public static final String GENER_FLEX_FILENAME = "lexico.flex";
	public static final String GENER_CUP_FILENAME = "sintactico.cup";
	public static final String GENER_FLEX_CLASS_NAME = "LexicalParser.java";
	public static final String GENER_CUP_CLASS_NAME = "SintacticParser.java";

	public static final String SLASH = File.separator;
	public static final Path RELATIVE_PATH = Paths.get("");
	public static final String PROJECT_PATH = RELATIVE_PATH.toAbsolutePath().toString();
	public static final String PROJECT_SRC_PATH = PROJECT_PATH + SLASH + "src";
	public static final String PROJECT_LOGIC_PACKAGE = "logic";
	public static final String LEX_FILE_PATH = PROJECT_SRC_PATH + SLASH + "logic" + SLASH
			+ GENER_FLEX_FILENAME;
	public static final String CUP_FILE_PATH = PROJECT_SRC_PATH + SLASH + "logic" + SLASH
			+ GENER_CUP_FILENAME;

	// Debug
	public static final String GENER_MOVE_FILE = "Moviendo el archivo: ";
	public static final String GENER_GENERATED_FILE = "Se generó el archivo: ";
	public static final String GENER_NOT_GENERATED_FILE = "No logró generar el archivo: ";
	public static final String GENER_GENERATING_FILES = "******** Generando los parsers ********";



}
