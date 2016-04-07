package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
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

public class MainEditor extends JFrame implements ActionListener, ListSelectionListener, Constants,
		WindowListener {

	private JPanel contentPanel;
	private JTextField metodoaCrear;
	private JList JLista;
	private JScrollPane JpanelDeslizable;
	private JButton _Ok;
	private JButton _cancel;
	private JLabel _concejo;
	private int[] indices;
	private Object[] seleccion;



	/**
	 * Constructor de la clase.
	 */
	public MainEditor() {
		// TODO Auto-generated constructor stub
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(332, 400);
		this.setResizable(false);
		this.contentPanel = new JPanel(null);
		this.setContentPane(this.contentPanel);
		this.setTitle(" LOGORDUINO EDITMODE ");
		this.setLocationRelativeTo(null);
		this.addWindowListener(this);
		GUIElements();
	}



	/**
	 * Metodo para cargar todos los elementos (GUI) de la clase.
	 */
	public void GUIElements() {

		this.setVisible(false);

		this.metodoaCrear = new JTextField();
		this.contentPanel = new JPanel();
		this._concejo = new JLabel("Cree un metodo o seleccione");
		this._Ok = new JButton("OK");
		this._cancel = new JButton("Cancel");
		this.JLista = new JList(LISTA);
		this.JpanelDeslizable = new JScrollPane(JLista);

		this._Ok.addActionListener(this);
		this._cancel.addActionListener(this);
		this.JLista.addListSelectionListener(this);

		this.JLista.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		this.seleccion = JLista.getSelectedValues();
		this.indices = JLista.getSelectedIndices();

		this.metodoaCrear.setBounds(10, 10, 300, 20);
		this._concejo.setBounds(10, 40, 300, 40);
		this.JpanelDeslizable.setBounds(10, 90, 300, 200);
		this._Ok.setBounds(10, 300, 300, 20);
		this._cancel.setBounds(10, 330, 300, 20);

		this.getContentPane().add(contentPanel);
		this.getContentPane().add(metodoaCrear);
		this.getContentPane().add(_concejo);
		this.getContentPane().add(JpanelDeslizable);
		this.getContentPane().add(_Ok);
		this.getContentPane().add(_cancel);

		this.setVisible(true);
	}



	/**
	 * Metodo que busca el nombre de un metodo en la lista contenedora de
	 * metodos.
	 */
	public boolean verificadorLista(String pValor) {

		int len = LISTA.getSize();
		int i = 0;
		while (i < len) {
			if (pValor.equals(LISTA.get(i))) {
				return false;
			}
			else {
				i += 1;
			}
		}
		return true;
	}



	/**
	 * Metodo que ejecuta el archivo que corresponde a un metodo creado.
	 */
	public void ejecutarEditor() throws IOException {

		Runtime.getRuntime().exec("kate temp/" + metodoaCrear.getText().toString() + ".txt");
	}



	/**
	 * Metodo para crear un archivo con el nombre del metodo escojido.
	 */
	public void create() {

		try {
			File archivo = new File("temp/" + this.metodoaCrear.getText().toString() + ".txt");
			BufferedWriter bw;
			if (archivo.exists()) {
				JOptionPane.showMessageDialog(this, "Metodo ya definido", "Duplicacion de metodos",
						JOptionPane.ERROR_MESSAGE);
			}
			else {
				LISTA.addElement(metodoaCrear.getText().toString());
				bw = new BufferedWriter(new FileWriter(archivo));
				bw.write("para" + " " + this.metodoaCrear.getText().toString() + "\nfin");
				bw.close();
			}
		} catch (IOException ex) {
			System.out.println(ex);
		}
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
	 * Metodo que lee las acciones por medio de la lista
	 * 
	 * @param e
	 */
	public void valueChanged(ListSelectionEvent e) {

		if (e.getValueIsAdjusting()) {
			this.metodoaCrear.setText(JLista.getSelectedValue().toString());

		}
	}



	/**
	 * Metodo que lee las acciones por medio de los botones
	 * 
	 * @param e
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == _Ok) {
			create();
			// LISTA.addElement(metodoaCrear.getText().toString());
			try {
				ejecutarEditor();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.setVisible(false);
		}
		if (e.getSource() == _cancel) {
			this.setVisible(false);
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
