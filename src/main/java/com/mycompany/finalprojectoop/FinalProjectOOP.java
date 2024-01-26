package com.mycompany.finalprojectoop;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class FinalProjectOOP {
    
    static long accountCounter = 2023099999; //Counter for Account Number
    static ArrayList<String> accountsFileName = new ArrayList<>(); //List to store bank accounts filename
    static ArrayList<BankAccount> accounts = new ArrayList<>(); //List to store bank account (BankAccount)
    
    public static void main(String[] args) {
        
        loadBankAccounts();
        addBankAccount();
 
        Login login = new Login();
    }
    
    /**
    * This method scans the current directory for bank account files and loads their account numbers.
    * It updates the 'accountCounter' and populates the 'accountsFileName' list.
    */
    public static void loadBankAccounts() {
        File folder = new File("."); // Create a File object representing the current directory
        File[] files = folder.listFiles(); // List to store files found in the current directory
        
        if (files != null) {
            for (File file : files) {
                // Check if the file name matches the expected pattern for bank account files
                if (file.getName().matches("NewAccount(\\d+)\\.txt")) {
                    String fileName = file.getName();
                    accountsFileName.add(fileName); // Add the file name to the list of account filenames
                    
                    // Extract the account number from the filename
                    long counter = Long.parseLong(fileName.substring("NewAccount".length(), fileName.length() - 4));
                    
                    // Update the 'accountCounter' if a larger account number is found
                    if (counter > accountCounter) {
                        accountCounter = counter;
                    }
                }
            }
        }
    }
    
    /**
    * Reads .txt files in the current directory, extracts account information,
    * creates BankAccount objects, and adds them to an ArrayList.
    */
    public static void addBankAccount() {
        File folder = new File(".");
        File files[] = folder.listFiles();
        
        if (files != null) {
            for (File file : files) { // Check if the file is a .txt file
                if (file.isFile() && file.getName().endsWith(".txt")) {
                    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                        String line;
                        
                        // Variables to store account information
                        String username = "";
                        String password = "";
                        String pincode = "";
                        String firstName = "";
                        String middleName = "";
                        String lastName = "";
                        String birthdate = "";
                        String gender = "";
                        String address = "";
                        String fathersName = "";
                        String mothersName = "";
                        String contactNumber = "";
                        double accountBalance = 0;
                        long accountNumber = 0;
                        
                        while ((line = reader.readLine()) != null) {
                            // Extract account information from the file
                            if (line.startsWith("Username: ")) {
                                username = line.substring("Username: ".length());
                            }
                            else if (line.startsWith("Password: ")) {
                                password = line.substring("Password: ".length());
                            }
                            else if (line.startsWith("Pin Code: ")) {
                                pincode = line.substring("Pin Code: ".length());
                            }
                            else if (line.startsWith("First Name: ")) {
                                firstName = line.substring("First Name: ".length());
                            }
                            else if (line.startsWith("Middle Name: ")) {
                                middleName = line.substring("Middle Name: ".length());
                            }
                            else if (line.startsWith("Last Name: ")) {
                                lastName = line.substring("Last Name: ".length());
                            }
                            else if (line.startsWith("Birthdate: ")) {
                                birthdate = line.substring("Birthdate: ".length());
                            }
                            else if (line.startsWith("Gender: ")) {
                                gender = line.substring("Gender: ".length());
                            }
                            else if (line.startsWith("Address: ")) {
                                address = line.substring("Address: ".length());
                            }
                            else if (line.startsWith("Fathers Name: ")) {
                                fathersName = line.substring("Fathers Name: ".length());
                            }
                            else if (line.startsWith("Mothers Name: ")) {
                                mothersName = line.substring("Mothers Name: ".length());
                            }
                            else if (line.startsWith("Contact Number: ")) {
                                contactNumber = line.substring("Contact Number: ".length());
                            }
                            else if (line.startsWith("Account Balance: ")) {
                                accountBalance = Double.parseDouble(line.substring("Account Balance: ".length()));
                            }
                            else if (line.startsWith("Account Number: ")) {
                                accountNumber = Long.parseLong(line.substring("Account Number: ".length()));
                            }
                        }
                        // Create a BankAccount object with the extracted information using Polymorhpism
                        BankTransactions account = new BankAccount(username, password, pincode, firstName,
                                                middleName, lastName, birthdate, gender, address, fathersName,
                                                mothersName, contactNumber, accountBalance, accountNumber);
                        
                        //TypeCasting to gain access to the methods of BankAccount
                        BankAccount addAccount = (BankAccount) account; 
                        
                        // Add the account to the 'accounts' list
                        accounts.add(addAccount);
                    }
                    catch (IOException e) {
                        JOptionPane.showMessageDialog(null, "Something went Wrong", "Error!", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    }
}
