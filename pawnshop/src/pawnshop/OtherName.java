package pawnshop;

/**
 * @author KhoiLq
 */

public class OtherName {
    // Declare variables
    private int contractId; // the contract id
    private String typeName; // the type name
    private String oNote;

    public String getoNote() {
        return oNote;
    }

    public void setoNote(String oNote) {
        this.oNote = oNote;
    }


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
     * @throws OtherNameException
     */
    public void setContractId(int contractId) throws OtherNameException {
        this.contractId = contractId;
    }

    /**
     * get the type name
     *
     * @return
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * set type name
     *
     * @param typeName
     * @throws OtherNameException
     */
    public void setTypeName(String typeName) throws OtherNameException {
        if (typeName.equals("")) {
            throw new OtherNameException("The Asset name can't be empty!");
        } else {
            this.typeName = typeName;
        }
    }

    public OtherName(int contractId, String typeName, String oNote) {
        this.contractId = contractId;
        this.typeName = typeName;
        this.oNote = oNote;
    }

    @Override
    public String toString() {
        return "- Type name: " + this.typeName + "\n" +
                "- Note: " + this.oNote + "\n";
    }

}

