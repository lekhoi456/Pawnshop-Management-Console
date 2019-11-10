package pawnshop;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author Thinh
 */
public class CustomerManagement {
    private String CU_FILE;                  //The URL of data file that stores all customers
    private int numberOfCustomer;             //Number � customers that srored in data file
    private ArrayList<Customer> customer = new ArrayList<>();     //All instances of customer

    /**
     * Creates instance for Customer management
     *
     * @throws CustomerException
     */
    public CustomerManagement(String name, String idOrPassport, String phoneNumber, String address, Date dateRange, int contractId, int idCustomer) throws CustomerException {
        if (CU_FILE.equals("")) {
            throw new CustomerException("The URL of Customer data file can't be empty!");
        } else {
            //Inits the URL of data file that stores Customer bank
            this.CU_FILE = CU_FILE;

            //Creates empty Customer bank
            this.customer = new ArrayList<Customer>();

            //So, the number of Customer is 0
            this.numberOfCustomer = 0;
        }
    }

    public CustomerManagement(String CU_FILE) {
        this.CU_FILE = CU_FILE;
    }

    /**
     * Loads data of customer from data file and stored it into ArrayList
     *
     * @throws IOException
     * @throws CustomerException
     */
    public void loadCustomer() throws IOException {
        File cuFile = new File(CU_FILE);

        //checks is file created
        if (!cuFile.exists()) {
            cuFile.createNewFile(); //if not, creates new file
            System.out.println("The data file customers.txt is not exits. " +
                    "Creating new data file customers.txt... " +
                    "Done!");
            this.numberOfCustomer = 0; //New data file with the number of meaning is 0
        } else {
            //If file is existed, so loading this data file
            System.out.print("The data file customers.txt is found. " +
                    "Data of customers is loading...");

            //Loads text file into buffer
            FileReader fr = new FileReader(cuFile);
            BufferedReader br = new BufferedReader(fr);
            String line;
            if ((line = br.readLine()) == null) return;

            this.numberOfCustomer = Integer.parseInt(line);

            for (int i = 0; i < this.numberOfCustomer; ++i) {
                try {
                    Customer customer = new Customer();
                    customer.setIdCustomer(Integer.parseInt(br.readLine()));
                    customer.setName(br.readLine());
                    customer.setIdOrPassport(br.readLine());
                    customer.setDateRange(br.readLine());
                    customer.setIssuedBy(br.readLine());
                    customer.setPhoneNumber(br.readLine());
                    customer.setAddress(br.readLine());

                    this.customer.add(customer);
                } catch (CustomerException ex) {
                    Logger.getLogger(CustomerManagement.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            br.close();


            System.out.println("Done!");
        }
    }

    public void saveCustomers() throws IOException {
        //Overwrite data file
        FileWriter fw = new FileWriter(new File(CU_FILE));
        BufferedWriter bw = new BufferedWriter(fw);
        try {
            System.out.print("Customer is saving into data file customers.txt... ");

            //Writes number of customer
            bw.write(String.valueOf(this.customer.size()));

            for (int i = 0; i < this.customer.size(); i++) {
                //Inits meaning's information
                String idCustomer = String.valueOf(this.customer.get(i).getIdCustomer());
                String name = this.customer.get(i).getName();
                String address = this.customer.get(i).getAddress();
                String phoneNumber = this.customer.get(i).getPhoneNumber();
                String idOrPassport = this.customer.get(i).getIdOrPassport();
                String issuedBy = this.customer.get(i).getIssuedBy();
                String dataRange = this.customer.get(i).getDateRange();

                //Writes customer's information into data file
                bw.newLine();
                bw.write(idCustomer);

                bw.newLine();
                bw.write(name);

                bw.newLine();
                bw.write(idOrPassport);

                bw.newLine();
                bw.write(dataRange);

                bw.newLine();
                bw.write(issuedBy);

                bw.newLine();
                bw.write(phoneNumber);

                bw.newLine();
                bw.write(address);
            }
        } finally {
            //Saves data file (from RAM into HDD)
            bw.close();
            System.out.println("Done!");
        }
    }

    /**
     * auto generate increase customerId
     *
     * @return
     */
    public int autoId() {
        for (int i = 0; ; ++i) {
            int j;
            for (j = 0; j < customer.size(); ++j) {
                if (customer.get(j).getIdCustomer() == i) break;
            }
            if (j == customer.size()) {
                return i;
            }
        }

    }

    /**
     * add new customer
     *
     * @param cus
     */
    public void addCustomer(Customer cus) {
        cus.setIdCustomer(autoId());
        this.customer.add(cus);
    }

    /**
     * Gets number of customer
     *
     * @return
     */
    public int getSize() {
        return this.numberOfCustomer;
    }

    /**
     * search customer by name or phone number
     *
     * @param ans
     * @return
     */
    public ArrayList<Customer> searchCustomer(String ans) {
        ArrayList<Customer> list = new ArrayList<>();
        for (int i = 0; i < customer.size(); ++i) {
            Customer customer = this.customer.get(i);
            if (customer.getName().equals(ans) ||
                    customer.getPhoneNumber().equals(ans)) {
                list.add(customer);
            }
        }
        return list;
    }

    /**
     * search customer by customerId
     *
     * @param idCustomer
     * @return
     */
    public Customer searchCustomer(int idCustomer) {
        for (int i = 0; i < customer.size(); ++i) {
            Customer customer = this.customer.get(i);
            if (customer.getIdCustomer() == idCustomer) {
                return customer;
            }
        }
        return null;
    }


}