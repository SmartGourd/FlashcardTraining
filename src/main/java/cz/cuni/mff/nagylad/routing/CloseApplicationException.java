package cz.cuni.mff.nagylad.routing;

public class CloseApplicationException extends RuntimeException {
    public String message;
    public CloseApplicationException(String message) {
        super(message);
        this.message = message;
    }
}
