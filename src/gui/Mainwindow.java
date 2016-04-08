package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import facade.Facade;

/**
 * Clase que extiende de JFrame para representar la ventana principal del
 * programa.
 * 
 * @author geraldtec
 *
 */
public class Mainwindow extends JFrame implements ActionListener {

	private JLabel _JL;
	private JButton _JB;
	private JPanel _contentPanel;
	private Facade _facade;



	/**
	 * Constructor de la clase.
	 */
	public Mainwindow(Facade pFacade) {

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(332, 600);
		_facade = pFacade;
		this._contentPanel = new JPanel(null);
		this.setLocationRelativeTo(null);
		this.setContentPane(this._contentPanel);
		this.GUIElements();
		this.setVisible(true);
	}



	/**
	 * Metodo que carga todos los elementos de la ventana.
	 */
	public void GUIElements() {

		_JL = new JLabel("Bienvenido");
		_JB = new JButton("Inicio");

		_JB.addActionListener(this);

		_JL.setBounds(10, 10, 300, 40);
		_JB.setBounds(10, 300, 300, 40);

		_JB.setBackground(Color.decode("#fd330d"));
		_JB.setForeground(Color.WHITE);
		getContentPane().setBackground(Color.WHITE);
		_JL.setForeground(Color.decode("#fd330d"));

		this.getContentPane().add(_JL);
		this.getContentPane().add(_JB);
	}



	/**
	 * Metodo propio de la extencion JFrame.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == _JB) {
			// new Connection(_facade);
			// this.setVisible(false);
			boolean flag = _facade.makeConnection();
			if (flag) {
				LogoShellWindow shell = new LogoShellWindow(_facade);
				_facade.setShell(shell);
				this.dispose();
			}
			else {
				JOptionPane.showMessageDialog(this,
						"No se puede establecer la conexión bluetooth con el arduino",
						"Error de conexión", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

}
