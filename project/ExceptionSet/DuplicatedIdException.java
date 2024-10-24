package project.project.ExceptionSet;

public class DuplicatedIdException extends Exception{
    public DuplicatedIdException() {
        super();
    }

    public DuplicatedIdException(Throwable cause) {
        super(cause);
    }

    public DuplicatedIdException(String message) {
        super(message);
    }

    public DuplicatedIdException(String message, Throwable cause) {
        super(message, cause);
    }

    protected DuplicatedIdException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
