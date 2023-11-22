import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Server{
	private static final int PORT=5692;
	
	public static void main(String[] args) {
		ServerSocket serverSocket=null;
		Scanner userInput=new Scanner(System.in);
		try {
			serverSocket=new ServerSocket(PORT);
			System.out.println("Server Listning on port: "+PORT);
			
			while(true) {
				Socket clientSocket=serverSocket.accept();
				System.out.println("New client Connected: "+clientSocket.getInetAddress());
				
				ClientHandler clientHandler=new ClientHandler(clientSocket);
				Thread thread=new Thread(clientHandler);
				thread.start();
				String message;
				while(true)
				{
					System.out.println("Server: ");
					message=userInput.nextLine();
					if("exit".equalsIgnoreCase(message)) {
						break;
					}
					clientHandler.sendMessageToClient(message);
				}
			}
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			if(serverSocket!=null) {
				try {
					serverSocket.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
		userInput.close();
	}
}

class ClientHandler implements Runnable{
	private Socket clientSocket;
	private PrintWriter writer;
	
	public ClientHandler(Socket clientSocket)
	{
		this.clientSocket=clientSocket;
		try {
			this.writer=new PrintWriter(clientSocket.getOutputStream(),true);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		try(BufferedReader reader=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));){
			String inputLine;
			while((inputLine=reader.readLine())!=null)
			{
				System.out.println("Client: "+inputLine);
			}
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			try {
				clientSocket.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void sendMessageToClient(String message) {
		writer.println(message);
	}
}
