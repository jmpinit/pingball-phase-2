package serverClientEtc;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

import Priya_gameParts.*;

public class Client {

	private static final int CAPACITY = 5;
	private final Board board;
	private final Socket socket;
	private final boolean online;
    private BlockingQueue<Integer> timeQueue;
		
	public Client(Board board, Socket socket, boolean online, BlockingQueue<Integer> timeQueue) {
		this.board = board;
		this.socket = socket;
		this.online = online;
		this.timeQueue = new ArrayBlockingQueue<Integer>(CAPACITY);
	}

	public Board getBoard() {
		return board;
	}


	public Socket getSocket() {
		return socket;
	}

	public boolean isOnline() {
		return online;
	}
	
	public void sendTime(Integer t) {
		try {
			this.timeQueue.put(t);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public Integer peekTime() {
		return this.timeQueue.peek();
	}
	
	public Integer getTime() {
		Integer n = -1;
		try {
			n = this.timeQueue.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return n;
	}
	

}
