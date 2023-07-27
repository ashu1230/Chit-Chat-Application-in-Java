package com.ashu.chatapp.network;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JTextArea;

import com.ashu.chatapp.utils.ConfigReader;

public class Client {
	Socket socket;
	OutputStream out;
	InputStream in;
	ClientWorker worker;
	JTextArea textArea;
	public Client(JTextArea textArea) throws UnknownHostException, IOException {
		int PORT = Integer.parseInt(ConfigReader.getValue("PORTNO"));
		socket = new Socket(ConfigReader.getValue("SERVER_IP"), PORT);
		out = socket.getOutputStream();
		in = socket.getInputStream();
		this.textArea = textArea;
		ReadMessages();
		
//		for test the code by running command......
		
//		System.out.println("Client Comes");
//		System.out.println("Enter The Message Send to the Server.....");
//		Scanner scanner = new Scanner(System.in);
//		String message = scanner.nextLine();
//		OutputStream out = socket.getOutputStream();   //write bytes on network
//		out.write(message.getBytes());	
//		System.out.println("Message send to the Server");
//		scanner.close();
//		out.close();
//		socket.close();
	}
	public void SendMessage(String message) throws IOException {
		message = message + "\n";
		out.write(message.getBytes());
	}
	
	public void ReadMessages() {
		worker = new ClientWorker(in, textArea);  // Calling a read thread
		worker.start();
	}
	

//	public static void main(String[] args) throws UnknownHostException, IOException {
//		// TODO Auto-generated method stub
//		Client client = new Client();
//	}

}
