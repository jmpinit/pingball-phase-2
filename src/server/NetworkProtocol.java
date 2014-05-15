package server;

import java.util.HashMap;
import java.util.Map;

public class NetworkProtocol {
    private final static int BYTES_INT = 4;
    private final static int BYTES_LONG = 8;
    
    public final static int MESSAGE_LENGTH = BYTES_INT*3 + BYTES_LONG;
    public final static byte[] PREAMBLE = new byte[] { Byte.MAX_VALUE, Byte.MIN_VALUE, Byte.MAX_VALUE };
            
    private static int uid = 0;
    
    /**
     * @return a uniquely identifying integer
     * (only up to MAX_INTEGER number of calls)
     */
    public static int getUID() {
        return uid++;
    }
    
    /***
     * A NetworkState is condensed representation of an object's current state.
     * @author pkalluri
     *
     */
    public static class NetworkState {
        private Field[] fields;
        
        private static Map<Integer, FieldName> idToFieldName = new HashMap<>();
        static {
            for(FieldName n: FieldName.values())
                idToFieldName.put(n.getUID(), n);
        }

        public static enum FieldName {
            VISIBLE(NetworkProtocol.getUID()),
            X(NetworkProtocol.getUID()),
            Y(NetworkProtocol.getUID()),
            WIDTH(NetworkProtocol.getUID()),
            HEIGHT(NetworkProtocol.getUID()),
            ANGLE(NetworkProtocol.getUID());
            
            private int uid;
            
            FieldName(int uid) {
                this.uid = uid;
            }
            
            public int getUID() {
                return uid;
            }
        }
        
        public NetworkState(Field[] fields) {
            this.fields = fields;
        }
        
        public Field[] getFields() { return fields; }
        
        public static class Field {
            private FieldName fieldName;
            private long value;
            
            public Field(FieldName fieldName, long value) {
                this.fieldName = fieldName;
                this.value = value;
            }
            
            public Field(int fieldID, long value) {
                if(idToFieldName.containsKey(fieldID)) {
                    this.fieldName = idToFieldName.get(fieldID);
                    this.value = value;
                } else {
                    throw new RuntimeException("Invalid fieldID " + fieldID + ".");
                }
            }
            
            public FieldName getFieldName() {
                return fieldName;
            }
            
            public long getValue() {
                return value;
            }
        }
    }

    /***
     * A Network Serializable is an object that maintains all the necessary architecture
     * to facilitate NetworkEvents easily being made about it.
     * 
     * @author pkalluri
     *
     */
    public interface NetworkSerializable {
        
        public NetworkState getState();
        
        public int getInstanceUID();
        
        public int getStaticUID();
    }

    /***
     * A Network Event is an update containing info on a type of object, specific object,
     * specific field, and that field's new value,
     * all condensed via unique IDs.
     *  
     * @author pkalluri
     *
     */
    public static class NetworkEvent {
        int typeUID;
        int instanceUID;
        int fieldUID;
        long value;
        
        public NetworkEvent(int typeUID, int instanceUID, int fieldUID, long value) {
            this.typeUID = typeUID;
            this.instanceUID = instanceUID;
            this.fieldUID = fieldUID;
            this.value = value;
        }
        
        public int getTypeUID() {
            return typeUID;
        }
        public int getInstanceUID() {
            return instanceUID;
        }

        public int getFieldUID() {
            return fieldUID;
        }

        public long getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "{ type: " + typeUID + ", instance: " + instanceUID + ", field: " + fieldUID + ", value: " + value + "}";
        }
    }
}