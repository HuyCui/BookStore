import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server{
   public static void main(String[] args)throws IOException
	{
		ServerSocket serverSocket = new ServerSocket(9011);
         Socket socket = serverSocket.accept();
		 System.out.println(socket.getInetAddress().getHostAddress()+"has connected");
		 BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
         BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		 String line =null;
		 while((line = br.readLine())!=null){
             if(line.equals("#")){
				 break;
	}
	//System.out.println(line);
    //bw.write(line.toUpperCase());
	bw.flush();
}
bw.close();
br.close();
socket.close();
 System.out.println(socket.getInetAddress().getHostAddress()+"has connected");
}
}
