package logic.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * @author Juan Pablo Brenes
 *         3/4/2016
 * 
 *         Clase que permite la comunicación con el arduino
 *
 */

public class Arduino implements ConstantsServer {

	private Queue<String> _queue;
	private String _ip;
	private int _port;
	private Socket _socket;
	private PrintWriter _out;
	private boolean _connected;
	boolean _debug;



	/**
	 * 
	 * Constructor de la clase
	 * 
	 * @param pDebug
	 */
	public Arduino(boolean pDebug) {
		this._connected = true;
		_queue = new LinkedList<String>();
		_debug = pDebug;
	}



	/**
	 * Método que realiza la conexión con el arduino
	 * 
	 * @param pIP
	 *            IP del arduino
	 * @param pPort
	 *            Puerto del arduino
	 * @return
	 */
	public boolean connect(String pIP, int pPort) {
		_ip = pIP;
		_port = pPort;

		try {
			_socket = new Socket(_ip, _port);
			this._connected = true;
			_out = new PrintWriter(_socket.getOutputStream(), true);
			_out.println("3Test");
			if (_debug) System.out.println(ARDUINO_CLASS + ARDUINO_SUCCESSFUL_CONNECTION);

		} catch (UnknownHostException e) {
			if (_debug) System.err.println(ARDUINO_CLASS + ARDUINO_ERROR_CONNECTION);
		} catch (IOException e) {
			if (_debug) System.err.println(ARDUINO_CLASS + ARDUINO_ERROR_IO);
		}

		return _connected;
	}



	/**
	 * Método que agrega mensajes a la cola de mensajes
	 * 
	 * @param pMessage
	 *            Mensaje para agregar en la cola
	 */
	public void addToQueue(String pMessage) {
		_queue.add(pMessage);
	}



	/**
	 * Método que comienza a enviar los mensaje al arduino para su ejecución
	 */
	public void sendMessages() {
		if (this._connected) {
			long timeInit = System.currentTimeMillis();

			while (!_queue.isEmpty()) {
				long timeFinal = System.currentTimeMillis();

				if ((timeFinal - timeInit) > 1000) {
					String message = _queue.poll();
					// _out.println(message);
					if (_debug) System.out.println(ARDUINO_CLASS + ARDUINO_MESSAGE_SEND + message);
					timeInit = System.currentTimeMillis();
				}

			}

		}
	}


}
