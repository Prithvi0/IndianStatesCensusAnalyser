package Exception;
// CLASS FOR CUSTOM EXCEPTIONS
public class StateCensusAnalyserException extends Exception {
        public enum ExceptionType {
            NO_SUCH_FILE, UNABLE_TO_PARSE, NO_CENSUS_DATA, NO_SUCH_HEADER_FILE
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