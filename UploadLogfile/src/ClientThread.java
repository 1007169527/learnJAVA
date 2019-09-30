import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ClientThread extends Thread {
	protected Socket clientSocket;
	private BufferedReader socketBufferedReader = null;
	private BufferedWriter socketBufferedWriter = null;
	private BufferedReader fileBufferedReader = null;
	private int count = 0;

	private String logFileFullPath = "";
	private String pcName = "";

	public ClientThread(Socket clientSocket, String logFileFullPath, String pcName) {
		this.clientSocket = clientSocket;
		try {
			socketBufferedWriter = new BufferedWriter(new OutputStreamWriter(this.clientSocket.getOutputStream()));
			socketBufferedReader = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
			fileBufferedReader = new BufferedReader(new FileReader(logFileFullPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.logFileFullPath = logFileFullPath;
		this.pcName = pcName;
		System.out.println("a new client thread init finished");
	}

	private void uploadLogFile() {
		byte[] buf = new byte[4096];
		String line;
		try {
			// socketBufferedWriter.write("LogFileFullPath=" + logFileFullPath + "\n\r");
			// socketBufferedWriter.write("PCName=" + pcName + "\n\r");

			line = fileBufferedReader.readLine();
			while (line != null) {
				System.out.println("a new line from src: " + line);
				socketBufferedWriter.write(line + "\n");
				socketBufferedWriter.flush();
				line = fileBufferedReader.readLine();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fileBufferedReader.close();
				clientSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void checkServerMsg() {
		String message = "";
		do {
			try {
				message = socketBufferedReader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} while (!message.equals("SERVER>>> MD5 CHECK PASS"));
	}

	public void run() {
		uploadLogFile();
		// checkServerMsg();
		return;
	}
}