package no.hvl.dat110.rpc;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import no.hvl.dat110.TODO;
import no.hvl.dat110.messaging.*;

public class RPCClient {

	private MessagingClient msgclient;
	private Connection connection;
	
	public RPCClient(String server, int port) {
	
		msgclient = new MessagingClient(server,port);
	}
	
	public void connect() {
		
		// TODO - START
		// connect using the underlying messaging layer connection
		
		if(connection == null) {
			connection = msgclient.connect();
		}
		
		
		
		
//		if (true)
//			throw new UnsupportedOperationException(TODO.method());
//		
		// TODO - END
	}
	
	public void disconnect() {
		
		// TODO - START
		// disconnect/close the underlying messaging connection
		
		if (true)
			throw new UnsupportedOperationException(TODO.method());
		
		// TODO - END
	}
	
	public byte[] call(byte rpcid, byte[] params) {
		
		
		/* 
		 * 
		Make a remote call on the RPC server by sending an RPC request message
		and receive an RPC reply message
		
		params is the marshalled parameters from the client-stub
				
		The rpcid, params, and return value must be encapsulated/decapsulated
		according to the RPC message format
			
		*/
		
		byte[] encaps = RPCUtils.encapsulate(rpcid, params);
		connection.send(MessageUtils.decapsulate(encaps));
		
		byte[] returnval = MessageUtils.encapsulate(connection.receive());
		returnval = RPCUtils.decapsulate(returnval);
		
		return returnval;
		
	}

}
