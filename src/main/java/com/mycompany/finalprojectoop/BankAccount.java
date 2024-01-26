package com.mycompany.finalprojectoop;

public class BankAccount extends BankTransactions {
    
    private String username;
    private String password;
    private String pinCode;
    
    private String firstName;
    private String middleName;
    private String lastName;
    private String birthDate;
    private String gender;
    private String address;
    
    private String fatherName;
    private String motherName;
    private String contactNumber;
    
    private double accountBalance;
    
    private long accountNumber;
    
    //Constructors for creating a new BankAccount
    public BankAccount(String username, String password, String pinCode, String firstName, String middleName,
                String lastName, String birthDate, String gender, String address, String fatherName,
                String motherName, String contactNumber) {
        
        this.username = username;
        this.password = password;
        this.pinCode = pinCode;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.address = address;
        this.fatherName = fatherName;
        this.motherName = motherName;
        this.contactNumber = contactNumber;
        this.accountBalance = 500; //Initial Deposit
        this.accountNumber = FinalProjectOOP.accountCounter;   
    }
    
    //Constructor for reading a .txt file and creating a BankAccount
    public BankAccount(String username, String password, String pinCode, String firstName, String middleName,
                String lastName, String birthDate, String gender, String address, String fatherName,
                String motherName, String contactNumber, double accountBalance, long accountNumber) {
        
        this.username = username;
        this.password = password;
        this.pinCode = pinCode;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.address = address;
        this.fatherName = fatherName;
        this.motherName = motherName;
        this.contactNumber = contactNumber;
        this.accountBalance = accountBalance;
        this.accountNumber = accountNumber;
    }
    
    //Override Methods from BankTransactions
    @Override
    public double deposit(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Invalid Deposit Amount");
        }
        
        return this.accountBalance += amount;
    }

    @Override
    public double withdraw(double amount) {
        if (amount > 0 && amount <= this.accountBalance) {
            this.accountBalance -= amount;
            return this.accountBalance;
        }
        
        throw new IllegalArgumentException("Invalid Withdrawal Amount or Insufficient Balance");
    }
    
    @Override
    public double balanceInquiry() {
        return this.accountBalance;
    }
    
    @Override
    public String toString() {
        return "Username: " + username + "\n" +
                "Password: " + password + "\n" + 
                "Pin Code: " + pinCode + "\n" + 
                "First Name: " + firstName + "\n" + 
                "Middle Name: " + middleName + "\n" + 
                "Last Name: " + lastName + "\n" + 
                "Birthdate: " + birthDate + "\n" + 
                "Gender: " + gender + "\n" + 
                "Address: " + address + "\n" + 
                "Fathers Name: " + fatherName + "\n" + 
                "Mothers Name: " + motherName + "\n" + 
                "Contact Number: " + contactNumber + "\n" +
                "Account Balance: " + accountBalance + "\n" +
                "Account Number: " + accountNumber;
    }
 
    //Getters for the attributes
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPinCode() {
        return pinCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getGender() {
        return gender;
    }

    public String getAddress() {
        return address;
    }

    public String getFatherName() {
        return fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public double getAccountBalance() {
        return accountBalance;
    }
    
    public long getAccountNumber() {
        return accountNumber;
    }
    
    //Setter
    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }
    
}
