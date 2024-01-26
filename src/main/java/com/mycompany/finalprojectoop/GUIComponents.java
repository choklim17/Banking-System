package com.mycompany.finalprojectoop;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class GUIComponents {
    
    // Create and return a JPanel with the specified properties.
    public JPanel createJPanel(int x, int y, int width, int height, String hexadecimal) {
        JPanel panel = new JPanel();
        panel.setBounds(x, y, width, height);
        panel.setBackground(Color.decode(hexadecimal));
        
        return panel;
    }
    
    // Create and return a JLabel with the specified properties.
    public JLabel createJLabel(String text, int x, int y, int width, int height, String foreground) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, width, height);
        label.setForeground(Color.decode(foreground));
        
        return label;
    }
    
    // Create and return a JButton with the specified properties.
    public JButton createJButton(String text, int x, int y, int width, int height, String background, String foreground) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        button.setBackground(Color.decode(background));
        button.setForeground(Color.decode(foreground));
        button.setFocusable(false);
        
        return button;
    }
    
    // Create and return a JTextField with the specified properties.
    public JTextField createJTextField(int x, int y, int width, int height, String background, String foreground) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, width, height);
        textField.setBackground(Color.decode(background));
        textField.setForeground(Color.decode(foreground));
        
        return textField;
    }
    
    // Create and return a JPasswordField with the specified properties.
    public JPasswordField createJPasswordField(int x, int y, int width, int height, String background, String foreground) {
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(x, y, width, height);
        passwordField.setBackground(Color.decode(background));
        passwordField.setForeground(Color.decode(foreground));
        
        return passwordField;
    }
    
    // Display an information message dialog box with the given message and title.
    public void createInformationMessage(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
    
    // Display an error message dialog box with the given message and title.
    public void createErrorMessage(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
    }
    
    // Display an warning message dialog box with the given message and title.
    public void createWarningMessage(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
    }
}
