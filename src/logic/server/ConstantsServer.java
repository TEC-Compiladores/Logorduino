package logic.server;

/**
 * @author Juan Pablo Brenes 6/3/2016
 * 
 *         Constantes del paquete server
 *
 */
interface ConstantsServer {

	// ###################### CLASS ARDUINO ######################

	// ERROR
	public static final String ARDUINO_ERROR_IO = "Error al enviar un mensaje al arduino";
	public static final String ARDUINO_ERROR_CONNECTION = "Error al intentar conectarse con el arduino";

	// NOTIFICATION
	public static final String ARDUINO_SUCCESSFUL_CONNECTION = "Conexión exitosa con el arduino";
	public static final String ARDUINO_NOT_CONNECTED = "No hay conexión con el arduino";

	//
	public static final String ARDUINO_CLASS = "ARDUINO >> ";
	public static final String ARDUINO_MESSAGE_SEND = "Enviado al arduino: ";


}
