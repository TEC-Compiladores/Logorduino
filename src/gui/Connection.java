package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;

import facade.Facade;

public class Connection extends JFrame implements ActionListener, Constants {

	private JLabel _lIP;
	private JLabel _lPORT;
	private JTextField _tfIP;
	private JTextField _tfPORT;
	private JButton _bMakeConecction;
	private JButton _bStart;
	private JPanel _contentPanel;
	private JPopupMenu _POPUP;
	private boolean prueba = false;
	private Facade _facade;
	private String _IP;
	private int _PORT;



	public Connection(Facade pFacade) {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setSize(332, 600);
		_facade = pFacade;
		this._contentPanel = new JPanel(null);
		this.setContentPane(this._contentPanel);
		GUIElements();
		this.setVisible(true);
	}



	public void GUIElements() {
		_lIP = new JLabel("Introduce una direccion IP");
		_lPORT = new JLabel("Introduce un Puerto");
		_tfIP = new JTextField();
		_tfPORT = new JTextField();
		_bMakeConecction = new JButton("Realizar Conexion");
		_bStart = new JButton("Iniciar");


		this._bMakeConecction.addActionListener(this);
		this._bStart.addActionListener(this);

		_lIP.setBounds(10, 10, 300, 20);
		_tfIP.setBounds(10, 50, 300, 20);
		_lPORT.setBounds(10, 90, 300, 20);
		_tfPORT.setBounds(10, 130, 300, 20);
		_bMakeConecction.setBounds(10, 450, 300, 30);
		_bStart.setBounds(10, 490, 300, 30);

		this.getContentPane().add(_lIP);
		this.getContentPane().add(_tfIP);
		this.getContentPane().add(_lPORT);
		this.getContentPane().add(_tfPORT);
		this.getContentPane().add(_bMakeConecction);
		this.getContentPane().add(_bStart);

	}



	/**
	 * Metodo para extraer los datos que se introduciran en la ventana de
	 * conexion.
	 */
	public void extractData() {
		this._IP = _tfIP.getText().toString();
		this._PORT = Integer.parseInt(_tfPORT.getText().toString());
		client.setIP(this._IP);
		client.set_PORT(this._PORT);
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if (e.getSource() == _bMakeConecction) {
			if (_tfIP.getText().toString().equals("") || _tfPORT.getText().toString().equals("")) {
				JOptionPane.showMessageDialog(this, "Debe ingresar una dirección IP correcta",
						"Connection Error", JOptionPane.ERROR_MESSAGE);
			}
			else {
				extractData();
				new Thread(client).start();
				JOptionPane.showMessageDialog(this, "Conexión exitosa");
			}
		}
		if (e.getSource() == _bStart) {
			if (!client.ifConnection()) {
				LogoShellWindow shell = new LogoShellWindow(_facade);
				_facade.setShell(shell);
				this.dispose();
				// this.setVisible(false);
			}
			else {
				JOptionPane.showMessageDialog(this, "Falló la conexión", "Connection Error",
						JOptionPane.ERROR_MESSAGE);
			}

		}
	}

}
