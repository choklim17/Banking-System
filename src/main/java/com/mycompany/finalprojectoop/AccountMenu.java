package com.mycompany.finalprojectoop;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class AccountMenu extends JFrame implements ActionListener {

    private String username;
    private String password;
    
    private long accountNumber;
    
    private JPanel headerPanel;
    private JPanel bodyPanel;
    
    private JLabel headerLabel;
    private JLabel accountNumberLabel;
    
    private JButton logoutButton;
    private JButton withdrawButton;
    private JButton checkBalanceButton;
    private JButton depositButton;
    private JButton checkProfileButton;

    private GUIComponents components;
    
    private BankAccount account;
    
    public AccountMenu(String username, String password, BankAccount account) {
        this.username = username;
        this.password = password;
//        this.firstName = firstName;
        this.account = account;
//        this.accountNumber = accountNumber;

        components = new GUIComponents();
        
        //Frame
        this.setTitle("Main Menu");
        this.setSize(400, 475);
        this.setResizable(false);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.decode("#0C356A"));

        //Logout Button
        logoutButton = components.createJButton("Logout", 268, 20, 100, 25, "#FFC436", "#0174BE");
        logoutButton.setBorder(null);
        logoutButton.addActionListener(this);
        
        //Header
        headerPanel = components.createJPanel(19, 60, 350, 50, "#0174BE");
        headerPanel.setBorder(new LineBorder(Color.decode("#FFC436"), 2));
        headerPanel.setLayout(null);
        
        headerLabel = components.createJLabel("Hello " + account.getFirstName() + "!", 10, 12, 250, 25, "#FFF0CE");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerPanel.add(headerLabel);
        
        //Body
        TitledBorder titledBorder = new TitledBorder("DASHBOARD ");
        titledBorder.setTitleColor(Color.decode("#FFC436"));
        titledBorder.setBorder(new LineBorder(Color.decode("#FFC436"), 2));
        titledBorder.setTitleFont(new Font("Arial", Font.BOLD, 12));
        
        bodyPanel = components.createJPanel(19, 125, 350, 290, "#0174BE");
        bodyPanel.setLayout(null);
        bodyPanel.setBorder(titledBorder);
        
        //Account Number Label
        accountNumberLabel = components.createJLabel("Account Number: " + account.getAccountNumber(), 20, 20, 220, 25, "#FFF0CE");
        accountNumberLabel.setFont(new Font("Arial", Font.BOLD, 14));
        
        //Check Profile Button
        checkProfileButton = components.createJButton("My Profile", 54, 70, 250, 30, "#FFC436", "#0174BE");
        checkProfileButton.setBorder(new LineBorder(Color.decode("#FFF0CE"), 2));
        checkProfileButton.addActionListener(this);
        
        //Check Balance Button
        checkBalanceButton = components.createJButton("Balance Inquiry", 54, 120, 250, 30, "#FFC436", "#0174BE");
        checkBalanceButton.setBorder(new LineBorder(Color.decode("#FFF0CE"), 2));
        checkBalanceButton.addActionListener(this);
        
        //Withdraw Button
        withdrawButton = components.createJButton("Withdraw", 54, 170, 250, 30, "#FFC436", "#0174BE");
        withdrawButton.setBorder(new LineBorder(Color.decode("#FFF0CE"), 2));
        withdrawButton.addActionListener(this);
        
        //Deposit Button
        depositButton = components.createJButton("Deposit", 54, 220, 250, 30, "#FFC436", "#0174BE");
        depositButton.setBorder(new LineBorder(Color.decode("#FFF0CE"), 2));
        depositButton.addActionListener(this);
        
        bodyPanel.add(depositButton);
        bodyPanel.add(withdrawButton);
        bodyPanel.add(checkBalanceButton);
        bodyPanel.add(checkProfileButton);
        bodyPanel.add(accountNumberLabel);
        
        this.add(bodyPanel);
        this.add(headerPanel);
        this.add(logoutButton);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        //Handles Logout Button
        if (e.getSource() == logoutButton) {
            Login login = new Login();
            this.dispose();
        }
        
        //Hanldes Check Profile Button
        if (e.getSource() == checkProfileButton) {
            Profile myProfile = new Profile(account);
        }
        
        //Handles Check Balance Button
        if (e.getSource() == checkBalanceButton) {
            // Read and update the current account balance
            readCurrentBalance();
            
             // Create a message with the current balance
            String currentBalance = String.format("Your Current Balance is: \n PHP %.2f", account.balanceInquiry());
            components.createInformationMessage(currentBalance, "Balance Inquiry");
        }
        
        //Handles Withdraw Button
        if (e.getSource() == withdrawButton) {
            String enteredPinCode = JOptionPane.showInputDialog("Enter Pin code:");
            
            if (enteredPinCode != null && enteredPinCode.equals(account.getPinCode())) {
                // If the entered PIN code is correct
                Withdraw withdraw = new Withdraw(account);
            }
            else {
                // If the entered PIN code is incorrect
                components.createErrorMessage("Incorrect PIN Code. Plese try again", "Invalid PIN Code");
            }
        }
        
        //Handles Deposit Button
        if (e.getSource() == depositButton) {
            String enteredPinCode = JOptionPane.showInputDialog("Enter Pin code:");
            
            if (enteredPinCode != null && enteredPinCode.equals(account.getPinCode())) {
                Deposit deposit = new Deposit(account);
            }
            else {
                components.createErrorMessage("Incorrect PIN Code. Plese try again", "Invalid PIN Code");
            }
        }
    }
    
    private void readCurrentBalance() {
        // Get the list of files in the current directory
        File folder = new File(".");
        File[] files = folder.listFiles();
        
        // Construct the filename based on the account number
        String fileName = "NewAccount" + account.getAccountNumber() + ".txt";
        double currentBalance = 0;
        
        if (files != null) {
            // Loop through the files in the current directory
            for (File file : files) {
                if (file.isFile() && file.getName().equals(fileName)) {
                    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                        String line;
                        
                        // Read each line of the file
                        while ((line = reader.readLine()) != null) {
                            if (line.startsWith("Account Balance: ")) {
                                // Extract the current balance from the line
                                currentBalance = Double.parseDouble(line.substring("Account Balance: ".length()));
                                break; // No need to continue reading after finding the balance
                            }
                        }
                        
                        // Update the account's balance with the retrieved value
                        account.setAccountBalance(currentBalance);
                    }
                    catch (IOException e) {
                        components.createErrorMessage("Something Went Wrong!", "Error!");
                    }
                }
            }
        }
    }
}
