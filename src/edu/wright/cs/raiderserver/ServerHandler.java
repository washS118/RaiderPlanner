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

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author lukeg
 *
 */
public class ServerHandler extends Thread {

	private volatile ArrayList<Client> clients;
	
	private Queue<String> messages;
	
	public ServerHandler() {
		clients = new ArrayList<>();
		messages = new LinkedList<>();
	}
	
	public void run() {
		while(true) {
			for(Client client : clients) {
				String message = client.getMessage();
				if(message != null) {
					messages.add(message);
				}
			}
			
			while(!messages.isEmpty()) {
				String message = messages.poll();
				System.out.println(message);
				
				for(Client client : clients) {
					client.addMessage(message);;
				}
			}
		}
	}
	
	protected void addClient(Client client) {
		clients.add(client);
		client.start();
	}
}
