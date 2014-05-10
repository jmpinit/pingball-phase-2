package server;

public class NetworkProtocol {
    private static int uid = 0;
    
    /**
     * @return a uniquely identifying integer
     * (only up to MAX_INTEGER number of calls)
     */
    public static int getUID() {
        return uid++;
    }
}

interface NetworkEvent<T> {
    public int getObjectUID();
    public int getFieldUID();
    public T getValue();
}
