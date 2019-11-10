package pawnshop;

/**
 * @author KhoiLq
 */

public class Device {
    // Declare variables
    private int contractId; // the contract id
    private String type; // the type name
    private String imei; // the imei of device
    private String passcode; // the passcode of device

    /**
     * get Contract ID
     *
     * @return
     */
    public int getContractId() {
        return contractId;
    }

    /**
     * set the contract ID
     *
     * @param contractId
     * @throws DeviceException
     */
    public void setContractId(int contractId) throws DeviceException {
        this.contractId = contractId;
    }

    /**
     * get the type name
     *
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     * set type name
     *
     * @param type
     * @throws DeviceException
     */
    public void setType(String type) throws DeviceException {
        if (type.equals("")) {
            throw new DeviceException("The Asset name can't be empty!");
        } else {
            this.type = type;
        }
    }

    /**
     * get imei of device
     *
     * @return
     */
    public String getImei() {
        return imei;
    }

    /**
     * set the imei of device
     *
     * @param imei
     * @throws DeviceException
     */
    public void setImei(String imei) throws DeviceException {
        if (imei.equals("")) {
            throw new DeviceException("The IMEI can't be empty!");
        } else {
            this.imei = imei;
        }
    }

    /**
     * get the passcode of device
     *
     * @return
     */
    public String getPasscode() {
        return passcode;
    }

    public void setPasscode(String passcode) throws DeviceException {
        this.passcode = passcode;
    }

    public Device(int contractId, String type, String imei, String passcode) {
        this.contractId = contractId;
        this.type = type;
        this.imei = imei;
        this.passcode = passcode;
    }

    /**
     * display device
     *
     * @return
     */
    @Override
    public String toString() {
        return "- Asset name: " + this.type + "\n" +
                "- IMEI: " + this.imei + "\n" +
                "- Passcode: " + this.passcode + "\n";
    }
}

