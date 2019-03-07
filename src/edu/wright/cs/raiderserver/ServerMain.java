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
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class creates the chat server.
 * @author lukeg
 */
public class ServerMain {
	private static final String serverName = "SERVER";
	private static ArrayList<Scanner> clientScanners = new ArrayList();
	private static ArrayList<PrintWriter> outputs = new ArrayList();
	private static Scanner in = new Scanner(System.in);
	private static ServerSocket ss = null;
	
	/**
	 * This starts the socket server listening for connections.
	 */
	public static void main(String[] args) {
		int port = 8080;
		
		// Create the socket server
		try {
			ss = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("IOError occured. Aborted.");
			System.exit(1);
		}

		new Thread(() -> {
			while (true) {
				try {
					// Listen for new connections
					Socket incomingConn = ss.accept();
					System.out.println("Server ready!");
					
					// Get message to send to client
					OutputStream out = incomingConn.getOutputStream();
					PrintWriter pw = new PrintWriter(out, true);
					
					// Receive new messages
					InputStream is;
					is = incomingConn.getInputStream();
					Scanner receive = new Scanner(is);
					
					// Add all I/O components to ArrayLists for maintenance
					clientScanners.add(receive);
					outputs.add(pw);
				} catch (IOException e) {
					e.printStackTrace();
					System.out.println("IOError occured. Aborted.");
					System.exit(1);
				}
			}
		}).start();
		
		// Handle I/O...
		
		// Spawn new background thread to handle receipt
		new Thread(() -> {
			while (true) {
				// Iterate across all incoming scanners, see if 
				//   anything new is coming in
				for (Scanner s : clientScanners) {
					if (s.hasNext()) {
						System.out.println(s.nextLine());
					}
				}
			}
		}).start();
		
		// Get keyboard input on this thread
		while (true) {
			// Iterate across all outputs to send out messages
			for (PrintWriter p : outputs) {
				p.println(serverName + "," + in.nextLine());
				p.flush();
			}
		}
	}

}
