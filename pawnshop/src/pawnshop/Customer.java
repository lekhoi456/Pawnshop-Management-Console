package pawnshop;

/**
 * @author ThinhNT x KhoiLQ
 */
public class Customer {
    // Declare variables
    private String name;              //the name customer
    private String idOrPassport;      //the passport customer
    private String issuedBy;          // the issued by
    private String phoneNumber;       //the phone customer
    private String address;           //the address customer
    private String dateRange;           //the date customer
    private int idCustomer;           //the id customer

    /**
     * constructor null
     */
    public Customer() {
    }

    /**
     * constructor set variables
     *
     * @param name
     * @param idOrPassport
     * @param issuedBy
     * @param phoneNumber
     * @param address
     * @param dateRange
     * @param idCustomer
     */
    public Customer(String name, String idOrPassport, String issuedBy, String phoneNumber, String address, String dateRange, int idCustomer) {
        this.name = name;
        this.idOrPassport = idOrPassport;
        this.issuedBy = issuedBy;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.dateRange = dateRange;
        this.idCustomer = idCustomer;
    }


    /**
     * Gets name
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name for customer
     *
     * @param name
     * @throws CustomerException
     */
    public void setName(String name) throws CustomerException {
        if (name.equals("")) {
            throw new CustomerException("Name can't be empty!");
        } else {
            this.name = name;
        }
    }

    /**
     * set Id or passport
     *
     * @param idOrPassport
     * @throws CustomerException
     */
    public void setIdOrPassport(String idOrPassport) throws CustomerException {
        if (idOrPassport.equals("")) {
            throw new CustomerException("Customer ID or Passport can't be empty!");
        } else {
            this.idOrPassport = idOrPassport;
        }
    }

    /**
     * get Id or passport
     *
     * @return
     */
    public String getIdOrPassport() {
        return idOrPassport;
    }

    /**
     * Gets address
     *
     * @return
     * @throws CustomerException
     */
    public String getAddress() {
        return address;
    }


    /**
     * Gets IssuedBy
     *
     * @return
     */
    public String getIssuedBy() {
        return issuedBy;
    }

    /**
     * Sets issuedBy
     *
     * @param issuedBy
     */
    public void setIssuedBy(String issuedBy) throws CustomerException {
        if (issuedBy.equals("")) {
            throw new CustomerException("Registered Place can't be empty");
        } else {
            this.issuedBy = issuedBy;
        }
    }

    /**
     * Gets mean of phoneNumber
     *
     * @return
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets phoneNumber for customer
     *
     * @param phoneNumber
     * @throws CustomerException
     */

    public void setPhoneNumber(String phoneNumber) throws CustomerException {
        if (phoneNumber.equals("")) {
            throw new CustomerException("Phone number can't be empty");
        } else {
            this.phoneNumber = phoneNumber;
        }
    }


    /**
     * Sets address of customer
     *
     * @param address
     * @throws CustomerException
     */
    public void setAddress(String address) throws CustomerException {
            this.address = address;
    }

    /**
     * Gets dateRange
     *
     * @return
     */
    public String getDateRange() {
        return dateRange;
    }

    /**
     * Sets dataRange for customer
     *
     * @param dateRange
     * @throws CustomerException
     */
    public void setDateRange(String dateRange) throws CustomerException {
        if (dateRange.equals("")) {
            throw new CustomerException("Date range can't be empty");
        } else {
            this.dateRange = dateRange;
        }
    }

    /**
     * gets customer id
     *
     * @return
     */
    public int getIdCustomer() {
        return idCustomer;
    }

    /**
     * Sets foreign key customer id for customer
     *
     * @param idCustomer
     * @throws CustomerException
     */
    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }


    /**
     * show the customer
     *
     * @return
     */
    @Override
    public String toString() {
        return String.format("- Customer ID: %d\n- Name: %s\n- ID or Passport: %s\n- Issued Date : %s\n- Registered Place: %s\n- Phone: %s\n- Address: %s\n", this.idCustomer, this.name, this.idOrPassport, this.dateRange, this.issuedBy, this.phoneNumber, this.address);
    }
}