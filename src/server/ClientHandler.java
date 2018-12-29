package server;

import java.io.OutputStream;
import java.io.InputStream;

public interface ClientHandler {

	public void handleClient(InputStream inFromClient, OutputStream outToClient);
}
