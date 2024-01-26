package com.mycompany.finalprojectoop;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class Login extends JFrame implements ActionListener{
    
    private JPanel panel;
    
    private JLabel usernameLabel;
    private JLabel passwordLabel;

    private JTextField usernameField;
    private JPasswordField passwordField;
    
    private JButton loginButton;
    private JButton openAccountButton;
    
    private String username;
    private String password;
    
    private GUIComponents components;
    
    public Login() {
        //Creates an instance of GUIComponents for code reusesability
        components = new GUIComponents();
        
        //Frame
        this.setTitle("Mobile Banking");
        this.setSize(350, 240);
        this.setResizable(false);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.decode("#0C356A"));
        
        //Panel
        panel = components.createJPanel(19, 25, 300, 150, "#0174BE");
        panel.setLayout(null);
        
        //Border for Panel
        TitledBorder titledBorder = new TitledBorder("LOGIN ");
        titledBorder.setTitleColor(Color.decode("#FFC436"));
        titledBorder.setBorder(new LineBorder(Color.decode("#FFC436"), 2));
        titledBorder.setTitleFont(new Font("Arial", Font.BOLD, 14));
        
        //Username Label and Field
        usernameLabel = components.createJLabel("USERNAME:", 20, 30, 80, 25, "#FFF0CE");
        
        usernameField = components.createJTextField(100, 30, 170, 25, "#FFF0CE", "#0C356A");

        //Password Label and Field
        passwordLabel = components.createJLabel("PASSWORD:", 20, 60, 80, 25, "#FFF0CE");
        
        passwordField = components.createJPasswordField(100, 60, 170, 25, "#FFF0CE", "#0C356A");

        //Create Account Button
        openAccountButton = components.createJButton("Open Account", 20, 100, 115, 25, "#FFC436", "#0174BE");
        openAccountButton.addActionListener(this);
        
        //Login Button
        loginButton = components.createJButton("Login", 170, 100, 100, 25, "#FFC436", "#0174BE");
        loginButton.addActionListener(this);

        //Adds components to Panel
        panel.add(loginButton);
        panel.add(openAccountButton);
        panel.add(passwordField);
        panel.add(passwordLabel);
        panel.add(usernameField);
        panel.add(usernameLabel);
        panel.setBorder(titledBorder);
        
        this.add(panel);
        this.setVisible(true);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == openAccountButton) {
            // Open a new account registration frame
            NewAccount newAccount = new NewAccount();
            this.dispose();
        }
        
        if (e.getSource() == loginButton) {
            username = usernameField.getText();
            password = passwordField.getText();
            
            ArrayList<BankAccount> accounts = FinalProjectOOP.accounts;
            
            BankAccount foundAccount = null; // To store the found account
            boolean isSuccessful = false; // To track if the login is successful
            
            // Iterate through the list of accounts
            for (BankAccount account : accounts) {
                // Check if the entered username and password match an account
                if (account.getUsername().equals(username) && account.getPassword().equals(password)) {
                    foundAccount = account; // Store the matched account
                    isSuccessful = true; // Set login success to true
                    break;
                }
            }
            
            if (isSuccessful) {
                // Open the account menu frame with the found account details
                AccountMenu accountMenu = new AccountMenu(username, password, foundAccount);
                this.dispose();
            }
            else {
                components.createErrorMessage("Incorrect Username or Password", "Login Failed!");
            }
        }
    }
}
