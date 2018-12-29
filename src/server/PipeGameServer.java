package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class PipeGameServer implements Server {

	private int port;
	private ClientHandler ch;
	private boolean toStop;
	private InputStream in;
	private OutputStream out;
	private ExecutorService executor;
	
	public PipeGameServer(int port, ClientHandler ch) {
		this.port = port;
		this.ch = ch;
		this.in = null;
		this.out = null;
		this.toStop = false; 
		this.executor=Executors.newFixedThreadPool(4);
		System.out.println("*** Server is alive and waiting on port: "+this.port+" ***");
	}
	@Override
	public void stop() 
	{
		this.toStop = true;
	}
	private void runServer() throws IOException
	{
		ServerSocket server = null;
		
		try {
			server = new ServerSocket(port);
		}  catch (SocketException e) {
		} catch (IOException e) {
		}
		
		while(toStop != true)
		{
			try {
				Socket aClient = server.accept();
				System.out.println("***** A new client connected *****");
				executor.execute(()->{
					try {
						in = aClient.getInputStream();
						out = aClient.getOutputStream();
						ch.handleClient(in, out);
						aClient.close();
					} catch (IOException e) {} 
				});
			}catch (IOException e) {}
			finally {
				if(in != null)
						in.close();
				if(out!= null)
						out.close();
			}
		}	
		executor.shutdown();
		if(server!=null)
				server.close();
		
	}
	@Override
	public void start() {
		new Thread (()->{
			try {
				runServer();
			} catch (IOException e) {}
		}).start();
	}
	
}
