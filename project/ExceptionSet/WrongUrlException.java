package project.project.ExceptionSet;

public class WrongUrlException extends Exception{
    public WrongUrlException() {
        super();
    }

    public WrongUrlException(Throwable cause) {
        super(cause);
    }

    public WrongUrlException(String message) {
        super(message);
    }

    public WrongUrlException(String message, Throwable cause) {
        super(message, cause);
    }

    protected WrongUrlException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
