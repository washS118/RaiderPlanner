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

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * This class creates the chat server.
 * @author lukeg
 */
public class ServerMain {
	private static final String serverName = "SERVER";

	/**
	 * This starts the socket server listening for connections.
	 */
	public static void main(String[] args) {
		int port = 8080;

		try {
			// Create the socket server
			ServerSocket ss = new ServerSocket(port);

			// Listen for new connections
			Socket incomingConn = ss.accept();
			System.out.println("Server ready!");

			// Get message to send to client
			Scanner in = new Scanner(System.in);
			OutputStream out = incomingConn.getOutputStream();
			PrintWriter pw = new PrintWriter(out, true);

			// Receive new messages
			InputStream is = incomingConn.getInputStream();
			Scanner receive = new Scanner(is);

			// Handle I/O...

			// Spawn new background thread to handle receipt
			new Thread(() -> {
				while (true) {
					if (receive.hasNext()) {
						System.out.println(receive.nextLine());
					}
				}
			}).start();

			// Get keyboard input on this thread
			while (true) {
				pw.println(serverName + "," + in.nextLine());
				pw.flush();
			}
		} catch (IOException exc) {
			System.err.println("A problem has occurred. Aborted.");
			exc.printStackTrace();
			System.exit(1);
		}
	}
}
