
package com.mycompany.finalprojectoop;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class NewAccount extends JFrame implements ActionListener {
    
    private JPanel headerPanel;
    private JPanel bodyPanel;
    
    private JLabel headerLabel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel pincodeLabel;
    private JLabel firstnameLabel;
    private JLabel middlenameLabel;
    private JLabel lastnameLabel;
    private JLabel birthdateLabel;
    private JLabel genderLabel;
    private JLabel addressLabel;
    private JLabel fathersnameLabel;
    private JLabel mothersnameLabel;
    private JLabel contactNoLabel;
    private JLabel depositLabel;
    private JLabel accountNumberLabel;
    
    private JTextField usernameField;
    private JTextField firstnameField;
    private JTextField middlenameField;
    private JTextField lastnameField;
    private JTextField birthdateField;
    private JTextField addressField;
    private JTextField fathersnameField;
    private JTextField mothersnameField;
    private JTextField contactNoField;
    private JTextField depositField;
    private JTextField accountNumberField;
    
    private JPasswordField passwordField;
    private JPasswordField pincodeField;
    
    private JComboBox genderBox;
    
    private JButton registerButton;
    private JButton exitButton;
    
    private GUIComponents components; 
    
    private BankTransactions account;
    
    public NewAccount() {
        components = new GUIComponents();
        
        //Frame
        this.setTitle("Create a Bank Account");
        this.setSize(400, 635);
        this.setResizable(false);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.decode("#0C356A"));
        
        //Border
        LineBorder lineBorder = new LineBorder(Color.decode("#FFC436"), 2);
        
        //Header
        headerPanel = components.createJPanel(19, 25, 350, 50, "#0174BE");
        headerPanel.setLayout(null);
        headerPanel.setBorder(lineBorder);
        
        headerLabel = components.createJLabel("Let's get you started!", 15, 12, 200, 25, "#FFF0CE");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerPanel.add(headerLabel);
          
        //Body
        bodyPanel = components.createJPanel(19, 90, 350, 485, "#0174BE");
        bodyPanel.setLayout(null);
        bodyPanel.setBorder(lineBorder);
        
        //Username Label and Field
        usernameLabel = components.createJLabel("Username:", 20, 15, 100, 25, "#FFF0CE");
        usernameField = components.createJTextField(120, 15, 200, 23, "#FFF0CE", "#0C356A");
        
        //Password Label and Field
        passwordLabel = components.createJLabel("Password:", 20, 45, 100, 25, "#FFF0CE");
        passwordField = components.createJPasswordField(120, 45, 200, 23, "#FFF0CE", "#0C356A");
        
        //Pincode Label and Field
        pincodeLabel = components.createJLabel("Pin Code:", 20, 75, 100, 25, "#FFF0CE");
        pincodeField = components.createJPasswordField(120, 75, 200, 23, "#FFF0CE", "#0C356A");
        
        //First Name Label and Field
        firstnameLabel = components.createJLabel("First Name:", 20, 105, 100, 25, "#FFF0CE");
        firstnameField = components.createJTextField(120, 105, 200, 23, "#FFF0CE", "#0C356A");
        
        //Middle Name Label and Field
        middlenameLabel = components.createJLabel("Middle Name:", 20, 135, 100, 25, "#FFF0CE");
        middlenameField = components.createJTextField(120, 135, 200, 23, "#FFF0CE", "#0C356A");
        
        //Last Name Label and Field
        lastnameLabel = components.createJLabel("Last Name:", 20, 165, 100, 25, "#FFF0CE");
        lastnameField = components.createJTextField(120, 165, 200, 23, "#FFF0CE", "#0C356A");
        
        //Birth Date Label and Field
        birthdateLabel = components.createJLabel("Birth Date:", 20, 195, 100, 25, "#FFF0CE");
        birthdateField = components.createJTextField(120, 195, 200, 23, "#FFF0CE", "#0C356A");
        
        //Gender Label and Field
        genderLabel = components.createJLabel("Gender:", 20, 225, 100, 25, "#FFF0CE");
        String[] genderType = {"Female", "Male"};
        genderBox = new JComboBox(genderType);
        genderBox.setBounds(120, 225, 200, 23);
        
        //Address Label and Field
        addressLabel = components.createJLabel("Address:", 20, 255, 100, 25, "#FFF0CE");
        addressField = components.createJTextField(120, 255, 200, 23, "#FFF0CE", "#0C356A");
        
        //Fathers Name Label and Field
        fathersnameLabel = components.createJLabel("Fathers Name:", 20, 285, 100, 25, "#FFF0CE");
        fathersnameField = components.createJTextField(120, 285, 200, 23, "#FFF0CE", "#0C356A");
        
        //Mothers Name Label and Field
        mothersnameLabel = components.createJLabel("Mothers Name:", 20, 315, 100, 25, "#FFF0CE");
        mothersnameField = components.createJTextField(120, 315, 200, 23, "#FFF0CE", "#0C356A");
        
        //Contact Number Label and Field
        contactNoLabel = components.createJLabel("Contact Number:", 20, 345, 100, 25, "#FFF0CE");
        contactNoField = components.createJTextField(120, 345, 200, 23, "#FFF0CE", "#0C356A");
        
        //Contact Number Label and Field
        depositLabel = components.createJLabel("Initial Deposit:", 20, 375, 100, 25, "#FFF0CE");
        depositField = components.createJTextField(120, 375, 200, 23, "#0C356A", "#FFC436");
        depositField.setEditable(false);
        
        //Account Number Label and Field
        accountNumberLabel = components.createJLabel("Account Number:", 20, 405, 100, 25, "#FFF0CE");
        accountNumberField = components.createJTextField(120, 405, 200, 23, "#0C356A", "#FFC436");
        accountNumberField.setEditable(false);
        
        //Exit Button
        exitButton = components.createJButton("Exit", 20, 445, 100, 25, "#FFC436", "#0174BE");
        exitButton.addActionListener(this);
        
        //Register Button
        registerButton = components.createJButton("Register", 210, 445, 110, 25, "#FFC436", "#0174BE");
        registerButton.addActionListener(this);
        
        //Adds components to Body Panel
        bodyPanel.add(registerButton);
        bodyPanel.add(exitButton);
        bodyPanel.add(accountNumberField);
        bodyPanel.add(accountNumberLabel);
        bodyPanel.add(depositField);
        bodyPanel.add(depositLabel);
        bodyPanel.add(contactNoField);
        bodyPanel.add(contactNoLabel);
        bodyPanel.add(mothersnameField);
        bodyPanel.add(mothersnameLabel);
        bodyPanel.add(fathersnameField);
        bodyPanel.add(fathersnameLabel);
        bodyPanel.add(addressField);
        bodyPanel.add(addressLabel);
        bodyPanel.add(genderBox);
        bodyPanel.add(genderLabel);
        bodyPanel.add(birthdateField);
        bodyPanel.add(birthdateLabel);
        bodyPanel.add(lastnameField);
        bodyPanel.add(lastnameLabel);
        bodyPanel.add(middlenameField);
        bodyPanel.add(middlenameLabel);
        bodyPanel.add(firstnameField);
        bodyPanel.add(firstnameLabel);
        bodyPanel.add(pincodeField);
        bodyPanel.add(pincodeLabel);
        bodyPanel.add(passwordField);
        bodyPanel.add(passwordLabel);
        bodyPanel.add(usernameField);
        bodyPanel.add(usernameLabel);
        
        this.add(bodyPanel);
        this.add(headerPanel);
        this.setVisible(true);
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        //Handles Exit Button
        if (e.getSource() == exitButton) {
            Login login = new Login();
            this.dispose();
        }
        
        //Handles Register Button
        if (e.getSource() == registerButton) {
            String username = usernameField.getText();
            String password = passwordField.getText();
            String pincode = pincodeField.getText();
            String firstname = firstnameField.getText();
            String middlename = middlenameField.getText();
            String lastname = lastnameField.getText();
            String birthdate = birthdateField.getText();
            String gender = (String) genderBox.getSelectedItem();
            String address = addressField.getText();
            String fathersname = fathersnameField.getText();
            String mothersname = mothersnameField.getText();
            String contactNo = contactNoField.getText();
            
            long highestCounter = 2023099999;

            //Checks if there are Empty Text Fields
            if (username.trim().isEmpty() || password.trim().isEmpty() || pincode.trim().isEmpty() || firstname.trim().isEmpty() || middlename.trim().isEmpty() ||
                    lastname.trim().isEmpty() || birthdate.trim().isEmpty() || gender.trim().isEmpty() || address.trim().isEmpty() || fathersname.trim().isEmpty() ||
                    mothersname.trim().isEmpty() || contactNo.trim().isEmpty()) {
                components.createWarningMessage("Please Fill Empty Fields", "Empty Fields!");
            }
            //Checks if Pincode consists of four digits
            else if (!pincode.matches("\\d{4}")) {
                components.createWarningMessage("Pincode should be four(4) digits", "Invalid Pincode!");
            }
            //Create a BankAccount object uisng Polymorphism
            else {
                // Reads the file name inside the list
                FinalProjectOOP.loadBankAccounts();
                
                // Loop through the file names in the list
                for (String fileName : FinalProjectOOP.accountsFileName) {
                    // Extract the account number from the filename
                    long counter = Long.parseLong(fileName.substring("NewAccount".length(), fileName.length() - 4));
                    
                    // Update the 'accountCounter' if a larger account number is found
                    if (counter > highestCounter) {
                        highestCounter = counter;
                    }
                }
                
                //Increments accountCounter
                FinalProjectOOP.accountCounter = highestCounter + 1;
                
                account = new BankAccount(username, password, pincode, firstname, middlename,
                                        lastname, birthdate, gender, address, fathersname, mothersname, contactNo);
                
                BankAccount newAccount = (BankAccount) account;

                //Writes account information into a .txt file
                String fileName = "NewAccount" + newAccount.getAccountNumber() + ".txt";

                try (PrintWriter writer = new PrintWriter(new File (fileName))) {
                    writer.println(account.toString());

                    depositField.setText(String.valueOf(account.balanceInquiry()));
                    accountNumberField.setText(String.valueOf(newAccount.getAccountNumber()));
                    
                    components.createInformationMessage("Account Number: " + newAccount.getAccountNumber() + "\n Initial Deposit: PHP" + account.balanceInquiry(), "Bank Account Created!");
                    
                    FinalProjectOOP.accounts.add(newAccount);
                    
                    AccountMenu accountMenu = new AccountMenu(newAccount.getUsername(), newAccount.getPassword(), newAccount);
                    this.dispose();
                    
                }
                catch (IOException ex) {
                    components.createErrorMessage("An Error Occured while creating Bank Account", "Bank Account creation failed!");
                }
            }
        }
    }
}
