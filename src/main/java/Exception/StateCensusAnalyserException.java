package Exception;
// CLASS FOR CUSTOM EXCEPTIONS
public class StateCensusAnalyserException extends Exception {
        public enum ExceptionType {
            NO_SUCH_FILE, NO_SUCH_FILE_TYPE, NO_SUCH_FILE_HEADER
        }

        public ExceptionType type;

        public StateCensusAnalyserException(ExceptionType type, String message) {
            super(message);
            this.type = type;
        }

        public StateCensusAnalyserException(ExceptionType type, Throwable cause) {
            super(cause);
            this.type = type;
        }

        public StateCensusAnalyserException(ExceptionType type, String message, Throwable cause) {
            super(message, cause);
            this.type = type;
        }
    }