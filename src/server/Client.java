package server;

import game.Board;

import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Data on the server associated with a remote client playing pingball.
 */
public class Client {
    private static final int CAPACITY = 5;
    private final Board board;
    private final Socket socket;
    private final boolean online;

    public Client(Board board, Socket socket, boolean online) {
        this.board = board;
        this.socket = socket;
        this.online = online;
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
}
