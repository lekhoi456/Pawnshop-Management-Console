package pawnshop;

public class ContractException extends Exception {
    /**
     * Create new ContractException
     *
     * @param message
     */
    public ContractException(String message) {
        super("Contract Exception: " + message);
    }
}
