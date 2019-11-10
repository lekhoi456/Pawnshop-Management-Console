package pawnshop;

import java.io.*;
import java.util.ArrayList;

/**
 * @author KhoiLQ
 */

public class OtherNameManagement {
    private String OT_FILE;          //The URL of data file that stores all others
    private int numberOfOtherName;       //Number of others that stored in data file
    private ArrayList<OtherName> others;  //All instances of others

    /**
     * Creates instance for OtherName management
     *
     * @param OT_FILE
     * @throws OtherNameException
     */
    public OtherNameManagement(String OT_FILE) throws OtherNameException {
        if (OT_FILE.equals("")) {
            throw new OtherNameException("The URL of OtherName data file can't be empty!");
        } else {
            //Inits the URL of data file that stores OtherName bank
            this.OT_FILE = OT_FILE;

            //Creates empty OtherName bank
            this.others = new ArrayList<OtherName>();

            //So, the number of OtherName is 0
            this.numberOfOtherName = 0;
        }
    }

    /**
     * Loads data of others from data file and stored it into ArrayList
     *
     * @throws IOException
     * @throws OtherNameException
     */
    public void loadothers() throws IOException {
        File otFile = new File(OT_FILE);

        //Checks is file created
        if (!otFile.exists()) {
            otFile.createNewFile(); //If not, creates new file
            System.out.print("\nThe data file others.txt is not exits. " +
                    "Creating new data file others.txt... " +
                    "Done!");
            this.numberOfOtherName = 0; //New data file with the number of OtherName is 0
        } else {
            //If file is existed, so loading this data file
            System.out.print("\nThe data file others.txt is found. " +
                    "Data of others is loading...");

            //Loads text file into buffer
            try (BufferedReader br = new BufferedReader(new FileReader(OT_FILE))) {
                String line, contractId, type, note;

                //Reads number of others
                line = br.readLine();
                if (line == null) return;
                this.numberOfOtherName = Integer.parseInt(line);
                System.out.print("Done!");
                for (int i = 0; i < this.numberOfOtherName; i++) {
                    //Reads OtherName's information
                    contractId = br.readLine();
                    type = br.readLine();
                    note = br.readLine();

                    //Create new instance of OtherName and adds to OtherName bank
                    this.others.add(new OtherName(Integer.parseInt(contractId), type, note));
                }


            }

        }
    }

    /**
     * Saves OtherName bank (ArrayList) into data file
     *
     * @throws IOException
     */
    public void saveOthers() throws IOException {
        //Overwrite data file
        FileWriter fw = new FileWriter(new File(OT_FILE));
        BufferedWriter bw = new BufferedWriter(fw);
        try {
            System.out.println("Others is saving into data file others.txt...");

            //Writes number of OtherName
            bw.write(String.valueOf(this.others.size()));

            for (int i = 0; i < this.others.size(); i++) {
                //Inits OtherName's information
                int contractId = this.others.get(i).getContractId();
                String type = this.others.get(i).getTypeName();
                String note = this.others.get(i).getoNote();

                //Writes OtherName's information into data file
                bw.newLine();
                bw.write(String.valueOf(contractId));
                bw.newLine();
                bw.write(type);
                bw.newLine();
                bw.write(note);
            }
        } finally {
            //Saves data file (from RAM into HDD)
            bw.close();
            System.out.println("Done!");
        }
    }

    public void deleteOthers(int id) throws IOException {
        OtherName otherName = null;
        int size = others.size();
        for (int i = 0; i < size; i++) {
            if (others.get(i).getContractId() == id) {
                otherName = others.get(i);
                break;
            }
        }
        if (otherName != null) {
            others.remove(otherName);
            saveOthers();
        }
    }

    /**
     * Adds new OtherName to OtherName bank
     *
     * @return
     * @throws OtherNameException
     */
    public void addOtherName(OtherName ortherName) {
        this.others.add(ortherName);
    }

    /**
     * Finds OtherName by OtherName id and return the index of this OtherName
     *
     * @param contractId
     * @return
     */
    public int findOtherName(int contractId) {
        for (int i = 0; i < this.others.size(); i++) {
            OtherName v = this.others.get(i);
            if (v.getContractId() == contractId) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Finds the OtherName instance by OtherName id
     *
     * @param contractId
     * @return
     */
    public OtherName getOtherName(int contractId) {
        int idx = this.findOtherName(contractId);
        if (idx == -1) {
            return null;
        } else {
            return this.others.get(idx);
        }
    }

    /**
     * Gets number of others
     *
     * @return
     */
    public int getSize() {
        return this.numberOfOtherName;
    }

}

