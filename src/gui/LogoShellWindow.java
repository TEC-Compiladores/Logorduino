package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import facade.Facade;

/**
 * Clase principal, esta es una ventana en la que se ingresa los diversos
 * comando del lenguaje LOGO
 * , ademas se pueden realizar acciones como abrir editor o salir del programa.
 * 
 * @author geraldtec
 *
 */
public class LogoShellWindow extends JFrame implements ActionListener, ListSelectionListener,
		Constants, WindowListener {

	private JLabel _lConcejos;
	private JTextField _tInstrucctions;
	private JLabel _lComandsHistory;
	private JButton _bRun;
	private JButton _bCargar;
	private JButton _bEditor;
	private JButton _bExit;
	private JPanel _contentPanel;
	private String[] List = { " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ",
			" ", " ", " ", " ", " ", " " };
	private int[] indices;
	private JList lista;
	private Object[] seleccion;
	private String newHElement;
	private JScrollPane barraDesplazamiento;
	private String _comando = "";
	private MainEditor me;
	private Facade _facade;
	private JFileChooser jfc;
	private String nameFile;



	/**
	 * Metodo constructor de la clase.
	 */
	public LogoShellWindow(Facade pFacade) {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(332, 600);
		_facade = pFacade;
		this.setResizable(false);
		this._contentPanel = new JPanel(null);
		this.setContentPane(this._contentPanel);
		this.setTitle(" LOGORDUINO COMANDS ");
		this.setLocationRelativeTo(null);
		this.GUIElements();
		this.setVisible(true);
		// JOptionPane.showMessageDialog(this,"No existe historial aun.","Sin historial",JOptionPane.WARNING_MESSAGE);

	}



	/**
	 * Metodo donde se crean elemenetos, se pocisionan y se le dan diversas
	 * caracteristicas.
	 */
	public void GUIElements() {

		this.addWindowListener(this);
		// this.me = new MainEditor();
		// this.me.setVisible(false);
		this.lista = new JList(List);
		this.barraDesplazamiento = new JScrollPane(lista);
		this._lConcejos = new JLabel("Escriba las Instrucciones: ");
		this._tInstrucctions = new JTextField();
		this._bRun = new JButton("Correr");
		_bCargar = new JButton("Cargar");
		this._bExit = new JButton("Volver");
		this._bEditor = new JButton("Editar");
		this._lComandsHistory = new JLabel("Encuentre abajo el historial de comandos: ");

		this._bRun.addActionListener(this);
		this._bCargar.addActionListener(this);
		this._bExit.addActionListener(this);
		this._bEditor.addActionListener(this);
		this.lista.addListSelectionListener(this);

		this.lista.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		this.seleccion = lista.getSelectedValues();
		this.indices = lista.getSelectedIndices();

		this._bRun.setBackground(Color.decode("#fd330d"));
		this._bRun.setForeground(Color.WHITE);
		this._bCargar.setBackground(Color.decode("#fd330d"));
		this._bCargar.setForeground(Color.WHITE);
		this._bExit.setBackground(Color.decode("#fd330d"));
		this._bExit.setForeground(Color.WHITE);
		this._bEditor.setBackground(Color.decode("#fd330d"));
		this._bEditor.setForeground(Color.WHITE);
		this.getContentPane().setBackground(Color.WHITE);
		this._lConcejos.setForeground(Color.decode("#fd330d"));
		this._tInstrucctions.setForeground(Color.decode("#fd330d"));

		this.barraDesplazamiento.setBounds(10, 160, 300, 280);
		this._lConcejos.setBounds(10, 10, 300, 20);
		this._tInstrucctions.setBounds(10, 40, 300, 20);
		this._bRun.setBounds(10, 70, 300, 20);
		this._bCargar.setBounds(10, 460, 300, 20);
		this._bExit.setBounds(10, 540, 300, 20);
		this._bEditor.setBounds(10, 500, 300, 20);
		this._lComandsHistory.setBounds(10, 130, 300, 20);

		this._contentPanel.setBackground(Color.WHITE);
		this._lComandsHistory.setForeground(Color.blue);
		this._lComandsHistory.setBackground(Color.lightGray);

		this.getContentPane().add(_lConcejos);
		this.getContentPane().add(_bCargar);
		this.getContentPane().add(_tInstrucctions);
		this.getContentPane().add(_bRun);
		this.getContentPane().add(_lComandsHistory);
		this.getContentPane().add(barraDesplazamiento);
		this.getContentPane().add(_bEditor);
		this.getContentPane().add(_bExit);
	}



	/**
	 * Metodo simple para hacer un llenado de la lista y actualizacion de ella
	 */
	public void AddElementToList() {

		List[19] = List[18];
		List[18] = List[17];
		List[17] = List[16];
		List[16] = List[15];
		List[15] = List[14];
		List[14] = List[13];
		List[13] = List[12];
		List[12] = List[11];
		List[11] = List[10];
		List[10] = List[9];
		List[9] = List[8];
		List[8] = List[7];
		List[7] = List[6];
		List[6] = List[5];
		List[5] = List[4];
		List[4] = List[3];
		List[3] = List[2];
		List[2] = List[1];
		List[1] = List[0];
		List[0] = newHElement;
	}



	/**
	 * Metodo que ejecuta el archivo que corresponde a un metodo creado.
	 * 
	 * @throws IOException
	 */
	public void ejecutarEditor(String pProcedure) {

		try {
			Runtime.getRuntime().exec("kate temp/" + (pProcedure) + ".txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}



	/**
	 * Metodo para crear un archivo con el nombre del metodo escojido.
	 */
	public void create(String pProcedure) {

		try {
			File archivo = new File("temp/" + (pProcedure) + ".txt");
			BufferedWriter bw;
			if (archivo.exists()) {
				JOptionPane.showMessageDialog(this, "Metodo ya definido", "Duplicacion de metodos",
						JOptionPane.ERROR_MESSAGE);
			}
			else {
				LISTA.addElement(pProcedure);
				bw = new BufferedWriter(new FileWriter(archivo));
				bw.write(_comando.substring(0) + "\nfin");
				System.out.println(_comando.substring(0) + "\nfin");
				bw.close();
			}
			// bw.close();
		} catch (IOException ex) {
			System.out.println(ex);
		}


		ejecutarEditor(pProcedure);
	}



	/**
	 * Metodo para borrar un archivo con el nombre del metodo escojido.
	 */
	public void borrar(String pValor) {

		File archivo = new File("temp/" + pValor + ".txt");
		BufferedWriter bw;
		if (archivo.exists()) {
			archivo.delete();
		}
		else {
			JOptionPane.showMessageDialog(this, "Metodo no existe", "Implementacion erronea",
					JOptionPane.ERROR_MESSAGE);
		}
	}



	/**
	 * Metodo que toma cada nombre de metodos existentes en la lista y los usa
	 * para ir cerrando todo aquel
	 * archivo que poseea ese nombre.
	 */
	public boolean borrador() {

		int len = LISTA.getSize();
		int i = 0;
		while (i < len) {
			borrar(LISTA.get(i).toString());
			System.out.println(LISTA.get(i).toString());
			i += 1;
		}
		return true;
	}



	/**
	 * 
	 */
	public void filechooser() {
		JFileChooser jfc = new JFileChooser();
		jfc.showOpenDialog(this);
		File abre = jfc.getSelectedFile();
		if (abre != null) {
			nameFile = jfc.getName();

			StringBuilder builder = new StringBuilder();
			try {
				Scanner scanner = new Scanner(abre);

				while (scanner.hasNextLine()) {
					builder.append(scanner.nextLine());
				}
				scanner.close();
				System.out.println(builder.toString());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			_facade.parseInput(builder.toString());
		}


	}



	/**
	 * Metodo que lee las acciones por medio de la lista
	 * 
	 * @param e
	 */
	public void valueChanged(ListSelectionEvent e) {

		if (e.getValueIsAdjusting() == false) {
			if (List.length != 0) {
				this._tInstrucctions.setText(lista.getSelectedValue().toString());
			}
			else {
				JOptionPane.showMessageDialog(this, "No hay historial aun.", "Sin historial",
						JOptionPane.WARNING_MESSAGE);
			}

		}
	}



	/**
	 * Metodo para verificar si se esta pidiendo crear un nuevo metodo.
	 */
	// public boolean isPara() {
	//
	// if (_comando.startsWith("para ")) {
	// create();
	// try {
	// ejecutarEditor();
	// return true;
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	// return false;
	// }



	public void showError(String pMessage) {
		JOptionPane.showMessageDialog(this, pMessage, "Error", JOptionPane.ERROR_MESSAGE);
	}



	/**
	 * Metodo que lee las acciones por medio de los botones
	 * 
	 * @param e
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == _bRun) {
			if (List.length != 0) {
				newHElement = this._tInstrucctions.getText();
				_comando = this._tInstrucctions.getText();

				AddElementToList();
				_bRun.setVisible(false);
				this.update(this.getGraphics());
				_facade.parseInput(_tInstrucctions.getText());
				_bRun.setVisible(true);



			}
			else {
				JOptionPane.showMessageDialog(this, "No hay historial aun.", "Sin historial",
						JOptionPane.WARNING_MESSAGE);
			}
			this.getContentPane().repaint();
		}
		if (e.getSource() == _bExit) {
			new Mainwindow(_facade);
			this.dispose();
			// this.setVisible(false);
		}
		if (e.getSource() == _bEditor) {
			// me.setVisible(true);
			this.me = new MainEditor();
			this.me.setVisible(true);
		}
		if (e.getSource() == _bCargar) {
			filechooser();

		}

	}



	/**
	 * Metodo propio de la extencion WindowEvent
	 */
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}



	/**
	 * Metodo propio de la extencion WindowEvent
	 */
	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		borrador();
		_facade.disconnect();
	}



	/**
	 * Metodo propio de la extencion WindowEvent
	 */
	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
	}



	/**
	 * Metodo propio de la extencion WindowEvent
	 */
	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}



	/**
	 * Metodo propio de la extencion WindowEvent
	 */
	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}



	/**
	 * Metodo propio de la extencion WindowEvent
	 */
	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}



	/**
	 * Metodo propio de la extencion WindowEvent
	 */
	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

}
