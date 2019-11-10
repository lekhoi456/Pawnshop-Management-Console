package pawnshop;

import java.io.*;
import java.util.ArrayList;

/**
 * @author KhoiLQ
 */

public class AccountManagement {
    private String ACC_FILE; // The URL of data file that stores all Account
    private int numberOfAccount; // The number of accounts that stored at data file
    private ArrayList<Account> accounts; // All instances of accounts

    public AccountManagement(String ACC_FILE) throws AccountException {
        if (ACC_FILE.equals("")) {
            throw new AccountException("The URL of Account data file can't be empty!");
        } else {
            // Inits the URL of data file that store account bank
            this.ACC_FILE = ACC_FILE;
            // Create empty account bank
            this.accounts = new ArrayList<Account>();
            // So, the number of account is equal 0
            this.numberOfAccount = 0;
        }
    }

    /**
     * Load data of accounts from data file and store it into ArrayList
     *
     * @throws IOException
     * @throws AccountException
     */
    public void loadAccounts() throws IOException, AccountException {
        File accFile = new File(ACC_FILE);

        // Checks the status file create or not
        if (!accFile.exists()) {
            accFile.createNewFile(); // If not, create a new file
            System.out.println("The data file accounts.txt is not exists." +
                    "Creating the new file accounts.txt..." +
                    "Done!");
            this.numberOfAccount = 0; // New data file with the number of account is equal 0
        } else {
            // If file is exists, loading this data file
            System.out.print("The data file accounts.txt is found. " +
                    "Data of accounts is loading...");
            // Load text file into buffer
            try (BufferedReader br = new BufferedReader(new FileReader(ACC_FILE))) {
                String line, userId, password;
                // Read the number of accounts
                line = br.readLine();
                this.numberOfAccount = Integer.parseInt(line);

                for (int i = 0; i < this.numberOfAccount; i++) {
                    // Read the account infomation
                    userId = br.readLine();
                    password = br.readLine();

                    // Create new instance of Account and adds to account bank
                    this.accounts.add(new Account(userId, password));
                }
            }
            System.out.println("Done!");
        }
    }

    /**
     * add new account
     *
     * @param account
     */
    public void addAccount(Account account) {
        this.accounts.add(account);
    }

    public void changePassword(String userId, String password) throws IOException, AccountException {
        boolean isExisted = false;
        int size = accounts.size();
        for (int i = 0; i < size; i++) {
            if (accounts.get(i).getUserId() == userId) {
                isExisted = true;
                accounts.get(i).setPassword(password);
                break;
            }
        }
        if (!isExisted) {
            System.out.println("userId: " + userId + " not existed.\n");
        } else {
            saveAccounts();
        }
    }

    /**
     * Save ArrayList<Account> into data file
     *
     * @throws IOException
     */
    public void saveAccounts() throws IOException {
        //Overwrite data file
        FileWriter fw = new FileWriter(new File(ACC_FILE), false);
        BufferedWriter bw = new BufferedWriter(fw);
        try {
            System.out.print("\nAccount is saving into data file vehicles.txt...");

            //Writes number of Vehicle
            bw.write(String.valueOf(this.accounts.size()));

            for (int i = 0; i < this.accounts.size(); i++) {
                //Inits Vehicle's information
                String userId = this.accounts.get(i).getUserId();
                String password = this.accounts.get(i).getPassword();

                //Writes Vehicle's information into data file
                bw.newLine();
                bw.write(userId);
                bw.newLine();
                bw.write(password);
            }
        } finally {
            //Saves data file (from RAM into HDD)
            bw.close();
            System.out.println("Done!");
        }
    }


    /**
     * Find account by userId and return the index of this userId
     *
     * @param userInput
     * @return
     */
    public int searchAccount(String userInput) {
        for (int i = 0; i < this.accounts.size(); i++) {
            Account acc = this.accounts.get(i);
            if (acc.getUserId().equals(userInput)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Gets number of accounts
     *
     * @return
     */
    public int getSize() {
        return this.numberOfAccount;
    }

    /**
     * checkk the account is valid or not
     *
     * @param account
     * @return
     */
    public boolean checkAccount(Account account) {
        for (Account x : accounts) {
            if (x.getUserId().equals(account.getUserId()) &&
                    x.getPassword().equals(account.getPassword())) return true;
        }
        return false;
    }

    /**
     * Show all accounts
     *
     * @return
     */
    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < accounts.size(); i++) {
            Account acc = accounts.get(i);
            str += acc.getUserId() + "\n" + acc.getPassword() + "\n";
        }
        return str;
    }
}
