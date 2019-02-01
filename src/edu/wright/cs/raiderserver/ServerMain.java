/*
 * Copyright (C) 2019
 *
 *
 *
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package edu.wright.cs.raiderserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * This class creates the chat server.
 * @author lukeg
 */
public class ServerMain {

	/**
	 * This starts the socket server listening for connections.
	 */
	public static void main(String[] args) {
		int port = 8080;

		try {
			// Create the socket server
			ServerSocket ss = new ServerSocket(port);
			
			// Listen for new connections
			ArrayList clients = new ArrayList<ClientThread>();
			while (true) {
				try {
					Socket incomingConn = ss.accept();
					ClientThread newClient = new ClientThread(incomingConn);
					new Thread(newClient).start();
					clients.add(newClient);
				} catch (IOException exc) {
					System.err.println("Incoming connection failed!");
				}
			}
		} catch (IOException exc) {
			System.err.println("A problem has occurred. Aborted.");
			System.exit(1);
		}
	}

}

/**
 * This class implements a client manager thread.
 * @author ajhs2
 */
class ClientThread extends ServerMain implements Runnable {
	
	private Socket sock;
	private BufferedReader in;
	private PrintWriter out;
	
	public ClientThread(Socket sock) {
		this.sock = sock;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		try {
			out = new PrintWriter(sock.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			
			while (!sock.isClosed()) {
				String input = in.readLine();
				if (input != null) {
					for (ClientThread c : clients) {
						c.getWriter().write(input);
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public PrintWriter getWriter() {
		return out;
	}
	
}
