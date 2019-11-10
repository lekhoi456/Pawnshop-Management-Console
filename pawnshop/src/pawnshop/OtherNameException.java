package pawnshop;

/**
 * @author KhoiLq
 */

public class OtherNameException extends Exception {
    /**
     * Create new OtherNameException
     *
     * @param message
     */
    public OtherNameException(String message) {
        super("OtherName Exception: " + message);
    }
}
