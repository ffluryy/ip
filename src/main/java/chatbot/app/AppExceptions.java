package chatbot.app;

public class AppExceptions {
    public static class ParseException extends RuntimeException {
        public ParseException(String message) {
            super(message);
        }
    }

    public static class InvalidCommandException extends ParseException {
        public InvalidCommandException(String message) {
            super(message);
        }
    }

    public static class InvalidArgumentException extends ParseException {
        public InvalidArgumentException(String message) {
            super(message);
        }
    }

    public static class UserInputException extends Exception {
        public UserInputException(String message) {
            super(message);
        }
    }

    public static class OutOfRange extends Exception {
        public OutOfRange(String message) {
            super(message);
        }
    }
}