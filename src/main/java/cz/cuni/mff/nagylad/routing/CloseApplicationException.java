package cz.cuni.mff.nagylad.routing;

/**
 * Custom exception to handle the closing of the application.
 * This exception is used to terminate the application gracefully and display a closing message.
 */
public class CloseApplicationException extends RuntimeException {

    /** Message to be displayed when the exception is thrown. */
    public String message;

    /**
     * Constructs a CloseApplicationException with a specified message.
     *
     * @param message The message to be displayed when the exception is thrown.
     */
    public CloseApplicationException(String message) {
        super(message); // Pass the message to the RuntimeException constructor.
        this.message = message; // Store the message for additional usage.
    }
}
