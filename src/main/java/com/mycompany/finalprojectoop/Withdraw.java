package com.mycompany.finalprojectoop;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class Withdraw extends JFrame implements ActionListener {
    
    private BankAccount account;
    private Timer timer;
    private int countdown;
    
    private JPanel headerPanel;
    private JPanel bodyPanel;
    
    private JLabel timerLabel;
    private JLabel headerLabel;
    private JLabel titleLabel;
    private JLabel withdrawalAmountLabel;
    
    private JTextField withdrawalAmountField;
    
    private JButton cancelButton;
    private JButton withdrawButton;
    
    private GUIComponents components;

    public Withdraw (BankAccount account) {
        this.account = account;
        
        components = new GUIComponents();
        
        //Frame
        this.setTitle("Withdraw");
        this.setSize(350, 330);
        this.setResizable(false);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.getContentPane().setBackground(Color.decode("#0C356A"));
        
        //Timer Label
        timerLabel = components.createJLabel("Timer: 60secs", 217, 20, 100, 25, "#0174BE");
        timerLabel.setBackground(Color.decode("#FFC436"));
        timerLabel.setOpaque(true);
        timerLabel.setBorder(new LineBorder(Color.decode("#FFF0CE"), 2));
        
        //Timer - 60secs
        countdown = 60;
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                countdown--;
                timerLabel.setText("Timer: " + countdown + "secs");
                
                if (countdown <= 0) {
                    ((Timer) e.getSource()).stop();
                    
                    components.createErrorMessage("Withdrawal Terminated", "Time's Up!");
                    dispose();
                }
            }
        });
        timer.setRepeats(true);
        timer.start();
        
        
        //Header Border
        TitledBorder titledBorder = new TitledBorder("Current Balance");
        titledBorder.setTitleColor(Color.decode("#FFC436"));
        titledBorder.setBorder(new LineBorder(Color.decode("#FFC436"), 2));
        titledBorder.setTitleFont(new Font("Arial", Font.BOLD, 14));
        
        //Header
        headerPanel = components.createJPanel(19, 60, 300, 50, "#0174BE");
        headerPanel.setBorder(titledBorder);
        headerPanel.setLayout(null);
        
        //Reads the Current Balance from the .txt file
        readCurrentBalance();
        
        String balance = String.format("PHP %.2f", account.getAccountBalance());
        headerLabel = components.createJLabel(balance, 10, 17, 250, 25, "#FFF0CE");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerPanel.add(headerLabel);
        
        //Body
        bodyPanel = components.createJPanel(19, 125, 300, 140, "#0174BE");
        bodyPanel.setBorder(new LineBorder(Color.decode("#FFC436"), 2));
        bodyPanel.setLayout(null);
        
        //Title Label - Enter Withdrawal Amount
        titleLabel = components.createJLabel("Enter Withdrawal Amount:", 15, 15, 230, 25, "#FFF0CE");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 17));
        
        //Withdrawal Amount Label and TextField
        withdrawalAmountLabel = components.createJLabel("PHP:", 15, 50, 50, 25, "#FFF0CE");
        withdrawalAmountField = components.createJTextField(60, 50, 210, 23, "#FFF0CE", "#0C356A");
        
        //CancelButton
        cancelButton = components.createJButton("Cancel", 15, 90, 100, 25, "#FFC436", "#0174BE");
        cancelButton.addActionListener(this);
        
        //Withdraw Button
        withdrawButton = components.createJButton("Withdraw", 170, 90, 100, 25, "#FFC436", "#0174BE");
        withdrawButton.addActionListener(this);
        
        bodyPanel.add(withdrawButton);
        bodyPanel.add(cancelButton);
        bodyPanel.add(withdrawalAmountField);
        bodyPanel.add(withdrawalAmountLabel);
        bodyPanel.add(titleLabel);
        
        this.add(bodyPanel);
        this.add(headerPanel);
        this.add(timerLabel);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancelButton) {
            timer.stop();
            this.dispose();
        }
        
        if (e.getSource() == withdrawButton) {
            String amountText = withdrawalAmountField.getText();
            double amount = 0;
            
            // Check if the text field is empty
            if (amountText.trim().isEmpty()) {
                components.createWarningMessage("Please Fill Empty Fields", "Empty Fields!");
            }
            // Check if the input is a valid amount (numeric with an optional decimal part)
            else if (!amountText.matches("^[0-9]+(\\.[0-9]+)?$")) {
                components.createWarningMessage("Please Enter a Valid Amount", "Invalid Withdrawal Amount!");
            }
            else { // The text field contains a valid amount
                // Read the current account balance from the file
                readCurrentBalance();
                amount = Double.parseDouble(amountText);
                
                try {
                    // Attempt the withdrawal operation
                    account.withdraw(amount);
                    
                    String fileName = "NewAccount" + account.getAccountNumber() + ".txt";
                    
                    // Update the account balance in the file
                    try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
                        writer.println(account.toString());
                    }
                    catch (IOException ex) {
                        components.createErrorMessage("An Error Occured while updating your account balance", "Transaction Error");
                    }
                    
                    // Update the displayed balance and show a success message
                    headerLabel.setText(String.format("PHP %.2f", account.getAccountBalance()));
                    components.createInformationMessage(String.format("Your New Balance is: PHP %.2f", account.getAccountBalance()), "Withdrawal Successful!");
                    
                    // Stop the 60-second timer
                    timer.stop();
                    
                    this.dispose();
                }
                catch (IllegalArgumentException ex) {
                    components.createErrorMessage(ex.getMessage(), "Withdrawal Failed!");
                }    
            }
            
        }
    }
    
    //Same as the readCurrentBalance in AccountMenu
    private void readCurrentBalance() {
        File folder = new File(".");
        File[] files = folder.listFiles();
        
        String fileName = "NewAccount" + account.getAccountNumber() + ".txt";
        double currentBalance = 0;
        
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().equals(fileName)) {
                    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                        String line;
                        
                        while ((line = reader.readLine()) != null) {
                            if (line.startsWith("Account Balance: ")) {
                                currentBalance = Double.parseDouble(line.substring("Account Balance: ".length()));
                                break;
                            }
                        }
                        
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
