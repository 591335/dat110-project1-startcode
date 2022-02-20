package no.hvl.dat110.rpc;

import java.util.HashMap;

import no.hvl.dat110.TODO;
import no.hvl.dat110.messaging.Connection;
import no.hvl.dat110.messaging.Message;
import no.hvl.dat110.messaging.MessagingServer;

public class RPCServer {

	private MessagingServer msgserver;
	private Connection connection;

	// hashmap to register RPC methods which are required to extend RPCRemoteImpl
	private HashMap<Byte, RPCRemoteImpl> services;

	public RPCServer(int port) {

		this.msgserver = new MessagingServer(port);
		this.services = new HashMap<Byte, RPCRemoteImpl>();

	}

	public void run() {

		// the stop RPC method is built into the server
		RPCRemoteImpl rpcstop = new RPCServerStopImpl(RPCCommon.RPIDSTOP, this);

		System.out.println("RPC SERVER RUN - Services: " + services.size());

		connection = msgserver.accept();

		System.out.println("RPC SERVER ACCEPTED");

		boolean stop = false;

		while (!stop) {

			byte rpcid = 0;
			Message requestmsg, replymsg;

			// TODO - START
			// - receive Message containing RPC request
			// - find the identifier for the RPC method to invoke
			// - lookup the method to be invoked
			// - invoke the method
			// - send back message containing RPC reply

			requestmsg = connection.receive();
			

			rpcid = requestmsg.getData()[0];
			byte[] data = RPCUtils.decapsulate(requestmsg.getData());
			byte[] reply = services.get(rpcid).invoke(data);
			replymsg = new Message(RPCUtils.encapsulate(rpcid, reply));
			connection.send(replymsg);

			if (rpcid == RPCCommon.RPIDSTOP) {
				stop = true;
			}
			
//		   byte[] identifier = requestmsg.getData();
//		   
//		   /*
//		    * Hvis identifier er større enn 0, sett rpcid til index 0
//		    * hvis ikke, sett rpcid til 0
//		    * blir rpcid satt til 0, stop loop
//		    */
//		   if (identifier.length > 0) {
//		   rpcid = identifier[0]; 
//		   } else {
//			   rpcid = 0;
//		   }
//		   
//		   RPCRemoteImpl finn = services.get(rpcid);
//		   
//		   if (finn != null) {
//			   byte[] svar = finn.invoke(identifier);
//			   replymsg = new Message (svar);
//			   connection.send(replymsg);
//		   }
//		   
//		   if (rpcid == RPCCommon.RPIDSTOP) {
//			   stop = true;
//		   }
		}

	}

	// used by server side implementation to register themselves in the RPC server
	public void register(byte rpcid, RPCRemoteImpl impl) {
		services.put(rpcid, impl);
	}

	public void stop() {
		connection.close();
		msgserver.stop();

	}
}
