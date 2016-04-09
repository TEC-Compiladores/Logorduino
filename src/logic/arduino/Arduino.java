package logic.arduino;

import java.util.LinkedList;
import java.util.Queue;

import jssc.SerialPort;
import jssc.SerialPortException;

/**
 * 
 * @author Juan Pablo Brenes
 *         3/4/2016
 * 
 *         Clase que permite la comunicación con el arduino
 *
 */

public class Arduino implements Runnable, ConstantsServer {

	private Queue<String> _queue;
	private SerialPort _serialPort;
	private boolean _connected;
	private boolean _running;
	private boolean _debug;



	/**
	 * 
	 * Constructor de la clase
	 * 
	 * @param pDebug
	 */
	public Arduino(boolean pDebug) {
		this._connected = false;
		_queue = new LinkedList<String>();
		_debug = pDebug;
	}




	public boolean connect() {
		_serialPort = new SerialPort("/dev/rfcomm0");

		try {
			_serialPort.openPort();
			_serialPort.setParams(SerialPort.BAUDRATE_9600, SerialPort.DATABITS_8,
					SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
			_connected = true;
		} catch (SerialPortException e) {
			_connected = false;
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
		// _running = true;
		// if (this._connected) {
		// long timeInit = System.currentTimeMillis();
		//
		// while (_running) {
		// long timeFinal = System.currentTimeMillis();
		//
		// if ((timeFinal - timeInit) > 1000) {
		// if (!_queue.isEmpty()) {
		// String message = _queue.poll();
		// // _out.println(message);
		// if (_debug)
		// System.out.println(ARDUINO_CLASS + ARDUINO_MESSAGE_SEND + message);
		// timeInit = System.currentTimeMillis();
		// }
		// else
		// _running = false;
		// }
		//
		// }
		//
		// }
		_running = true;
		this.run();
	}



	/**
	 * Método que permite desconectar la conexión bluetooth entre la computadora
	 * y el arduino
	 */
	public void disconnect() {
		try {
			_serialPort.closePort();
		} catch (SerialPortException e) {
			System.out.println(ARDUINO_CLASS + "Error al cerrar el puerto bluetooth");
		}
	}



	@Override
	public void run() {

		if (this._connected) {
			long timeInit = System.currentTimeMillis();

			while (_running) {
				long timeFinal = System.currentTimeMillis();
				if ((timeFinal - timeInit) > 4000) {
					if (!_queue.isEmpty()) {
						String message = _queue.poll();
						try {
							_serialPort.writeBytes((message + "\r\n").getBytes());
						} catch (SerialPortException e) {
							System.out.println("Error al enviar un mensaje");
						}
						if (_debug)
							System.out.println(ARDUINO_CLASS + ARDUINO_MESSAGE_SEND + message);
					}
					else
						_running = false;
					timeInit = System.currentTimeMillis();
				}
			}
		}
		else {
			if (_debug) System.out.println(ARDUINO_CLASS + ARDUINO_NOT_CONNECTED);
		}

	}


}
