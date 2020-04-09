package Exception;
// CLASS FOR CUSTOM STATE CENSUS ANALYSIS EXCEPTIONS
public class StateCensusAnalyserException extends Exception {
        public enum ExceptionType {
            NO_SUCH_FILE, INCORRECT_FILE, UNABLE_TO_PARSE, NO_CENSUS_DATA, NO_CENSUS_STATE_CODE_DATA;
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