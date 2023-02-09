package simplechatting.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerApplication {
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		
		try {
			serverSocket  = new ServerSocket(9090); // 9090 쓰고 있다면
			System.out.println("==========<<<서버실행>>>==========");
			
			Socket socket = serverSocket.accept(); // 클라이언트의 접속을 기다리는 녀석
			
			OutputStream outputStream = socket.getOutputStream();
			PrintWriter out = new PrintWriter(outputStream, true);
			out.println("join");
//			System.out.println(socket.getInetAddress() + "클라이언트가 연결되었습니다.");
			
			InputStream inputStream = socket.getInputStream();
			BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
			
			String welcomeMessage = in.readLine();
			System.out.println(welcomeMessage); // 서버
			out.println(welcomeMessage); // 클라이언트
			
			while(true) {
				in.readLine();
			}
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}finally {
			if(serverSocket != null) {
				try {
					serverSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			System.out.println("==========<<<서버종료>>>==========");
		}
		
	}

}
