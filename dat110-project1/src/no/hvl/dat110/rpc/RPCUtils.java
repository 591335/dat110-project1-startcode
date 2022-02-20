package no.hvl.dat110.rpc;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Arrays;
import no.hvl.dat110.TODO;

public class RPCUtils {

	public static byte[] encapsulate(byte rpcid, byte[] payload) {

		byte[] rpcmsg = null;

		rpcmsg = new byte[payload.length + 1];
		rpcmsg[0] = rpcid;

//		System.arraycopy(payload, 0, rpcmsg, 1, payload.length);

		for (int i = 0; i < payload.length; i++) {
			rpcmsg[i + 1] = payload[i];
		}

		return rpcmsg;
	}

	public static byte[] decapsulate(byte[] rpcmsg) {

		byte[] payload = null;

		// rpcmsg.length - 1 for å ikke ta med rpdid

//		byte rpcid = rpcmsg[0];
		payload = new byte[rpcmsg.length - 1];

//		System.arraycopy(rpcmsg, 1, payload, 0, payload.length);

		for (int i = 0; i < rpcmsg.length - 1; i++) {
			payload[i] = rpcmsg[i + 1];
		}

		return payload;

	}

	public static byte[] marshallString(String str) {

		byte[] encoded = null;

		encoded = str.getBytes();

		return encoded;
	}

	public static String unmarshallString(byte[] data) {

		String decoded = null;
		
		decoded = new String(data);

		return decoded;
	}

	public static byte[] marshallVoid() {

		byte[] encoded = null;
		
		encoded = new byte[1];

		return encoded;

	}

	public static void unmarshallVoid(byte[] data) {
		return;

	}

	public static byte[] marshallBoolean(boolean b) {

		byte[] encoded = new byte[2];

		if (b) {
			encoded[1] = 1;
		} else {
			encoded[1] = 0;
		}

		return encoded;
	}

	public static boolean unmarshallBoolean(byte[] data) {

		return (data[1] > 0);

	}

	public static byte[] marshallInteger(int x) {

//		byte[] encoded = new byte[5];
//
//		byte[] b = ByteBuffer.allocate(4).putInt(x).array();

//		for(int i = 1; i<encoded.length; i++) {
//			encoded[i] = b[i-1];
//		}

//		byte[] encoded = new byte[] { (byte) ((x >> 24) & 0xff), (byte) ((x >> 16) & 0xff), (byte) ((x >> 8) & 0xff),
//				(byte) ((x >> 0) & 0xff), };
//
//		return encoded;
		
		 byte[] encoded = null;
	        
	        // TODO - START 
	        
	        BigInteger bigInt = BigInteger.valueOf(x);
	        encoded = bigInt.toByteArray();
	        // TODO - END
	        
	        return encoded;
	}

	public static int unmarshallInteger(byte[] data) {

//		int decoded = ByteBuffer.wrap(Arrays.copyOfRange(data, 1, data.length)).getInt();

//		int decoded = data[0] & 0xFF << 24 | (data[1] & 0xFF) << 16 | (data[2] & 0xFF) << 8 | (data[3] & 0xFF) << 0;
//
//		return decoded;
		
		 int decoded = 0;
	        // TODO - START 
	        decoded = new BigInteger((data)).intValue();
	        // TODO - END
	        
	        return decoded;

	}
}
