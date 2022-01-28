package no.hvl.dat110.messaging;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import no.hvl.dat110.TODO;


public class Connection {

	private DataOutputStream outStream; // for writing bytes to the TCP connection
	private DataInputStream inStream; // for reading bytes from the TCP connection
	private Socket socket; // socket for the underlying TCP connection
	
	public Connection(Socket socket) {

		try {

			this.socket = socket;

			outStream = new DataOutputStream(socket.getOutputStream());

			inStream = new DataInputStream (socket.getInputStream());

		} catch (IOException ex) {

			System.out.println("Connection: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	public void send(Message message) {

		byte[] data;
		
		try {
			// Skriver en kodet melding til outStream
			outStream.write(MessageUtils.encapsulate(message));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Message receive() {

		Message message = null;
		byte[] data;

		
		// Medlingene inneholder 128 byte / alstå 1024 bits
		data = new byte[127];
		
		try {
			// Fra instream, les data fra melding
			inStream.read(data);
			message = MessageUtils.decapsulate(data);

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return message;
		
	}

	// close the connection by closing streams and the underlying socket	
	public void close() {

		try {
			
			outStream.close();
			inStream.close();

			socket.close();
			
		} catch (IOException ex) {

			System.out.println("Connection: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
}