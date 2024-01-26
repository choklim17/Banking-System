package com.mycompany.finalprojectoop;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class Profile extends JFrame {
    
    private BankAccount account;
    
    private JPanel bodyPanel;
    
    private JLabel firstNameLabel;
    private JLabel middleNameLabel;
    private JLabel lastNameLabel;
    private JLabel birthdateLabel;
    private JLabel genderLabel;
    private JLabel addressLabel;
    private JLabel fathersNameLabel;
    private JLabel mothersNameLabel;
    private JLabel contactNumberLabel;
    
    private JTextField firstNameField;
    private JTextField middleNameField;
    private JTextField lastNameField;
    private JTextField birthdateField;
    private JTextField genderField;
    private JTextField addressField;
    private JTextField fathersNameField;
    private JTextField mothersNameField;
    private JTextField contactNumberField;
    
    public Profile (BankAccount account) {
        this.account = account;
        
        GUIComponents components = new GUIComponents();
        
        //Frame
        this.setTitle("My Profile");
        this.setSize(400, 410);
        this.setResizable(false);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.getContentPane().setBackground(Color.decode("#0C356A"));
        
        //Body Panel Border
        TitledBorder titledBorder = new TitledBorder("PROFILE ");
        titledBorder.setTitleColor(Color.decode("#FFC436"));
        titledBorder.setBorder(new LineBorder(Color.decode("#FFC436"), 2));
        titledBorder.setTitleFont(new Font("Arial", Font.BOLD, 12));
        
        //Label Border
        LineBorder lineBorder = new LineBorder(Color.decode("#FFF0CE"), 2);
        
        //Label Font
        Font font = new Font("Arial", Font.BOLD, 15);
        
        //Body Panel
        bodyPanel = components.createJPanel(19, 25, 350, 320, "#0174BE");
        bodyPanel.setBorder(titledBorder);
        bodyPanel.setLayout(null);
        
        //First Name Label and TextField;
        firstNameLabel = components.createJLabel("First Name:", 19, 25, 100, 25, "#FFF0CE");
        firstNameField = components.createJTextField(120, 25, 200, 23, "#FFF0CE","#0C356A");
        firstNameField.setText(account.getFirstName());
        firstNameField.setEditable(false);
        
        //Middle Name Label and TextField
        middleNameLabel = components.createJLabel("Middle Name:", 19, 55, 100, 25, "#FFF0CE");
        middleNameField = components.createJTextField(120, 55, 200, 23, "#FFF0CE", "#0C356A");
        middleNameField.setText(account.getMiddleName());
        middleNameField.setEditable(false);
        
        //Last Name Label and TextField
        lastNameLabel = components.createJLabel("Last Name:", 19, 85, 100, 25, "#FFF0CE");
        lastNameField = components.createJTextField(120, 85, 200, 23, "#FFF0CE", "#0C356A");
        lastNameField.setText(account.getLastName());
        lastNameField.setEditable(false);
        
        //Birthdate Label and TextField
        birthdateLabel = components.createJLabel("Birthdate:", 19, 115, 100, 25, "#FFF0CE");
        birthdateField = components.createJTextField(120, 115, 200, 23, "#FFF0CE", "#0C356A");
        birthdateField.setText(account.getBirthDate());
        birthdateField.setEditable(false);
        
        //Gender Label and TextField
        genderLabel = components.createJLabel("Gender:", 19, 145, 100, 25, "#FFF0CE");
        genderField = components.createJTextField(120, 145, 200, 23, "#FFF0CE", "#0C356A");
        genderField.setText(account.getGender());
        genderField.setEditable(false);
        
        //Address Label and TextField
        addressLabel = components.createJLabel("Address:", 19, 175, 100, 25, "#FFF0CE");
        addressField = components.createJTextField(120, 175, 200, 23, "#FFF0CE", "#0C356A");
        addressField.setText(account.getAddress());
        addressField.setEditable(false);
        
        //Fathers Name Label and TextField
        fathersNameLabel = components.createJLabel("Fathers Name:", 19, 205, 100, 25, "#FFF0CE");
        fathersNameField = components.createJTextField(120, 205, 200, 23, "#FFF0CE", "#0C356A");
        fathersNameField.setText(account.getFatherName());
        fathersNameField.setEditable(false);
        
        //Mothers Name Label and TextField
        mothersNameLabel = components.createJLabel("Mothers Name:", 19, 235, 100, 25, "#FFF0CE");
        mothersNameField = components.createJTextField(120, 235, 200, 23, "#FFF0CE", "#0C356A");
        mothersNameField.setText(account.getMotherName());
        mothersNameField.setEditable(false);
        
        //Contact Number Label and TextField
        contactNumberLabel = components.createJLabel("Contact Number:", 19, 265, 100, 25, "#FFF0CE");
        contactNumberField = components.createJTextField(120, 265, 200, 23, "#FFF0CE", "#0C356A");
        contactNumberField.setText(account.getContactNumber());
        contactNumberField.setEditable(false);
        
        //Adds the Account Information label and textfield
        bodyPanel.add(contactNumberField);
        bodyPanel.add(contactNumberLabel);
        bodyPanel.add(mothersNameField);
        bodyPanel.add(mothersNameLabel);
        bodyPanel.add(fathersNameField);
        bodyPanel.add(fathersNameLabel);
        bodyPanel.add(addressField);
        bodyPanel.add(addressLabel);
        bodyPanel.add(genderField);
        bodyPanel.add(genderLabel);
        bodyPanel.add(birthdateField);
        bodyPanel.add(birthdateLabel);
        bodyPanel.add(lastNameField);
        bodyPanel.add(lastNameLabel);
        bodyPanel.add(middleNameField);
        bodyPanel.add(middleNameLabel);
        bodyPanel.add(firstNameField);
        bodyPanel.add(firstNameLabel);
        
        this.add(bodyPanel);
        this.setVisible(true);
    }

}
