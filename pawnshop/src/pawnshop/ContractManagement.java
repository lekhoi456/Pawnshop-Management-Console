package pawnshop;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ContractManagement {
    private String C_FILE;
    private int numberOfContract;
    public ArrayList<Contract> contracts;
    private Contract obj = new Contract();

    /**
     * Constructor
     *
     * @param C_FILE
     * @throws ContractException
     */
    public ContractManagement(String C_FILE) throws ContractException {
        if (C_FILE.trim().equals("")) {
            throw new ContractException("The path is invalid!");
        } else {
            this.C_FILE = C_FILE;
            this.contracts = new ArrayList<>();
            this.numberOfContract = 0;
        }
    }

    /**
     * Read data from contracts.txt file
     *
     * @throws IOException
     * @throws ContractException
     */
    public void loadContracts() throws IOException, ContractException {
        File ctFile = new File(C_FILE);

        if (!ctFile.exists()) {
            ctFile.createNewFile();
            System.out.print("The data file contracts.txt is not exits. " +
                    "Creating new data file contracts.txt... " +
                    "Done!\n");
            this.numberOfContract = 0;
        } else {
            System.out.print("The data file contracts.txt is found. " +
                    "Data of contracts is loading...");
            try (BufferedReader br = new BufferedReader(new FileReader(ctFile))) {
                String s;

                while ((s = br.readLine()) != null) {
                    Contract c = new Contract();
                    c.setAmountOfMoney(Integer.parseInt(s));

                    s = br.readLine();
                    c.setContractId(Integer.parseInt(s));

                    s = br.readLine();
                    c.setIdCustomer(Integer.parseInt(s));

                    s = br.readLine();
                    c.setNote(s);

                    s = br.readLine();
                    c.setStartDatePawn(s);

                    s = br.readLine();
                    c.setUserId(s);

                    s = br.readLine();
                    c.setTimeForm(s);

                    s = br.readLine();
                    c.setCycles(Integer.parseInt(s));

                    s = br.readLine();
                    c.setStatus(Integer.parseInt(s));

                    s = br.readLine();
                    c.setInterestRate(Float.parseFloat(s));
                    contracts.add(c);
                }
            }

            System.out.println("Done!");
        }
    }

    /**
     * Write data into contracts.txt
     *
     * @throws IOException
     */

    public void saveContracts() throws IOException {
        //Overwrite data file
        FileWriter fw = new FileWriter(new File(C_FILE));
        BufferedWriter bw = new BufferedWriter(fw);
        try {
            System.out.print("\nContract is saving into data file contracts.txt... ");

            for (int i = 0; i < this.contracts.size(); i++) {
                //Inits meaning's information
                String amountOfMoney = String.valueOf(this.contracts.get(i).getAmountOfMoney());
                String contractId = String.valueOf(this.contracts.get(i).getContractId());
                String idCustomer = String.valueOf(this.contracts.get(i).getIdCustomer());
                String note = this.contracts.get(i).getNote();
                String startDatePawn = this.contracts.get(i).getStartDatePawn();
                String userId = this.contracts.get(i).getUserId();
                String timeForm = this.contracts.get(i).getTimeForm();
                String cycles = String.valueOf(this.contracts.get(i).getCycles());
                String status = String.valueOf(this.contracts.get(i).getStatus());
                String interestRate = String.valueOf(this.contracts.get(i).getInterestRate());

                bw.write(amountOfMoney);

                bw.newLine();
                bw.write(contractId);

                bw.newLine();
                bw.write(idCustomer);

                bw.newLine();
                bw.write(note);

                bw.newLine();
                bw.write(startDatePawn);

                bw.newLine();
                bw.write(userId);

                bw.newLine();
                bw.write(timeForm);

                bw.newLine();
                bw.write(cycles);

                bw.newLine();
                bw.write(status);

                bw.newLine();
                bw.write(interestRate);
                bw.newLine();
            }
        } finally {
            //Saves data file (from RAM into HDD)
            bw.close();
            System.out.println("Done!");
        }
    }

    public void deleteContract(int id) throws IOException {
        Contract contract = null;
        int size = contracts.size();
        for (int i = 0; i < size; i++) {
            if (contracts.get(i).getContractId() == id) {
                contract = contracts.get(i);
                break;
            }
        }
        if (contract != null) {
            contracts.remove(contract);
            saveContracts();
        }
    }


    /**
     * Get the number of contracts
     *
     * @return
     */
    public int getSize() {
        return this.numberOfContract;
    }

    /**
     * Find a contract in the list
     *
     * @param contractId
     * @return
     */
    public Contract search(int contractId) {
        for (Contract c : contracts) {
            if (c.getContractId() == contractId) return c;
        }
        return null;
    }

    /**
     * Delete a contract in the list
     *
     * @param contractId
     * @throws ContractException
     */
    public void detele(int contractId) throws ContractException {
        Contract c = search(contractId);
        if (c == null) throw new ContractException("Contract not found! Could not delete!");
        else {
            contracts.remove(c);
        }
    }

    /**
     * auto increase contract id
     *
     * @return
     */
    public int autoContracId() {
        for (int i = 0; ; ++i) {
            int j;
            for (j = 0; j < contracts.size(); ++j) {
                if (contracts.get(j).getContractId() == i) break;
            }
            if (j == contracts.size()) return i;
        }
    }

    /**
     * add contract
     *
     * @param contract
     */
    public void addContract(Contract contract) {
        this.contracts.add(contract);
    }

    /**
     * search contract by customer id
     *
     * @param idCustomer
     * @return
     */
    public ArrayList<Contract> searchByCustomerID(int idCustomer) {
        ArrayList<Contract> list = new ArrayList<>();
        for (int i = 0; i < contracts.size(); ++i) {
            if (contracts.get(i).getIdCustomer() == idCustomer) list.add(contracts.get(i));
        }
        return list;
    }

    /**
     * get list contract is due
     *
     * @return
     */
    public ArrayList<Contract> getListContractIsDue() {
        ArrayList<Contract> list = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        for (Contract x : contracts) {
            if (cal.getTime().compareTo(x.getDateEnd().getTime()) >= 0 && x.getStatus() == 0) list.add(x);
        }
        return list;
    }

    /**
     * get list contract from date1 to date2
     *
     * @param date1
     * @param date2
     * @return
     */
    public ArrayList<Contract> getListContractDateToDate(String date1, String date2) {
        ArrayList<Contract> list = new ArrayList<>();
        Date d1 = null, d2 = null;
        try {
            d1 = new SimpleDateFormat("dd/MM/yyyy").parse(date1);
            d2 = new SimpleDateFormat("dd/MM/yyyy").parse(date2);
        } catch (ParseException ex) {
        }

        for (Contract x : contracts) {
            if (x.getDateStart().getTime().compareTo(d1) >= 0 &&
                    x.getDateStart().getTime().compareTo(d2) <= 0) list.add(x);
        }
        return list;
    }

    /**
     * get list contract was done
     *
     * @return
     */
    public ArrayList<Contract> getListContractWasDone() {
        ArrayList<Contract> list = new ArrayList<>();
        for (Contract x : contracts) {
            if (x.getStatus() == 1) list.add(x);
        }
        return list;
    }
}
