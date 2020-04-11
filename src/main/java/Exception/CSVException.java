package Exception;
// CLASS FOR CUSTOM CSV EXCEPTIONS
public class CSVException extends Exception {
    public enum ExceptionType {
        NO_SUCH_FILE, INCORRECT_FILE, UNABLE_TO_PARSE, NO_CENSUS_DATA;
    }

    public ExceptionType type;

    public CSVException(ExceptionType type, String message) {
        super(message);
        this.type = type;
    }
    public CSVException(ExceptionType type, Throwable cause) {
        super(cause);
        this.type = type;
    }
    public CSVException(ExceptionType type, String message, Throwable cause) {
        super(message, cause);
        this.type = type;
    }
}