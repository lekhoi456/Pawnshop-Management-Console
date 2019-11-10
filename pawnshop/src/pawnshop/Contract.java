package pawnshop;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Contract {
    // Declare variables
    private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private Calendar startDate, endDate;
    private String note, startDatePawn;
    private int contractId, idCustomer;
    private String userId;
    private String timeForm;
    private int cycles, amountOfMoney, status = 0, realPayment;
    private float interestRate;
    private long numberOfDay;


    /**
     * constructor set status = 0
     */
    public Contract() {
        this.status = 0;
    }

    /**
     * constructor set another variables
     *
     * @param note
     * @param startDatePawn
     * @param contractId
     * @param idCustomer
     * @param userId
     * @param timeForm
     * @param cycles
     * @param amountOfMoney
     * @param status
     * @param interestRate
     */
    public Contract(String note, String startDatePawn, int contractId, int idCustomer, String userId, String timeForm, int cycles, int amountOfMoney, int status, float interestRate) {
        this.note = note;
        this.startDatePawn = startDatePawn;
        this.contractId = contractId;
        this.idCustomer = idCustomer;
        this.userId = userId;
        this.timeForm = timeForm;
        this.cycles = cycles;
        this.amountOfMoney = amountOfMoney;
        this.status = status;
        this.interestRate = interestRate;
    }

    /**
     * Get date end in the pawn cycle
     *
     * @return
     */
    public Calendar getDateEnd() {
        try {
            this.endDate = Calendar.getInstance();
            this.endDate.setTime(dateFormat.parse(startDatePawn));
        } catch (ParseException ex) {
        }
        if (timeForm.equals("d")) {
            endDate.add(Calendar.DAY_OF_MONTH, cycles);
        } else if (timeForm.equals("m")) {
            endDate.add(Calendar.DAY_OF_MONTH, 30 * cycles);
        } else {
            endDate.add(Calendar.DAY_OF_MONTH, 365 * cycles);
        }
        return this.endDate;
    }

    /**
     * Get day start in the pawn cycle
     *
     * @return
     */
    public Calendar getDateStart() {
        try {
            this.startDate = Calendar.getInstance();
            this.startDate.setTime(dateFormat.parse(startDatePawn));
        } catch (ParseException ex) {
        }
        return this.startDate;
    }

    /**
     * get the total number of day due
     *
     * @return
     */
    public long getNumberOdDayDue() {
        Calendar cal = Calendar.getInstance();
        long number = (cal.getTime().getTime() - getDateEnd().getTime().getTime()) / (1000L * 60L * 60L * 24L);
        if (number >= 0) {
            this.numberOfDay = number;
            return number;
        } else {
            this.numberOfDay = -1;
            return -1;
        }
    }

    /**
     * get the real payment customer must pay to pawnshop
     *
     * @return
     */
    public int getRealPayment() {
        int payment = (int) (this.amountOfMoney * ((this.interestRate / 100) * this.cycles + 1));
        long dayIsDue = getNumberOdDayDue();
        if (dayIsDue > 0) {
            payment += 0.015 * this.amountOfMoney * dayIsDue;
        }
        return payment;
    }

    /**
     * get note of contract
     *
     * @return
     */
    public String getNote() {
        return note;
    }

    /**
     * set note
     *
     * @param note
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * get the start date pawn
     *
     * @return
     */
    public String getStartDatePawn() {
        return startDatePawn;
    }

    /**
     * don't set the start date pawn, because the application must accept valid date, we do not need to check again.
     *
     * @param startDatePawn
     */
    public void setStartDatePawn(String startDatePawn)  {
        /*if (DateUtils.isValidDate(startDatePawn) == true) {
            this.startDatePawn = startDatePawn;
        } else {
            throw new ContractException("Not valid date!");
        }*/
        this.startDatePawn = startDatePawn;


    }

    /**
     * get contract id
     *
     * @return
     */
    public int getContractId() {
        return contractId;
    }

    /**
     * set contract id
     * because it automatically increases
     *
     * @param contractId
     */
    public void setContractId(int contractId) {
        this.contractId = contractId;
    }

    /**
     * get id customer
     *
     * @return
     */
    public int getIdCustomer() {
        return idCustomer;
    }

    /**
     * set id customer
     * because it automatically increases
     *
     * @param idCustomer
     */
    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    /**
     * get user id
     *
     * @return
     */
    public String getUserId() {
        return userId;
    }

    /**
     * set user id
     *
     * @param userId
     */
    public void setUserId(String userId) throws ContractException {
        if (userId.trim().equals("")) {
            throw new ContractException("The user id can't be empty!");
        } else {
            this.userId = userId;
        }
    }

    /**
     * get time form
     *
     * @return
     */
    public String getTimeForm() {
        return timeForm;
    }

    /**
     * set time form
     *
     * @param timeForm
     */
    public void setTimeForm(String timeForm) throws ContractException {
        if (timeForm.trim().equals("")) {
            throw new ContractException("Terms of Loan can't be empty!");
        } else {
            this.timeForm = timeForm;
        }
    }

    /**
     * get cycles
     *
     * @return
     */
    public int getCycles() {
        return cycles;
    }

    /**
     * set cycle
     *
     * @param cycles
     */
    public void setCycles(int cycles) throws ContractException {
        if (cycles <= 0) {
            throw new ContractException("The credit period must be greater than 0!");
        } else {
            this.cycles = cycles;
        }
    }

    /**
     * get amount of money
     *
     * @return
     */
    public int getAmountOfMoney() {
        return amountOfMoney;
    }

    /**
     * set amount of money
     *
     * @param amountOfMoney
     * @throws ContractException
     */
    public void setAmountOfMoney(int amountOfMoney) throws ContractException {
        if (amountOfMoney <= 0) {
            throw new ContractException("The Loan Amount must be greater than 0!");
        } else {
            this.amountOfMoney = amountOfMoney;
        }
    }

    /**
     * get status
     *
     * @return
     */
    public int getStatus() {
        return status;
    }

    /**
     * set status
     * because it automatically generates
     *
     * @param status
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * get interest rate
     *
     * @return
     */
    public float getInterestRate() {
        return interestRate;
    }

    /**
     * set interest rate
     *
     * @param interestRate
     */
    public void setInterestRate(float interestRate) throws ContractException {
        if (interestRate <= 0) {
            throw new ContractException("The interest rate must be greater than 0!");
        } else {
            this.interestRate = interestRate;
        }
    }

    /**
     * show the Contract
     *
     * @return
     */
    @Override
    public String toString() {
        return "- Start Date of Contract: " + this.startDatePawn + "\n" +
                "- Credit period: " + this.cycles + "\n" +
                "- Interest rate: " + this.interestRate + "\n" +
                "- Loan Amount: " + this.amountOfMoney + "\n" +
                "- Accrued Expenses: " + this.getRealPayment() + "\n" +
                "- Contract Maker: " + this.userId + "\n";
    }

}