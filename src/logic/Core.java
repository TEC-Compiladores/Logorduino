package logic;

import logic.interpreter.Interpreter;
import logic.server.Arduino;



public class Core {

	private boolean _debug;
	private Interpreter _interpreter;
	private Arduino _arduino;




	public Core(boolean pGenerate, boolean pDebug) {
		_debug = pDebug;
		_arduino = new Arduino(_debug);
		_interpreter = new Interpreter(_arduino, pGenerate, _debug);

		// this.parseTxt("test.txt");
	}



	public Interpreter getInterpreter() {
		return _interpreter;
	}



	/**
	 * Método que indica a la clase Interpreter que se debe crear una nueva
	 * variable
	 * 
	 * @param pVar
	 *            Nombre de la variable
	 * @param pValue
	 *            Valor de la variable
	 */
	public void createVar(String pVar, Number pValue) {
		_interpreter.insertVar(pVar, pValue);
	}





	// public void test() {
	// Scanner input = new Scanner(System.in);
	// Scanner input2 = new Scanner(System.in);
	//
	// do {
	// System.out.println("Elija una opcion: ");
	// System.out.println("1) Generar");
	// System.out.println("2) Ejecutar");
	// System.out.println("3) Salir");
	// System.out.print("Opcion: ");
	// _running = input.nextInt();
	//
	// switch (_running) {
	// case 1:
	// this.generate();
	// break;
	// case 2:
	// String[] archivoPrueba = { "test.txt" };
	// // String ss = input2.nextLine() + "\n";
	// // System.out.println(ss);
	// // StringReader str = new StringReader(ss);
	// _analizer.analizarTxt(archivoPrueba);
	// System.out.println("Ejecutado!");
	// break;
	// case 3:
	// System.out.println("Adios!");
	// break;
	// }
	//
	// } while (_running != 3);
	// }
	//
	//
	//
	// private void generate() {
	// System.out.println("*** Generando ***");
	// String archLexico =
	// "/home/pablo/workspace/Logorduino/src/logic/lexico.flex";
	// String archSintactico =
	// "/home/pablo/workspace/Logorduino/src/logic/sintactico.cup";
	//
	// String[] alexico = { archLexico };
	// String[] asintactico = { "-parser", "AnalizadorSintactico",
	// archSintactico };
	// jflex.Main.main(alexico);
	// try {
	// java_cup.Main.main(asintactico);
	// } catch (Exception ex) {
	// Logger.getLogger(Core.class.getName()).log(Level.SEVERE, null, ex);
	// }
	// boolean mvAL = moveFile("AnalizadorLexico.java");
	// boolean mvAS = moveFile("AnalizadorSintactico.java");
	// boolean mvSym = moveFile("sym.java");
	// if (mvAL && mvAS && mvSym) {
	// System.exit(0);
	// }
	//
	// System.out.println("Generado!");
	// }
	//
	//
	//
	// private boolean moveFile(String pFileName) {
	// boolean flag = false;
	// File file = new File(pFileName);
	// if (file.exists()) {
	// System.out.println("\n*** Moviendo " + file + " \n***");
	// Path currentRelativePath = Paths.get("");
	// String newDir = currentRelativePath.toAbsolutePath().toString() +
	// File.separator
	// + "src" + File.separator + "logic" + File.separator + file.getName();
	// File archViejo = new File(newDir);
	// archViejo.delete();
	// if (file.renameTo(new File(newDir))) {
	// System.out.println("\n*** Generado " + pFileName + "***\n");
	// flag = true;
	// }
	// else {
	// System.out.println("\n*** No movido " + pFileName + " ***\n");
	// }
	//
	// }
	// else {
	// System.out.println("\n*** Codigo no existente ***\n");
	// }
	// return flag;
	// }

}
