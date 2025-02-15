package cherrybot.exception;

/**
 * Represents an exception CherryBot to handle errors specific to the chatbot.
 */
public class CherryBotException extends Exception {

    /**
     * Constructs a CherryBotException with a specified error message.
     *
     * @param message The detailed error message to be displayed when the exception is thrown.
     */
    public CherryBotException(String message) {
        super(message);
    }

}
