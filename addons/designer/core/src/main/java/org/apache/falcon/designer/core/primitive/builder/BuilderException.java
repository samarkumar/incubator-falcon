package org.apache.falcon.designer.core.primitive.builder;

/**
 * Exception thrown while building a primitive
 */
public class BuilderException extends Exception {
    /**
     * Constructs a default exception with no cause or message.
     */
    public BuilderException() {
        super();
    }

    /**
     * Constructs an exception with a specific message.
     * @param message
     *            - Message on the exception
     */
    public BuilderException(String message) {
        super(message);
    }

    /**
     * Constructs an exception with a specific message and cause.
     * @param message
     *            - Message on the exception
     * @param cause
     *            - Underlying exception that resulted in this being thrown
     */
    public BuilderException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs an exception with a cause and message is initialized to be
     * same as that of the cause.
     * @param cause
     *            - Underlying exception that resulted in this being thrown
     */
    public BuilderException(Throwable cause) {
        super(cause);
    }
}
