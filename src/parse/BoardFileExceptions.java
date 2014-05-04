package pingball.parse;

class ParseException extends RuntimeException {
    public ParseException(String message, String line) {
        super(message + "\n" + "\"" + line + "\"");
    }
}

class InvalidBoardException extends RuntimeException {
    public InvalidBoardException(String message) {
        super(message);
    }
}