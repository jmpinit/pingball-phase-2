package game;

import server.NetworkProtocol.NetworkEvent;

public class NetworkEventImplementation<T> implements NetworkEvent<T>{
    int ObjectUID;
    int FieldUID;
    T value;
    
    public NetworkEventImplementation(int ObjectUID, int FieldUID, T value) {
        this.ObjectUID = ObjectUID;
        this.FieldUID = FieldUID;
        this.value = value;
    }
    
    @Override
    public int getObjectUID() {
        return ObjectUID;
    }

    @Override
    public int getFieldUID() {
        return FieldUID;
    }

    @Override
    public T getValue() {
        return value;
    }

}
