package pawnshop;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author KhoiLQ
 */


public class Application {

    private static AccountManagement accm;
    private static CustomerManagement cusm;
    private static VehicleManagement vehm;
    private static DeviceManagement devm;
    private static OtherNameManagement othm;
    private static ContractManagement conm;

    /**
     * Main Program
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // Declare Global variables -->
            // Load Account bank
            accm = new AccountManagement("src/data/accounts.txt");
            accm.loadAccounts();
            conm = new ContractManagement("src/data/contracts.txt");
            conm.loadContracts();
            // Load Customer bank
            cusm = new CustomerManagement("src/data/customers.txt");
            cusm.loadCustomer();
            // Load the Vehicle bank
            vehm = new VehicleManagement("src/data/vehicles.txt");
            vehm.loadVehicles();
            // Load the Device bank
            devm = new DeviceManagement("src/data/devices.txt");
            devm.loadDevices();
            // Load other name bank
            othm = new OtherNameManagement("src/data/others.txt");
            othm.loadothers();

            String func = "";

            // matcher and pattern check valid String is positive integer
            Matcher matcher;
            Pattern pattern = Pattern.compile("\\d*");

            //Creates a scanner
            Scanner cin = new Scanner(System.in);
            // <-- Declare Global variables

            //Account Login -->
            Account account = null;
            System.out.println("\n\nWelcome to KhanhCa Pawnshop Management v0.1.8!");
            System.out.println("Copyright @2019 by HKT Group\n");
            System.out.println("---- LOGIN PANEL ----\n");
            String userId = "", password = "";
            do { // check valid username and password
                // Input username
                System.out.print("Username: ");
                userId = cin.nextLine();
                // Input password
                System.out.print("Password: ");
                password = cin.nextLine();
                account = new Account(userId, password);
                if (!accm.checkAccount(account)) {
                    System.out.println("Username or password is incorrect. Please try again!");
                } else { // Display login successfully notification
                    System.out.println("Login successfully!");
                }
            } while (!accm.checkAccount(account));
            // <-- Account Login

            // Main Program -->
            do {

                //Shows menu
                System.out.println("\n---------- KHANHCA PAWNSHOP MANAGEMENT ----------");
                System.out.println("1. Add Contract.");
                System.out.println("2. Search Contract.");
                System.out.println("3. Report.");
                System.out.println("4. Setting.");
                System.out.println("5. Quit.\n");
                // Input option
                System.out.print("Please choose option: ");
                func = cin.nextLine(); // clear buffer
                String strUserEntered = "";
                switch (func) {
                    case "1": // Add Contract -->

                        // Inits variables
                        Customer customer = null;
                        int contractId = conm.autoContracId();
                        Contract contract = new Contract();
                        contract.setContractId(contractId);
                        contract.setUserId(account.getUserId());


                        System.out.println("\n---------- ADD CONTRACT ----------");
                        // Customer Information ->>
                        do { // If add another contract
                            // Declare Variables
                            String customerName, idOrPassport, dateRange, issuedBy, phoneNumber, address;

                            System.out.print("New customer? (y/n): ");
                            strUserEntered = cin.nextLine();
                            if (strUserEntered.equals("n")) { // Old customer
                                String strUserEntered2;
                                do {
                                    // If strUserEntered input wrong
                                    // Load information of old customer
                                    System.out.println("Search by:" +
                                            "\n1. ID's Customer." +
                                            "\n2. Name or Phone number." +
                                            "\n3. Close.");
                                    System.out.print("Please choose option: ");
                                    strUserEntered2 = cin.nextLine();

                                    // search for ID customer
                                    if (strUserEntered2.equals("1")) {
                                        String check = "";
                                        do { // If search customer information is not correct
                                            int id;
                                            do {
                                                System.out.print("Please enter ID Customer: ");
                                                id = cin.nextInt();
                                                cin.nextLine();
                                                if (id < 0) {
                                                    System.out.println("The ID Customer " +
                                                            "must be greater or equal than 0!");
                                                }
                                            } while (id < 0);
                                            customer = cusm.searchCustomer(id);

                                            // display customer's information
                                            if (customer != null) {
                                                System.out.println("\n--- CUSTOMER'S INFORMATION ---");
                                                System.out.println(customer.toString() + "\n");
                                                System.out.print("Is that correct? (y/n): ");
                                                check = cin.nextLine();
                                                if (check.equals("n")) {
                                                    System.out.println("Please try again!");
                                                } else if (!(check.trim().equals("n") || check.trim().equals("y"))) {
                                                    System.out.println("You must type 'y' or 'n'. Please try again!");
                                                }
                                            } else {
                                                System.out.println("Error! No customer's information found for Customer ID: " + id + "!");
                                            }
                                        } while (check.equals("n") || (!(check.equals("y"))));

                                        // search for name or phone number
                                    } else if (strUserEntered2.equals("2")) {
                                        String ans;
                                        int index;
                                        ArrayList<Customer> list;
                                        do { // If size list = 0
                                            int z = 0;
                                            System.out.print("Please enter Customer name or Phone number: ");
                                            ans = cin.nextLine();
                                            list = cusm.searchCustomer(ans);
                                            if (list.size() == 0) {
                                                System.out.println("Error! No customer's information found for keyword: '" + ans + "'!");
                                                System.out.println("Please try again!");
                                            } else { // display list customer found with keyword
                                                for (z = 0; z < list.size(); z++) {
                                                    System.out.println(z + 1 + ". ");
                                                    System.out.println(list.get(z).toString());
                                                }
                                            }
                                            if (list.size() > 0) {
                                                do {
                                                    System.out.print("Please enter index: ");
                                                    index = cin.nextInt();
                                                    cin.nextLine();
                                                    if (index < 0 || index > list.size()) {
                                                        if (z == 1) {
                                                            System.out.println("The index must be equal than 1. The index is automatically set to 1!");
                                                            index = 1;
                                                        } else {
                                                            System.out.println("You must be choose from 1 to " + list.size() + ". Please try again!");
                                                        }
                                                    }
                                                } while (index < 0 || index > list.size());
                                                customer = list.get(index - 1); // because index = id+1
                                            }
                                        } while (list.size() == 0);
                                    } else if (strUserEntered2.trim().equals("3")) {
                                        System.out.println("Closing...");
                                    } else {
                                        System.out.println("You must type '1' or '2' or '3'!");
                                    }
                                } while (!(strUserEntered2.equals("1") || strUserEntered2.equals("2") || strUserEntered2.equals("3")));

                                // New customer -> Input data
                            } else if (strUserEntered.equals("y")) {
                                customer = new Customer();

                                do { // customer name can't be empty
                                    System.out.print("Please enter a Customer Name: ");
                                    customerName = cin.nextLine();
                                    if (customerName.trim().equals("")) {
                                        System.out.println("The Customer name can't be empty!");
                                    }
                                } while (customerName.trim().equals(""));
                                customer.setName(customerName);

                                do { // id or passport can't be empty
                                    System.out.print("Please enter Customer's ID or Passport: ");
                                    idOrPassport = cin.nextLine();
                                    if (idOrPassport.trim().equals("")) {
                                        System.out.println("The Customer's ID or Passport can't be empty!");
                                    }
                                } while (idOrPassport.trim().equals(""));
                                customer.setIdOrPassport(idOrPassport);

                                do { // check valid date
                                    System.out.print("Please enter Issued Date (dd/mm/yyyy)I: ");
                                    dateRange = cin.nextLine();
                                    if (DateUtils.isValidDate(dateRange)) {
                                        contract.setStartDatePawn(dateRange);
                                    } else {
                                        System.out.println("Not valid date. Please try again!");
                                    }
                                } while (!DateUtils.isValidDate(dateRange));
                                customer.setDateRange(dateRange);

                                do { // issued by can't be empty
                                    System.out.print("Please enter Registered Place (City/Country): ");
                                    issuedBy = cin.nextLine();
                                    matcher = pattern.matcher(issuedBy);
                                    if (issuedBy.trim().equals("")) {
                                        System.out.println("The Registered Place can't be empty!");
                                    } else if (matcher.matches()) { // matches with positive number format
                                        System.out.println("The Registered Place can't be numbers!");
                                    }
                                } while ((matcher.matches()) || (issuedBy.trim().equals("")));
                                customer.setIssuedBy(issuedBy);

                                do { // phone number must be a positive integer
                                    System.out.print("Please enter Phone number: ");
                                    phoneNumber = cin.nextLine();
                                    matcher = pattern.matcher(phoneNumber);
                                    if (!(matcher.matches())) { // matches with positive number format
                                        System.out.println("The Phone number must be a positive integer number!");
                                    } else if (phoneNumber.trim().equals("")) {
                                        System.out.println("The Phone number can't be empty!");
                                    }
                                } while ((!(matcher.matches())) || (phoneNumber.trim().equals("")));
                                customer.setPhoneNumber(phoneNumber);

                                // Address can be empty
                                System.out.print("Please enter Address: ");
                                address = cin.nextLine();
                                customer.setAddress(address);

                                cusm.addCustomer(customer); // put input in addCustomer class
                            } else { // Invalid input
                                System.out.println("You must choose 'y' or 'n'!");
                            }
                        } while (!(strUserEntered.equals("y") || strUserEntered.equals("n")));
                        // <-- Customer Information

                        contract.setIdCustomer(customer.getIdCustomer()); // set the idCustomer

                        // Item Information ->>
                        String type; // the name of type
                        do { // the name of type
                            System.out.print("Please enter an Assets name: ");
                            type = cin.nextLine();
                            matcher = pattern.matcher(type);
                            if (matcher.matches()) { // matches with positive number format
                                System.out.println("The Assets name can't be numbers!");
                            } else if (type.trim().equals("")) {
                                System.out.println("The Assets name can't be empty!");
                            }
                        } while ((matcher.matches()) || (type.trim().equals("")));

                        String vdo = ""; // save the type (v/d/o)
                        do { // Check valid input
                            System.out.println("List of Types");
                            System.out.println("v - Vehicle");
                            System.out.println("d - Device");
                            System.out.println("o - Other");

                            do {
                                System.out.print("Please enter a type (v/d/o): ");
                                vdo = cin.nextLine();
                                if (vdo.equals("")) {
                                    System.out.println("The type can't be empty!");
                                } else if (vdo.trim().equals("v") || vdo.trim().equals("d") || vdo.trim().equals("o")) {
                                    System.out.print("Press c to continue or e to close: ");
                                    strUserEntered = cin.nextLine();
                                    if (strUserEntered.trim().equals("")) {
                                        System.out.println("The type can't be empty!");
                                    } else if (!(strUserEntered.equals("c") || strUserEntered.equals("e"))) {
                                        System.out.println("You must be type 'c' or 'e'!");
                                    }
                                } else {
                                    System.out.println("You must type 'v' or 'd' or 'o'!");
                                }
                            } while ((!strUserEntered.equals("c")) || (strUserEntered.equals("e")));
                            if (vdo.trim().equals("v")) { // Vehicle
                                // Declare local variables of vehicle
                                String licensePlate, chassisId, enginesId;

                                do {
                                    System.out.print("Please enter License Plate: ");
                                    licensePlate = cin.nextLine();
                                    matcher = pattern.matcher(licensePlate);
                                    if (matcher.matches()) { // matches with positive number format
                                        System.out.println("The License Plate can't be numbers!");
                                    } else if (licensePlate.trim().equals("")) {
                                        System.out.println("The License Plate can't be empty!");
                                    }
                                } while ((matcher.matches()) || (licensePlate.trim().equals("")));

                                // The chassis id can be empty
                                System.out.print("Please enter a Chassis ID: ");
                                chassisId = cin.nextLine();

                                // The engines id can be empty
                                System.out.print("Please enter a Engines ID: ");
                                enginesId = cin.nextLine();

                                vehm.addVehicle(new Vehicle(contractId, type, licensePlate, chassisId, enginesId)); // Add input in Vehicle

                            } else if (vdo.trim().equals("d")) { // Device
                                // declare local variables of device
                                String imei, passcode;

                                // The IMEI can't be empty
                                do {
                                    System.out.print("Please enter IMEI: ");
                                    imei = cin.nextLine();
                                    if (imei.trim().equals("")) {
                                        System.out.println("The IMEI can't be empty!");
                                    }
                                } while (imei.trim().equals(""));

                                // The passcode can be empty
                                System.out.print("Please enter Passcode: ");
                                passcode = cin.nextLine();

                                devm.addDevice(new Device(contractId, type, imei, passcode)); // Add input in Device

                            } else if (vdo.trim().equals("o")) { // Other name
                                String onote; // declare variable of other name

                                // Othername's note can be empty
                                System.out.print("Please enter Asset Note: ");
                                onote = cin.nextLine();

                                othm.addOtherName(new OtherName(contractId, type, onote)); // Add input in Other
                            }
                        } while ((!(vdo.trim().equals("v") || vdo.trim().equals("d") || vdo.trim().equals("o"))));
                        // <-- Item Information

                        // Contract Information ->>
                        // declare variables for contract
                        String timeForm;
                        int cycles, amountOfMoney, status;
                        float interestRate;

                        // Choose the time from
                        do {
                            // display option for user choose
                            System.out.println("Terms of Loan:");
                            System.out.println("d - Day");
                            System.out.println("w - Week");
                            System.out.println("m - Month");

                            do {
                                System.out.print("Please enter Term of Loan (d/w/m): ");
                                timeForm = cin.nextLine();
                                if (timeForm.equals("")) {
                                    System.out.println("The Term of Loan can't be empty!");
                                } else if (timeForm.trim().equals("d") || timeForm.trim().equals("w") || timeForm.trim().equals("m")) {
                                    System.out.print("Press c to continue or e to close: ");
                                    strUserEntered = cin.nextLine();
                                    if (!(strUserEntered.equals("c") || strUserEntered.equals("e"))) {
                                        System.out.println("You must be type 'c' or 'e'!");
                                    }
                                } else {
                                    System.out.println("You must type 'd' or 'w' or 'm'!");
                                }
                            } while ((!strUserEntered.equals("c")) || (strUserEntered.equals("e")));
                        } while ((!(timeForm.trim().equals("d") || timeForm.trim().equals("w") || timeForm.trim().equals("m"))));
                        contract.setTimeForm(timeForm);

                        // input the cycles
                        do {
                            System.out.print("Please enter Credit period: ");
                            cycles = cin.nextInt();
                            cin.nextLine();
                            if (cycles <= 0) {
                                System.out.println("Credit period must be greater than 0!");
                            }
                        } while (cycles <= 0);
                        contract.setCycles(cycles);

                        // input amount of money
                        do {
                            System.out.print("Please enter Loan Amount: ");
                            amountOfMoney = cin.nextInt();
                            cin.nextLine();
                            if (amountOfMoney <= 0) {
                                System.out.println("Loan Amount must be greater than 0!");
                            }
                        } while (amountOfMoney <= 0);
                        contract.setAmountOfMoney(amountOfMoney);

                        // input interest rate
                        do {
                            System.out.print("Please enter Interest Rate: ");
                            interestRate = cin.nextFloat();
                            cin.nextLine();
                            if (interestRate < 0) {
                                System.out.println("Interest Rate must be greater than 0!");
                            }
                        } while (interestRate < 0);
                        contract.setInterestRate(interestRate);


                        // input start date pawn
                        String startDatePawn = "";
                        do {
                            System.out.print("Please enter the Start Date of Contract (dd/mm/yyyy): ");
                            startDatePawn = cin.nextLine();
                            if (DateUtils.isValidDate(startDatePawn)) {
                                contract.setStartDatePawn(startDatePawn);
                            } else {
                                System.out.println("Not valid date. Please try again!");
                            }
                        } while (!DateUtils.isValidDate(startDatePawn));

                        // input note
                        String note;
                        System.out.print("Please enter Contract Note: ");
                        note = cin.nextLine();
                        contract.setNote(note);
                        // <-- Contract Information
                        contract.setContractId(conm.autoContracId());
                        conm.addContract(contract);
                        conm.saveContracts();
                        cusm.saveCustomers();
                        if (vdo.equals("v")) {
                            vehm.saveVehicles();
                        } else if (vdo.equals("d")) {
                            devm.savedevices();
                        } else if (vdo.equals("o")) {
                            othm.saveOthers();
                        }
                        System.out.println("\n--- The Contract has been successfully added to the system ---");

                        break;
                    // <-- Add Contract

                    case "2": // Search Contract and Cash out
                        strUserEntered = "";
                        do { // choose option to search
                            System.out.println("\n---------- SEARCH CONTRACT ----------");
                            System.out.println("1. Search by Contract ID.");
                            System.out.println("2. Search by Customer Name or Phone number.");
                            System.out.println("3. Close.");
                            System.out.print("Enter option: ");
                            strUserEntered = cin.nextLine();

                            if (strUserEntered.trim().equals("1")) {
                                System.out.print("Please enter Contract ID: ");
                                int id = cin.nextInt();
                                cin.nextLine();
                                contract = conm.search(id); // search by contractId
                                if (contract == null) { // if search not found
                                    System.out.println("No contract found with ID: " + id);
                                } else { // found a contract
                                    showContractWithCondition(contract);
                                    // select function
                                    do {
                                        System.out.println("Choose function:" +
                                                "\n1. Pay all to this contract" +
                                                "\n2. Delete contract" +
                                                "\n3. Close.");
                                        System.out.print("Please enter option: ");
                                        strUserEntered = cin.nextLine();
                                        if (strUserEntered.equals("1")) {
                                            char ans;
                                            do {
                                                System.out.print("Do you want to pay (y/n): ");
                                                ans = cin.next().charAt(0);
                                                if (ans == 'y') {

                                                    conm.search(id).setStatus(1);
                                                    conm.saveContracts();
                                                    System.out.println("OK! Payment was DONE!");
                                                } else if (ans == 'n') {
                                                    System.out.println("Contract is pawning!");
                                                }
                                                if (!(ans == 'y' || ans == 'n')) {
                                                    System.out.println("You must choose 'y' or 'n'!");
                                                }
                                            } while (!(ans == 'y' || ans == 'n'));
                                            cin.nextLine();
                                        } else if (strUserEntered.equals("2")) {
                                            conm.deleteContract(id);
                                            vehm.deleteVehicles(id);
                                            devm.deleteDevices(id);
                                            othm.deleteOthers(id);
                                            System.out.println("Delete contract successfully!");
                                        } else if (strUserEntered.equals("3")) {
                                            System.out.println("Closing...");
                                        } else {
                                            System.out.println("You must type '1' or '2' or '3'!");
                                        }
                                    } while ((!(strUserEntered.equals("1") || strUserEntered.equals("2"))) && (!(strUserEntered.equals("3"))));
                                }

                                // Search by name or phone -> List to choose
                            } else if (strUserEntered.equals("2")) {
                                System.out.print("Please enter the keyword: ");
                                String name = cin.nextLine();

                                ArrayList<Customer> list = cusm.searchCustomer(name);
                                if (list.size() == 0) {
                                    System.out.println("Error! No customer's information found for keyword: " + name);
                                } else { // search customer is found
                                    System.out.println("--- LIST CUSTOMER ---");
                                    for (int z = 0; z < list.size(); ++z) {
                                        System.out.println(z + 1 + ". ");
                                        System.out.println(list.get(z).toString());
                                    }

                                    System.out.print("Please enter index: ");
                                    int index = cin.nextInt();
                                    cin.nextLine();
                                    customer = list.get(index - 1);
                                    ArrayList<Contract> listCon = conm.searchByCustomerID(customer.getIdCustomer());
                                    if (listCon.size() == 0) {
                                        System.out.println("Error! Search the contract not found!");
                                    } else {
                                        System.out.println("--- LIST CONTRACT ---");
                                        for (int z = 0; z < listCon.size(); z++) {
                                            if (listCon.get(z).getStatus() == 0) {
                                                System.out.println(z + 1 + ". ");
                                                System.out.println(listCon.get(z).toString());
                                            }
                                        }

                                        System.out.print("Please enter index: ");
                                        index = cin.nextInt();
                                        cin.nextLine();

                                        contract = listCon.get(index - 1);

                                        showContractWithCondition(contract);

                                        System.out.println("Choose function" +
                                                "\n1. Pay all to this contract" +
                                                "\n2. Delete contract" +
                                                "\n3. Close.");
                                        System.out.print("Please enter option: ");
                                        strUserEntered = cin.nextLine();
                                        if (strUserEntered.equals("1")) {
                                            if (contract.getStatus() == 0) {
                                                char ans;
                                                do {
                                                    System.out.print("Do you want to pay (y/n): ");
                                                    ans = cin.next().charAt(0);
                                                    if (ans == 'y') {
                                                        contract.setStatus(1);
                                                        conm.saveContracts();
                                                        System.out.println("OK! Payment was DONE!");
                                                    } else if (ans == 'n') {
                                                        System.out.println("Contract is pawning!");
                                                    }
                                                    if (!(ans == 'y' || ans == 'n')) {
                                                        System.out.println("You must choose 'y' or 'n'!");
                                                    }
                                                } while (!(ans == 'y' || ans == 'n'));
                                            }
                                        } else if (strUserEntered.equals("2")) {
                                            conm.deleteContract(listCon.get(index - 1).getContractId());
                                            vehm.deleteVehicles(listCon.get(index - 1).getContractId());
                                            devm.deleteDevices(listCon.get(index - 1).getContractId());
                                            othm.deleteOthers(listCon.get(index - 1).getContractId());
                                            System.out.println("Delete contract #" + listCon.get(index - 1).getContractId() + " successfully!");
                                        } else if (strUserEntered.equals("3")) {
                                            System.out.println("Closing...");
                                        } else {
                                            System.out.println("\nYou must type '1' or '2' or '3'!\n");
                                        }

                                    }
                                }
                            } else if (strUserEntered.equals("3")) {
                                System.out.println("Closing...");
                            } else { // Display warning message
                                System.out.println("You must choose '1' or '2' or '3'!");
                            }
                        } while ((!(strUserEntered.equals("1") || strUserEntered.equals("2"))) && (!(strUserEntered.equals("3"))));
                        break;

                    //Report
                    case "3":
                        strUserEntered = "";
                        do {
                            // Menu that user choose
                            System.out.println("\n---------- REPORT ----------");
                            System.out.println("1. Display Maturity contracts");
                            System.out.println("2. Display Contracts by Date");
                            System.out.println("3. Display Paid contracts");
                            System.out.println("4. Display Financial Report");
                            System.out.println("5. Display Assets Report");
                            System.out.println("6. Display All Contracts");
                            System.out.println("7 - Close");
                            // Input data
                            System.out.print("Please choose option: ");
                            strUserEntered = cin.nextLine();
                            if (strUserEntered.trim().equals("1")) { // 1 - Display the Contract is Due
                                System.out.println("\n---------- MATURITY CONTRACTS ----------\n");
                                ArrayList<Contract> list = conm.getListContractIsDue();
                                if (list.size() == 0) { // Display warning message
                                    System.out.println("No maturity contracts.\n");
                                } else { // If found, show the contract is due
                                    showListContract(list);
                                }
                            } else if (strUserEntered.trim().equals("2")) { // 2 - Display the Contract by Date

                                String date1 = "";
                                do {
                                    System.out.print("\nDate from (dd/mm/yyyy): ");
                                    date1 = cin.nextLine();
                                    if (!DateUtils.isValidDate(date1)) {
                                        System.out.println("Not valid date. Please try again!");
                                    }
                                } while (!DateUtils.isValidDate(date1));

                                String date2 = "";
                                do {
                                    System.out.print("Date to (dd/mm/yyyy): ");
                                    date2 = cin.nextLine();
                                    if (!DateUtils.isValidDate(date2)) {
                                        System.out.println("Not valid date. Please try again!");
                                    }
                                } while (!DateUtils.isValidDate(date2));

                                System.out.println("\n---------- CONTRACTS FROM " + date1 + " TO " + date2 + " ----------\n");

                                ArrayList<Contract> list = conm.getListContractDateToDate(date1, date2);
                                if (list.size() == 0) {
                                    System.out.println("There are no contracts to display!");
                                } else { // If found, show the contract by date
                                    showListContract(list);
                                }
                            } else if (strUserEntered.equals("3")) { // 3 - Display the Contract was Done
                                System.out.println("---------- PAID CONTRACTS ----------\n");
                                ArrayList<Contract> list = conm.getListContractWasDone();
                                if (list.size() == 0) {
                                    System.out.println("There are no contracts to display!");
                                } else {
                                    showListContract(list);
                                }
                            } else if (strUserEntered.equals("4")) { // 4 - Sale Report
                                long moneyOut = 0, moneyIn = 0;
                                for (Contract x : conm.contracts) {
                                    if (x.getStatus() == 0) moneyOut += x.getAmountOfMoney();
                                    else moneyIn += x.getRealPayment();
                                }
                                System.out.println("\n---------- FINANCIAL REPORT ----------\n");
                                System.out.println("- MONEY OUT: $" + moneyOut);
                                System.out.println("- MONEY IN: $" + moneyIn);

                            } else if (strUserEntered.equals("5")) { //5 - Item Report
                                ArrayList<Device> listD = new ArrayList<>();
                                ArrayList<Vehicle> listV = new ArrayList<>();
                                ArrayList<OtherName> listO = new ArrayList<>();

                                for (Contract x : conm.contracts) {
                                    if (x.getStatus() == 0) {
                                        Device d = devm.getDevice(x.getContractId());
                                        Vehicle v = vehm.getVehicle(x.getContractId());
                                        OtherName o = othm.getOtherName(x.getContractId());

                                        if (d != null) listD.add(d);
                                        if (v != null) listV.add(v);
                                        if (o != null) listO.add(o);
                                    }
                                }

                                System.out.println("\n---------- ASSETS REPORT ----------");
                                System.out.println("\n--- VEHICLE ---");
                                if (listV.size() == 0) {
                                    System.out.println("There are no items to display!");
                                } else {
                                    System.out.println("CONTRACT ID        ASSET NAME       LICENSE PLATE        CHASSIS ID        ENGINES ID");
                                    for (Vehicle x : listV) {
                                        System.out.printf("%-19d%-17s%-21s%-18s%s\n", x.getContractId(), x.getType(), x.getLicensePlate(), x.getChassisId(), x.getEnginesId());
                                    }
                                }

                                System.out.println("\n--- DEVICE ---");
                                if (listD.size() == 0) {
                                    System.out.println("There are no items to display!");
                                } else {
                                    System.out.println("CONTRACT ID        ASSET NAME       IMEI                     PASSCODE");
                                    for (Device d : listD) {
                                        System.out.printf("%-19d%-17s%-25s%s\n", d.getContractId(), d.getType(), d.getImei(), d.getPasscode());
                                    }
                                }

                                System.out.println("\n--- OTHER NAME ---");
                                if (listO.size() == 0) {
                                    System.out.println("There are no items to display!");
                                } else {
                                    System.out.println("CONTRACT ID        ASSET NAME                   NOTE");
                                    for (OtherName o : listO) {
                                        System.out.printf("%-19d%-29s%s\n", o.getContractId(), o.getTypeName(), o.getoNote());
                                    }
                                }

                            } else if (strUserEntered.equals("6")) { // 6 - View All Contract
                                if (conm.contracts.size() == 0) {
                                    System.out.println("(empty)");
                                } else {
                                    System.out.println("---------- VIEW ALL CONTRACTS ----------\n");
                                    System.out.println("\nCONTRACT ID        CUSTOMER NAME              START DATE             LOAN AMOUNT            ACCRUED EXPENSES         PHONE NUMBER        STATUS");
                                    for (Contract x : conm.contracts) {
                                        customer = cusm.searchCustomer(x.getIdCustomer());
                                        System.out.printf("%-19d%-27s%-23s%-23d%-25d%-20s%s\n", x.getContractId(), customer.getName(), x.getStartDatePawn(), x.getAmountOfMoney(), x.getRealPayment(), customer.getPhoneNumber(), x.getStatus() == 0 ? "Not payment" : "Done");
                                    }
                                }
                            } else if (strUserEntered.equals("7")) {
                                break;
                            } else {
                                System.out.println("You must choose 1/2/3/4/5/6/7!");
                            }
                        } while (!strUserEntered.equals("7"));
                        break;

                    case "4":
                        System.out.println("Only the admin account can be open Setting");
                        do {
                            System.out.print("Do you want to continue (y/n): ");
                            strUserEntered = cin.nextLine();
                            if (!(strUserEntered.equals("y") || (strUserEntered.equals("n")))) {
                                System.out.println("You must type 'y' or 'n'!");
                            }
                        } while (!(strUserEntered.equals("y") || strUserEntered.equals("n")));
                        if (strUserEntered.equals("y")) {
                            if (!userId.equals("admin")) {

                                do { // check valid username and password
                                    // Input password
                                    System.out.print("Enter the password for the admin account: ");
                                    password = cin.nextLine();
                                    account = new Account("admin", password);
                                    if (!accm.checkAccount(account)) {
                                        System.out.println("Password is incorrect. Please try again!");
                                    } else { // Display login successfully notification
                                        System.out.println("Logged in successfully!\n");
                                    }
                                } while (!accm.checkAccount(account));
                            }
                            if (accm.checkAccount((account))) {
                                System.out.println("Logged in successfully!\n");
                                System.out.println("---------- SETTING ----------\n");
                                do {
                                    System.out.println("1. Change Password.");
                                    System.out.println("2. Add new Account.");
                                    System.out.println("3. Close.");
                                    System.out.print("Please choose option: ");
                                    strUserEntered = cin.nextLine();
                                    // Change password
                                    if (strUserEntered.equals("1")) {
                                        /*String userId2 = "", password2 = "";
                                        do {
                                            do {
                                                // Input username
                                                System.out.print("Username: ");
                                                userId2 = cin.nextLine();
                                                if (userId2.equals("")) {
                                                    System.out.println("Username can't be empty!");
                                                }
                                            } while (userId2.equals(""));
                                            do {
                                                // Input password
                                                System.out.print("Password: ");
                                                password2 = cin.nextLine();
                                                if (password2.equals("")) {
                                                    System.out.println("The password can't be empty!");
                                                }
                                            } while (password2.equals(""));
                                            if (accm.checkAccount(account)) {
                                                System.out.print("Please enter old password: ");
                                                String pass = cin.nextLine();
                                                if (pass.equals(password)) {
                                                    System.out.print("Please enter new password: ");
                                                    pass = cin.nextLine();
                                                    if (pass.equals("")) {
                                                        System.out.println("The password can't be empty!");
                                                    } else {
                                                        accm.changePassword(userId, pass);
                                                    }
                                                }*/
                                                System.out.println("The function is developing. Please come back soon!");
                                            /*} else {
                                                System.out.println("Account name or password is incorrect. Please try again!");
                                                System.out.println("The function is developing. Please come back soon!");
                                            }
                                        } while (!accm.checkAccount(account));
                                     */
                                    }
                                    /* add a new user */
                                    else if (strUserEntered.equals("2")) {
                                        String userId2 = "", password2 = "";
                                        do {
                                            do {
                                                // Input username
                                                System.out.print("Username: ");
                                                userId2 = cin.nextLine();
                                                if (userId2.equals("")) {
                                                    System.out.println("Username can't be empty!");
                                                }
                                            } while (userId2.equals(""));
                                            do {
                                                // Input password
                                                System.out.print("Password: ");
                                                password2 = cin.nextLine();
                                                if (password2.equals("")) {
                                                    System.out.println("The password can't be empty!");
                                                }
                                            } while (password2.equals(""));
                                            accm.addAccount(new Account(userId2, password2));
                                            accm.saveAccounts();
                                            if (accm.checkAccount(account)) {
                                                System.out.println("Added a new account successfully!");
                                            } else {
                                                System.out.println("Sorry, there was an error in the process of adding new accounts. Please try again!");
                                            }
                                        } while (!accm.checkAccount(account));
                                    } else if (strUserEntered.equals("3")) {
                                        System.out.println("Closing...");
                                    } else {
                                        System.out.println("You must type '1' or '2' or '3'!");
                                    }
                                } while ((!(strUserEntered.equals("1") || strUserEntered.equals("2"))) && (!strUserEntered.equals("3")));
                            }
                        }
                        break;
                    // Exit
                    case "5":
                        System.out.println("Thank for using our software!\n"
                                + "See you again!");
                        break;
                    default:
                        System.out.println("Error. There is no function for this option. You must be choose from 1 to 5!");
                }
            } while (!func.equals("5"));
            // <-- Main Program
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                //save accounts
                accm.saveAccounts();
            } catch (Exception e) {
                System.out.println("Exception: Can't save Accounts!");
            }
            try {
                //save customers
                cusm.saveCustomers();
            } catch (Exception e) {
                System.out.println("Exception: Can't save Customers!");
            }
            try {
                //save vehicles
                vehm.saveVehicles();
            } catch (Exception e) {
                System.out.println("Exception: Can't save Vehicles!");
            }
            try {
                // save devices
                devm.savedevices();
            } catch (Exception e) {
                System.out.println("Exception: Can't save Devices!");
            }
            try {
                // save other names
                othm.saveOthers();
            } catch (Exception e) {
                System.out.println("Exception: can't save Other name");
            }

        }
    }

    /**
     * show list contract with condition (id or name or phone number)
     *
     * @param contract
     */
    private static void showContractWithCondition(Contract contract) {
        System.out.println("--- INFORMATION OF CONTRACT ---");
        Customer cus = cusm.searchCustomer(contract.getIdCustomer()); // search by idCustomer
        System.out.print(cus.toString());
        System.out.print(contract.toString());
        Vehicle vehicle = vehm.getVehicle(contract.getContractId()); // search by contractId
        if (vehicle == null) {
            Device device = devm.getDevice(contract.getContractId());
            if (device == null) {
                OtherName otherName = othm.getOtherName(contract.getContractId());
                System.out.println(otherName.toString());
            } else {
                System.out.println(device.toString());
            }
        } else {
            System.out.print(vehicle.toString());
        }
    }

    /**
     * Show List Contract
     *
     * @param list
     */
    private static void showListContract(ArrayList<Contract> list) {
        Customer customer;
        System.out.println("CONTRACT ID        CUSTOMER NAME              START DATE             LOAN AMOUNT            ACCRUED EXPENSES         PHONE NUMBER");
        for (Contract x : list) {
            customer = cusm.searchCustomer(x.getIdCustomer());
            System.out.printf("%-19d%-27s%-23s%-23d%-25d%s\n", x.getContractId(), customer.getName(), x.getStartDatePawn(), x.getAmountOfMoney(), x.getRealPayment(), customer.getPhoneNumber());
        }
    }

}
