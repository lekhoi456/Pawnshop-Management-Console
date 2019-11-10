package pawnshop;

/**
 * @author KhoiLq
 */

public class VehicleException extends Exception {
    /**
     * Create new VehicleException
     *
     * @param message
     */
    public VehicleException(String message) {
        super("Vehicle Exception: " + message);
    }
}
