package no.hvl.dat110.messaging;

import no.hvl.dat110.TODO;

public class Message {

	private byte[] data;

	public Message(byte[] data) {
		
		// TODO - START
		
		/**
		 *  Hvis meldingen er større eller lik segmentsize (128) er meldingen for lang
		 */
		if(data != null && data.length <= MessageConfig.SEGMENTSIZE){
			this.data = data;
		} else {
			System.out.println("The message is to long");
		}
		
	}
	
	public Message() {
		super();
	}

	public byte[] getData() {
		return this.data; 
	}
}
