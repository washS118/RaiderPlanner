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
import java.io.InputStream;
import java.io.InputStreamReader;
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
	private static Scanner in = new Scanner(System.in);
	private static ServerSocket ss = null;
	private static volatile String toSend = "";
	
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
					BufferedReader receive = new BufferedReader(
							new InputStreamReader(is));

					// Start reader thread to get new input from clients
					spawnClientReaderThread(receive);
					
					// Start writer thread to send out a message
					spawnClientWriterThread(pw);
				} catch (IOException e) {
					e.printStackTrace();
					System.out.println("IOError occured. Aborted.");
					System.exit(1);
				}
			}
		}).start();
		
		// Get server input on this thread
		while (true) {
			toSend = in.nextLine();
		}

	}
	
	private static void spawnClientReaderThread(BufferedReader recieve) {
		// Spawn new background thread to handle receipt
		new Thread(() -> {
			String incoming;
			while (true) {
				try {
					if ((incoming = recieve.readLine()) != null) {
						System.out.println(incoming);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
	}
	
	private static void spawnClientWriterThread(PrintWriter pw) {
		new Thread(() -> {
			String lastSent = "";
			while (true) {
				// Check if we've already send the current message
				if (lastSent != toSend) {
					// Iterate across all outputs to send out messages
					pw.println(serverName + "," + toSend);
					pw.flush();
					lastSent = toSend;
				}
			}
		}).start();
	}

}
