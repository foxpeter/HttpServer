package version01;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class HttpServer {
	
	public static final String WEB_ROOT = 
			System.getProperty("user.dir") + File.separator + "webroot";
	
	public static final String SHUTDOWN_COMMAND = "/SHUTDOWN";
	
	private boolean shutdown = false;
	
	public void await(){
		ServerSocket ss = null;
		int port = 7777;
		
		try {
			ss = new ServerSocket(port,1,InetAddress.getByName("127.0.0.1"));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		while(!shutdown){
			Socket s = null;
			InputStream in = null;
			OutputStream out = null;
			
			try {
				s = ss.accept();
				in = s.getInputStream();
				out = s.getOutputStream();
				
				Request req = new Request(in);
				req.parse();
				
				Response resp = new Response(out);
				resp.setRequest(req);
				resp.sendStaticResource();
				
				s.close();
				
				shutdown = req.getURI().equals(SHUTDOWN_COMMAND);
			} catch (IOException e) {
				e.printStackTrace();
				continue;
			}
		}
	}
	
	public static void main(String[] args) {
		HttpServer server = new HttpServer();
		server.await();
	}

}
