package nia.chapter1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Random;

public class NIOClient {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		InetSocketAddress crunchifyAddr = new InetSocketAddress("localhost", 1111);
		SocketChannel crunchifyClient = SocketChannel.open(crunchifyAddr);
		
		log("Connecting to Server on port 1111...");
		
		ArrayList<String> companyDetails = new ArrayList<String>();
		
		Random r = new Random();
		int id = r.nextInt(10);
		
		// create a ArrayList with companyName list
		companyDetails.add(id + ": Facebook");
		companyDetails.add(id + ": Twitter");
		companyDetails.add(id + ": IBM");
		companyDetails.add(id + ": Google");
		companyDetails.add(id + ": Crunchify");
		companyDetails.add(id + ": close");
		
		for (String companyName : companyDetails) {
			
			byte[] message = new String(companyName).getBytes();
			ByteBuffer buffer = ByteBuffer.wrap(message);
			crunchifyClient.write(buffer);
			
			log("sending: " + companyName);
			buffer.clear();
			
			// wait for 2 seconds before sending next message
			Thread.sleep(1000);
		}
		crunchifyClient.close();
	}
	
	private static void log(String str) {
		System.out.println(str);
	}
}