import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;

public class Client {
	private static final String SERVER_IP="192.168.0.103";
	private static final int SERVER_PORT=5692;
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		try(Socket socket=new Socket(SERVER_IP,SERVER_PORT);
			PrintWriter writer=new PrintWriter(socket.getOutputStream(),true)){
			while(true) {
				System.out.println("Connected to Server");
				
				Thread readerThread=new Thread(()->{
					try(BufferedReader reader=new BufferedReader(new InputStreamReader(socket.getInputStream()))){
						
						String response;
						while((response=reader.readLine())!=null) {
							System.out.println("Server "+response);
						}
					}catch(IOException e) {
						e.printStackTrace();
					}
				});
				readerThread.start();
				String message;
				while(true) {
					System.out.println("Client message");
					message=sc.nextLine();
					if("exit".equalsIgnoreCase(message)) {
						break;
					}
					writer.println(message);
				}
			}
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			sc.close();
		}
	}
}
