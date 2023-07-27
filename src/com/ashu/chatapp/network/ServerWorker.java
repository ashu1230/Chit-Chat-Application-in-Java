package com.ashu.chatapp.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

//thread == worker....
//worker need a to perform
//for job u given runnable
//Once Job is created via runnable so write the job logic inside a run function
//Assign the job to a THread/Worker
//public class ServerWorker implements Runnable{

//public class ServerWorker extends Thread{
//	@Override
//	public void run() {
////		job to perform
////		Logic
//		
//		for(int i=1; i<=5; i++) {
//			System.out.println("Run I is "+i+" "+Thread.currentThread());
//		}	
//	}
//	public static void main(String[] args) {
//		ServerWorker worker = new ServerWorker();
//		worker.start();
////		assign a job to a thread..
////		Thread worker = new Thread(job);
////		worker.start();    //Internally it call run()
//		for(int j=1; j<=5; j++) {
//			System.out.println("Run"+j+" "+Thread.currentThread());
//		}
//	}
//}
public class ServerWorker extends Thread{
	private Server server;
	private Socket clientSocket;
	private InputStream in;
	private OutputStream out;
	public ServerWorker(Socket clientSocket, Server server) throws IOException{
		this.clientSocket = clientSocket;
		this.server = server;
		in = clientSocket.getInputStream();  //client data read
		out = clientSocket.getOutputStream();   ////client data write
		System.out.println("New CLient Comes");
	}
	@Override
	public void run() {
//		job to perform
//		Logic
//		read data from the client and the broadcast the data to all
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String line;
		try {
		while(true){
				line = br.readLine();
				if(line.equalsIgnoreCase("quit")) {
					break;   //client chat end ---- particular client
				}
//				out.write(line.getBytes());
//				Broadcast to all clients..
				for(ServerWorker serverWorker : server.workers) {
					line = line +"\n";
					serverWorker.out.write(line.getBytes());
				}
		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
			if(br!=null) {
				br.close();
			}
			if(in!=null) {
				in.close();
			}
			if(out!=null) {
				out.close();
			}
			if(clientSocket!=null) {
				clientSocket.close();
			}}
			catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}








