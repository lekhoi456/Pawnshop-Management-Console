package pawnshop;

/**
 * @author KhoiLq
 */

public class DeviceException extends Exception {
    /**
     * Create new DeviceException
     *
     * @param message
     */
    public DeviceException(String message) {
        super("Device Exception: " + message);
    }
}
