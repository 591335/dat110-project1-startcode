package no.hvl.dat110.rpc;

import no.hvl.dat110.TODO;
import no.hvl.dat110.messaging.*;

public class RPCClient {

	private MessagingClient msgclient;
	private Connection connection;
	
	public RPCClient(String server, int port) {
	
		msgclient = new MessagingClient(server,port);
	}
	
	public void connect() {
		
		if (connection == null) {
			connection = msgclient.connect();
		}
	}
	
	public void disconnect() {
		
		if (connection != null) {
			connection.close();
		}
	}
	
	public byte[] call(byte rpcid, byte[] params) {
		
//		Message melding = new Message(params);
//		connection.send(melding);
//		
//		Message mottattMelding = connection.receive();
		
		byte[] encaps = RPCUtils.encapsulate(rpcid, params);
        connection.send(new Message(encaps));

        Message mottatMelding = connection.receive();
		
		byte[] returnval = mottatMelding.getData();
		
		return returnval;
		
	}

}
