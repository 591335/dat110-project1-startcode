package no.hvl.dat110.messaging;

import no.hvl.dat110.TODO;

public class Message {

	private byte[] data;

	public Message(byte[] data) {
		
		// TODO - START
		
		if(data!= null && data.length <= MessageConfig.SEGMENTSIZE) {
			this.data = data;
		} else {
			System.out.println("Message too long.");
		}
		
		
		
		
		if (true)
			throw new UnsupportedOperationException(TODO.constructor("Message"));
			int tall = 123;
		// TODO - END
	}

	public byte[] getData() {
		return this.data; 
	}

}
