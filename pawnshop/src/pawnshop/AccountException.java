package pawnshop;

/**
 * @author KhoiLq
 */

public class AccountException extends Exception {
    /**
     * Create new AccountException
     *
     * @param message
     */
    public AccountException(String message) {
        super("Account Exception: " + message);
    }
}
