package pawnshop;

import java.io.*;
import java.util.ArrayList;

/**
 * @author KhoiLQ
 */

public class DeviceManagement {
    private String DE_FILE;          //The URL of data file that stores all devices
    private int numberOfDevice;       //Number of devices that stored in data file
    private ArrayList<Device> devices;  //All instances of devices

    /**
     * Creates instance for Device management
     *
     * @param DE_FILE
     * @throws DeviceException
     */
    public DeviceManagement(String DE_FILE) throws DeviceException {
        if (DE_FILE.equals("")) {
            throw new DeviceException("The URL of Device data file can't be empty!");
        } else {
            //Inits the URL of data file that stores Device bank
            this.DE_FILE = DE_FILE;

            //Creates empty Device bank
            this.devices = new ArrayList<Device>();

            //So, the number of Device is 0
            this.numberOfDevice = 0;
        }
    }

    /**
     * Loads data of devices from data file and stored it into ArrayList
     *
     * @throws IOException
     * @throws DeviceException
     */
    public void loadDevices() throws IOException {
        File deFile = new File(DE_FILE);

        //Checks is file created
        if (!deFile.exists()) {
            deFile.createNewFile(); //If not, creates new file
            System.out.print("\nThe data file devices.txt is not exits. " +
                    "Creating new data file devices.txt... " +
                    "Done!");
            this.numberOfDevice = 0; //New data file with the number of Device is 0
        } else {
            //If file is existed, so loading this data file
            System.out.print("\nThe data file devices.txt is found. " +
                    "Data of devices is loading...");

            //Loads text file into buffer
            try (BufferedReader br = new BufferedReader(new FileReader(DE_FILE))) {
                String line, contractId, type, imei, passcode;

                //Reads number of devices
                line = br.readLine();
                if (line == null) return;
                this.numberOfDevice = Integer.parseInt(line);

                for (int i = 0; i < this.numberOfDevice; i++) {
                    //Reads Device's information
                    contractId = br.readLine();
                    type = br.readLine();
                    imei = br.readLine();
                    passcode = br.readLine();

                    //Create new instance of Device and adds to Device bank
                    this.devices.add(new Device(Integer.parseInt(contractId), type, imei, passcode));
                }
            }
            System.out.print("Done!");
        }
    }

    /**
     * Saves Device bank (ArrayList) into data file
     *
     * @throws IOException
     */
    public void savedevices() throws IOException {
        //Overwrite data file
        FileWriter fw = new FileWriter(new File(DE_FILE));
        BufferedWriter bw = new BufferedWriter(fw);
        try {
            System.out.print("Devices is saving into data file devices.txt...");

            //Writes number of Device

            bw.write(String.valueOf(this.devices.size()));

            for (int i = 0; i < this.devices.size(); i++) {
                //Inits Device's information
                int contractId = this.devices.get(i).getContractId();
                String type = this.devices.get(i).getType();
                String imei = this.devices.get(i).getImei();
                String passcode = this.devices.get(i).getPasscode();

                //Writes Device's information into data file
                bw.newLine();
                bw.write(String.valueOf(contractId));
                bw.newLine();
                bw.write(type);
                bw.newLine();
                bw.write(imei);
                bw.newLine();
                bw.write(passcode);
            }
        } finally {
            //Saves data file (from RAM into HDD)
            bw.close();
            System.out.println("Done!");
        }
    }

    public void deleteDevices(int id) throws IOException {
        Device device = null;
        int size = devices.size();
        for (int i = 0; i < size; i++) {
            if (devices.get(i).getContractId() == id) {
                device = devices.get(i);
                break;
            }
        }
        if (device != null) {
            devices.remove(device);
            savedevices();
        }
    }


    /**
     * Adds new Device to Device bank
     *
     * @return
     * @throws DeviceException
     */
    public void addDevice(Device device) {
        this.devices.add(device);
    }

    /**
     * Finds Device by Device id and return the index of this Device
     *
     * @param contractId
     * @return
     */
    public int findDevice(int contractId) {
        for (int i = 0; i < this.devices.size(); i++) {
            Device d = this.devices.get(i);
            if (d.getContractId() == contractId) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Finds the Device instance by Device id
     *
     * @param contractId
     * @return
     */
    public Device getDevice(int contractId) {
        int idx = this.findDevice(contractId);
        if (idx == -1) {
            return null;
        } else {
            return this.devices.get(idx);
        }
    }

    /**
     * Gets number of devices
     *
     * @return
     */
    public int getSize() {
        return this.numberOfDevice;
    }


}

