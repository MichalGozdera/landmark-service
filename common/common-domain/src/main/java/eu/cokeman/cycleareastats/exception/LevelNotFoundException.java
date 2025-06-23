package eu.cokeman.cycleareastats.exception;

public class LevelNotFoundException extends RuntimeException{

    public LevelNotFoundException(String message) {
        super(message);
    }

    public LevelNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
