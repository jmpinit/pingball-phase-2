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
    
    public static class NetworkState {
        private int id;
        private Field[] fields;
        
        public enum FieldName {
            X(getUID()), Y(getUID()),
            WIDTH(getUID()), HEIGHT(getUID()),
            ANGLE(getUID());
            
            private int id;
            
            FieldName(int id) {
                this.id = id;
            }
            
            public int id() {
                return id;
            }
        }
        
        public NetworkState(int id, Field[] fields) {
            this.id = id;
            this.fields = fields;
        }
        
        public int getID() { return id; }
        public Field[] getFields() { return fields; }
        
        public static class Field {
            private FieldName id;
            private long value;
            
            public Field(FieldName id, long value) {
                this.id = id;
                this.value = value;
            }
            
            public FieldName getID() {
                return id;
            }
            
            public long getValue() {
                return value;
            }
        }
    }

    public interface NetworkSerializable {
        public NetworkState getState();
    }

    public interface NetworkEvent<T> {
        public int getObjectUID();
        public int getFieldUID();
        public T getValue();
    }
}