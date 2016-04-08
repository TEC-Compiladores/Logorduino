package facade;

import gui.LogoShellWindow;
import gui.Mainwindow;
import logic.Core;



public class Facade {

	private Core _core;
	private Mainwindow _mainWindow;
	private LogoShellWindow _shell;



	public Facade(Core pCore) {
		_core = pCore;
	}



	public void initGUI() {
		_mainWindow = new Mainwindow(this);
	}



	public void parseInput(String pCommand) {
		_core.parseInput(pCommand);
	}



	public void setShell(LogoShellWindow pShell) {
		_shell = pShell;
	}



	public void showError(String pError) {
		_shell.showError(pError);
	}



	public void createProcedure(String pName) {
		_shell.create(pName);
	}



	public void disconnect() {
		_core.disconnect();
	}



	public boolean makeConnection() {
		return _core.makeConnection();
	}

}
