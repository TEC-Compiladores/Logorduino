package logic;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ClassGenerator implements ConstantsLogic {


	public static void generate(boolean pDebug) {
		if (pDebug) System.out.println(CLASS_GENERATOR + GENER_GENERATING_FILES);
		String archLexico = LEX_FILE_PATH;
		String archSintactico = CUP_FILE_PATH;

		String[] alexico = { archLexico };
		String[] asintactico = { "-nowarn", "-nosummary", "-parser", "SintacticParser",
				archSintactico };
		jflex.Main.main(alexico);
		try {
			java_cup.Main.main(asintactico);
		} catch (Exception ex) {
			Logger.getLogger(Core.class.getName()).log(Level.SEVERE, null, ex);
		}
		boolean mvAS = moveFile(GENER_CUP_CLASS_NAME, pDebug);
		boolean mvSym = moveFile("sym.java", pDebug);
		if (mvAS && mvSym) {
			if (pDebug) System.out.println(CLASS_GENERATOR + "Se movieron los archivos a src");
		}
	}



	public static boolean moveFile(String pFileName, boolean pDebug) {
		boolean flag = false;
		File file = new File(pFileName);
		if (file.exists()) {
			if (pDebug) System.out.println(CLASS_GENERATOR + GENER_MOVE_FILE + file);
			String newDir = PROJECT_SRC_PATH + SLASH + PROJECT_INTERPRETER_PACKAGE + SLASH
					+ file.getName();
			File archViejo = new File(newDir);
			archViejo.delete();
			if (file.renameTo(new File(newDir))) {
				System.out.println(CLASS_GENERATOR + GENER_GENERATED_FILE + pFileName);
				flag = true;
			}
			else {
				System.out.println(CLASS_GENERATOR + GENER_NOT_GENERATED_FILE + pFileName);
			}

		}
		else {
			System.out.println("*** Codigo no existente ***");
		}
		return flag;
	}

}
