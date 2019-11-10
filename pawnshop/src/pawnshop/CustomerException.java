package pawnshop;

/**
 * @author KhoiLq
 */

public class CustomerException extends Exception {
    /**
     * Create new CustomerException
     *
     * @param message
     */
    public CustomerException(String message) {
        super("Customer Exception: " + message);
    }
}
