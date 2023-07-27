package com.ashu.chatapp.network;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.ashu.chatapp.utils.ConfigReader;

public class Server {
	ServerSocket serverSocket;
	ArrayList<ServerWorker> workers = new ArrayList<>();  //containes all the sockets
	public Server() throws IOException {
		int PORT = Integer.parseInt(ConfigReader.getValue("PORTNO"));
		serverSocket = new ServerSocket(PORT);
		System.out.println("server start and waiting for the client to join..");
		handkeClientRequest();
	}
	
	
//	multiple client handshaking
	public void handkeClientRequest() throws IOException {
		while(true) {
			Socket clientSocket = serverSocket.accept();
//			per client per thread...
			ServerWorker serverWroker = new ServerWorker(clientSocket, this); //Creating a new Worker/ Thread
			workers.add(serverWroker);
			serverWroker.start();
			}
	}
	
	
	
	

//	for Single client
//	public Server() throws IOException {
//		int PORT = Integer.parseInt(ConfigReader.getValue("PORTNO"));
//		serverSocket = new ServerSocket(PORT);
//		System.out.println("Server Started and Waiting For the Client Connection..");
//		Socket socket = serverSocket.accept(); ///handshaking.
//		System.out.println("Clients join The Server");
//		InputStream in = socket.getInputStream();  //read bytes form network.
//		byte arr[] = in.readAllBytes();		//in java 9 to read all bytes.
//		String str = new String(arr); 	//bytes convert into string.
//		System.out.println("Message Recive Form Client:- " + str);
//		in.close();
//		socket.close();
//	}
	
	public static void main(String[] args) throws IOException {
		Server server = new Server();

	}

}
