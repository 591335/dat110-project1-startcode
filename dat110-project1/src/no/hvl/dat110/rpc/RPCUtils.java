package no.hvl.dat110.rpc;

import java.nio.ByteBuffer;
import java.util.Arrays;
import no.hvl.dat110.TODO;

public class RPCUtils {
	
	public static byte[] encapsulate(byte rpcid, byte[] payload) {
		
		
		
		// TODO - START
		
		// Encapsulate the rpcid and payload in a byte array according to the  RPC message syntax
		byte[] rpcmsg = new byte[payload.length + 1];
		for (int i = 0; i < payload.length; i++) {
			rpcmsg[i+1] = payload[i];
		}
		rpcmsg[0] = rpcid;
		
		return rpcmsg;
	}
	
	public static byte[] decapsulate(byte[] rpcmsg) {
		
		// TODO - START
		
		// Decapsulate the rpcid and payload in a byte array according to the  RPC message syntax
		byte[] payload = null;
		for(int i = 0; i < payload.length; i++) {
			payload[i] = rpcmsg[i+1];
		}
		return payload;
	}
	
	public static byte[] marshallString(String str) {
		
		byte[] encoded = str.getBytes();
		
		return encoded;
	}
	
	public static String unmarshallString(byte[] data) { 
		
		String decoded = new String(data);
		
		return decoded;
	}
	
	public static byte[] marshallVoid() {
		
		byte[] encoded = new byte[0];
		return encoded;
		
	}
	
	public static void unmarshallVoid(byte[] data) {
		
		return;
	}
	
	public static byte[] marshallBoolean(boolean b) {
		
		byte[] encoded = new byte[1];
				
		if (b) {
			encoded[0] = 1;
		} else
		{
			encoded[0] = 0;
		}
		
		return encoded;
	}
	
	public static boolean unmarshallBoolean(byte[] data) {
		
		return (data[0] > 0);
		
	}
	
	public static byte[] marshallInteger(int x) {
		
		byte[] encoded = new byte[5];
		byte[] b = ByteBuffer.allocate(4).putInt(x).array();
		for(int i = 1; i<encoded.length; i++) {
			encoded[i] = b[i - 1];
		}
		return encoded;
	}
	
	
	public static int unmarshallInteger(byte[] data) {
		
		int decoded = ByteBuffer.wrap(Arrays.copyOfRange(data, 1, data.length)).getInt();
		return decoded;
		
	}
}
