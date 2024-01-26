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
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class Deposit extends JFrame implements ActionListener{
    
    private BankAccount account;
    
    private JPanel headerPanel;
    private JPanel bodyPanel;
    
    private JLabel headerLabel;
    private JLabel titleLabel;
    private JLabel depositAmountLabel;
    
    private JTextField depositAmountField;
    
    private JButton cancelButton;
    private JButton depositButton;
    
    private GUIComponents components;
    
    public Deposit (BankAccount account) {
        this.account = account;
        
        components = new GUIComponents();
        
        //Frame
        this.setTitle("Deposit");
        this.setSize(350, 300);
        this.setResizable(false);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.getContentPane().setBackground(Color.decode("#0C356A"));
        
        //Header Border
        TitledBorder titledBorder = new TitledBorder("Current Balance");
        titledBorder.setTitleColor(Color.decode("#FFC436"));
        titledBorder.setBorder(new LineBorder(Color.decode("#FFC436"), 2));
        titledBorder.setTitleFont(new Font("Arial", Font.BOLD, 14));
        
        //Header
        headerPanel = components.createJPanel(19, 25, 300, 50, "#0174BE");
        headerPanel.setBorder(titledBorder);
        headerPanel.setLayout(null);
        
        //Reads the Current Balance from the .txt file
        readCurrentBalance();
        
        String balance = String.format("PHP %.2f", account.getAccountBalance());
        headerLabel = components.createJLabel(balance, 10, 17, 250, 25, "#FFF0CE");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerPanel.add(headerLabel);
        
        //Body
        bodyPanel = components.createJPanel(19, 90, 300, 140, "#0174BE");
        bodyPanel.setBorder(new LineBorder(Color.decode("#FFC436"), 2));
        bodyPanel.setLayout(null);
        
        //Title Label - Enter Deposit Amount
        titleLabel = components.createJLabel("Enter Deposit Amount:", 15, 15, 230, 25, "#FFF0CE");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 17));
        
        //Withdrawal Amount Label and TextField
        depositAmountLabel = components.createJLabel("PHP:", 15, 50, 50, 25, "#FFF0CE");
        depositAmountField = components.createJTextField(60, 50, 210, 23, "#FFF0CE", "#0C356A");
        
        //CancelButton
        cancelButton = components.createJButton("Cancel", 15, 90, 100, 25, "#FFC436", "#0174BE");
        cancelButton.addActionListener(this);
        
        //Deposit Button
        depositButton = components.createJButton("Deposit", 170, 90, 100, 25, "#FFC436", "#0174BE");
        depositButton.addActionListener(this);
        
        bodyPanel.add(depositButton);
        bodyPanel.add(cancelButton);
        bodyPanel.add(depositAmountField);
        bodyPanel.add(depositAmountLabel);
        bodyPanel.add(titleLabel);
        
        this.add(bodyPanel);
        this.add(headerPanel);
        this.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancelButton) {
            this.dispose();
        }
        
        if (e.getSource() == depositButton) {
            String amountText = depositAmountField.getText();
            double amount = 0;
            
            if (amountText.trim().isEmpty()) {
                components.createWarningMessage("Please Fill Empty Fields", "Empty Fields!");
            }
            else if (!amountText.matches("^[0-9]+(\\.[0-9]+)?$")) {
                components.createWarningMessage("Please Enter a Valid Amount", "Invalid Deposit Amount!");
            }
            else {
                readCurrentBalance();
                amount = Double.parseDouble(amountText);
                
                try {
                    account.deposit(amount);
                    
                    String fileName = "NewAccount" + account.getAccountNumber() + ".txt";
                    
                    try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
                        writer.println(account.toString());
                    }
                    catch (IOException ex) {
                        components.createErrorMessage("An Error Occured while updating your account balance", "Transaction Error");
                    }
                    
                    headerLabel.setText(String.format("PHP %.2f", account.getAccountBalance()));
                    components.createInformationMessage(String.format("Your New Balance is: PHP %.2f", account.getAccountBalance()), "Withdrawal Successful!");
                    this.dispose();
                }
                catch (IllegalArgumentException ex) {
                    components.createErrorMessage(ex.getMessage(), "Deposit Failed!");
                }
            }
        }
    }
    
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
