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
    
    /***
     * A NetworkState is condensed representation of an object's current state.
     * @author pkalluri
     *
     */
    public static class NetworkState {
        private Field[] fields;
        
        public enum FieldName {
            X(getUID()), Y(getUID()),
            WIDTH(getUID()), HEIGHT(getUID()),
            ANGLE(getUID());
            
            private int id;
            
            FieldName(int id) {
                this.id = id;
            }
            
            public int UID() {
                return id;
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
            
            public FieldName getFiedName() {
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

    }
}