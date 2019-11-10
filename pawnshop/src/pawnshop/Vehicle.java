package pawnshop;

/**
 * @author KhoiLq
 */

public class Vehicle {
    private int contractId; // the contract id
    private String type; // the type name
    private String licensePlate; // the license plate of vehicle
    private String chassisId; // the chassis of vehicle
    private String enginesId; // the engines id of vehicle

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
     * @throws VehicleException
     */
    public void setContractId(int contractId) throws VehicleException {
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
     * @throws VehicleException
     */
    public void setType(String type) throws VehicleException {
        if (type.equals("")) {
            throw new VehicleException("The Asset name can't be empty!");
        } else {
            this.type = type;
        }
    }

    /**
     * get the License Plate of contract
     *
     * @return
     */
    public String getLicensePlate() {
        return licensePlate;
    }

    /**
     * set the license plate
     *
     * @param licensePlate
     * @throws VehicleException
     */
    public void setLicensePlate(String licensePlate) throws VehicleException {
        if (licensePlate.equals("")) {
            throw new VehicleException("The License plate can't be empty!");
        } else {
            this.licensePlate = licensePlate;
        }
    }

    /**
     * get the chassis id of contract
     *
     * @return
     */
    public String getChassisId() {
        return chassisId;
    }

    /**
     * set the chassis id
     *
     * @param chassisId
     * @throws VehicleException
     */
    public void setChassisId(String chassisId) throws VehicleException {
        if (chassisId.equals("")) {
            throw new VehicleException("The Chassis ID can't be empty!");
        } else {
            this.chassisId = chassisId;
        }
    }

    /**
     * get the engines id
     *
     * @return
     */
    public String getEnginesId() {
        return enginesId;
    }

    /**
     * set the engines id
     *
     * @param enginesId
     * @throws VehicleException
     */
    public void setEnginesId(String enginesId) throws VehicleException {
        if (enginesId.equals("")) {
            throw new VehicleException("The Engines ID can't be empty!");
        } else {
            this.enginesId = enginesId;
        }
    }

    /**
     * constructor of vehicle
     *
     * @param contractId
     * @param type
     * @param licensePlate
     * @param chassisId
     * @param enginesId
     */
    public Vehicle(int contractId, String type, String licensePlate, String chassisId, String enginesId) {
        this.contractId = contractId;
        this.type = type;
        this.licensePlate = licensePlate;
        this.chassisId = chassisId;
        this.enginesId = enginesId;
    }

    /**
     * show the vehicle
     *
     * @return
     */
    @Override
    public String toString() {
        return "- Asset name: " + this.type + "\n" +
                "- License Plate: " + this.licensePlate + "\n" +
                "- Chassis Id: " + this.chassisId + "\n" +
                "- Engines Id: " + this.enginesId + "\n";
    }
}
