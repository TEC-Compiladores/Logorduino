package logic;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by gerald, albin, juan, diego on 26/02/16.
 */
public class ClientThread implements Runnable {

	private String _IP;
	private int _PORT;
	private Socket _SOCKET;
	private boolean _flag;
	private String _Message1 = "Successful Connection";
	private String _Message2 = "Host Error";
	private String _Message3 = "Wrong Data";
	private String _MessageInto;
	private DataInputStream _Incoming;
	private DataOutputStream _Outcoming;



	/**
	 * Constructor de la clase
	 */
	public ClientThread() {
		_flag = false;
	}



	/**
	 * Metodo hilo, en este se pone todo los procedimientos y se llaman metodos
	 * que
	 * vallan a correr cuando se inicie el thread Reading.
	 */
	@Override
	public void run() {
		try {
			InetAddress ip = InetAddress.getByName(_IP);
			_SOCKET = new Socket(_IP, _PORT);
			_flag = true;
			System.out.println(_Message1);
			_Outcoming = new DataOutputStream(_SOCKET.getOutputStream());
			_Incoming = new DataInputStream(_SOCKET.getInputStream());
			while (true) {
				_MessageInto = _Incoming.readUTF();
				// System.out.println(_MessageInto);
				/**
				 * if(!_MessageInto.equals("1")){
				 * sendMessage(_MessageInto);
				 * //System.out.println("Se envio");
				 * }
				 */
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(_Message2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(_Message3);
		}
	}



	/**
	 * Metodo para leer los mensajes recibidos
	 */
	public String getMessage() {

		return _MessageInto;
	}



	/**
	 * Metodo para enviar mensajes
	 */
	public String sendMessage(String pMessage) {
		String reply = "";
		try {
			_Outcoming.writeUTF(pMessage);
			_Outcoming.flush();

			// _MessageInto = _Incoming.readUTF();
			// reply = getMessage();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return reply;
	}



	/**
	 * Metodo para cerrar conexion
	 */
	public void closeConnection() {
		try {
			sendMessage("I stopped sending data");
			_Incoming.close();
			_Outcoming.close();
			_SOCKET.close();
			_flag = false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	/**
	 * Metodo para preguntar por conexion
	 */
	public boolean ifConnection() {
		return _flag;
	}



	/**
	 * Metodo para setear la direccion IP
	 */
	public void setIP(String pIP) {
		_IP = pIP;
	}



	/**
	 * Metodo para setear el numero de puerto de la conexion
	 */
	public void set_PORT(int pPort) {
		_PORT = pPort;
	}

}
