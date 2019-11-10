package pawnshop;

import java.io.*;
import java.util.ArrayList;

/**
 * @author KhoiLQ
 */

public class VehicleManagement {
    private String VE_FILE;          //The URL of data file that stores all vehicles
    private int numberOfVehicle;       //Number of vehicles that stored in data file
    private ArrayList<Vehicle> vehicles;  //All instances of vehicles

    /**
     * Creates instance for Vehicle management
     *
     * @param VE_FILE
     * @throws VehicleException
     */
    public VehicleManagement(String VE_FILE) throws VehicleException {
        if (VE_FILE.equals("")) {
            throw new VehicleException("The URL of Vehicle data file can't be empty!");
        } else {
            //Inits the URL of data file that stores Vehicle bank
            this.VE_FILE = VE_FILE;

            //Creates empty Vehicle bank
            this.vehicles = new ArrayList<Vehicle>();

            //So, the number of Vehicle is 0
            this.numberOfVehicle = 0;
        }
    }

    /**
     * Loads data of vehicles from data file and stored it into ArrayList
     *
     * @throws IOException
     * @throws VehicleException
     */
    public void loadVehicles() throws IOException {
        File veFile = new File(VE_FILE);

        //Checks is file created
        if (!veFile.exists()) {
            veFile.createNewFile(); //If not, creates new file
            System.out.print("The data file vehicles.txt is not exits. " +
                    "Creating new data file vehicles.txt... " +
                    "Done!");
            this.numberOfVehicle = 0; //New data file with the number of Vehicle is 0
        } else {
            //If file is existed, so loading this data file
            System.out.print("The data file vehicles.txt is found. " +
                    "Data of vehicles is loading...");

            //Loads text file into buffer
            try (BufferedReader br = new BufferedReader(new FileReader(VE_FILE))) {
                String line, contractId, type, licensePlate, chassisId, enginesId;

                //Reads number of vehicles
                line = br.readLine();
                if (line == null) return;
                this.numberOfVehicle = Integer.parseInt(line);

                for (int i = 0; i < this.numberOfVehicle; i++) {
                    //Reads Vehicle's information
                    contractId = br.readLine();
                    type = br.readLine();
                    licensePlate = br.readLine();
                    chassisId = br.readLine();
                    enginesId = br.readLine();


                    //Create new instance of Vehicle and adds to Vehicle bank
                    this.vehicles.add(new Vehicle(Integer.parseInt(contractId), type, licensePlate, chassisId, enginesId));
                }
            }
            System.out.print("Done!");
        }
    }

    /**
     * Saves Vehicle bank (ArrayList) into data file
     *
     * @throws IOException
     */
    public void saveVehicles() throws IOException {
        //Overwrite data file
        FileWriter fw = new FileWriter(new File(VE_FILE), false);
        BufferedWriter bw = new BufferedWriter(fw);
        try {
            System.out.print("Vehicle is saving into data file vehicles.txt...");

            //Writes number of Vehicle
            bw.write(String.valueOf(this.vehicles.size()));

            for (int i = 0; i < this.vehicles.size(); i++) {
                //Inits Vehicle's information
                int contractId = this.vehicles.get(i).getContractId();
                String type = this.vehicles.get(i).getType();
                String licensePlate = this.vehicles.get(i).getLicensePlate();
                String chassisId = this.vehicles.get(i).getChassisId();
                String enginesId = this.vehicles.get(i).getEnginesId();

                //Writes Vehicle's information into data file
                bw.newLine();
                bw.write(String.valueOf(contractId));
                bw.newLine();
                bw.write(type);
                bw.newLine();
                bw.write(licensePlate);
                bw.newLine();
                bw.write(chassisId);
                bw.newLine();
                bw.write(enginesId);

            }
        } finally {
            //Saves data file (from RAM into HDD)
            bw.close();
            System.out.println("Done!");
        }
    }

    public void deleteVehicles(int id) throws IOException {
        Vehicle vehicle = null;
        int size = vehicles.size();
        for (int i = 0; i < size; i++) {
            if (vehicles.get(i).getContractId() == id) {
                vehicle = vehicles.get(i);
                break;
            }
        }
        if (vehicle != null) {
            vehicles.remove(vehicle);
            saveVehicles();
        }
    }

    /**
     * Finds Vehicle by Vehicle id and return the index of this Vehicle
     *
     * @param contractId
     * @return
     */
    public int findVehicle(int contractId) {
        for (int i = 0; i < this.vehicles.size(); i++) {
            Vehicle v = this.vehicles.get(i);
            if (v.getContractId() == contractId) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Finds the Vehicle instance by Vehicle id
     *
     * @param contractId
     * @return
     */
    public Vehicle getVehicle(int contractId) {
        int idx = this.findVehicle(contractId);
        if (idx == -1) {
            return null;
        } else {
            return this.vehicles.get(idx);
        }
    }

    /**
     * Gets number of vehicles
     *
     * @return
     */
    public int getSize() {
        return this.numberOfVehicle;
    }

    public void addVehicle(Vehicle vehicle) {
        this.vehicles.add(vehicle);
    }

}
