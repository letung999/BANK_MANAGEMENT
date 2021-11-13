package OOP1.practice3.controller;

import OOP1.practice3.model.Bank;
import OOP1.practice3.model.BankManagement;
import OOP1.practice3.model.User;

import java.util.ArrayList;

public interface IDataController {
    void openFileToWrite(String fileName);

    void writeBankToFile(Bank bank, String fileName);

    void closeFileAfterWrite(String fileName);

    void writeBMGToFile(BankManagement bmg, String fileName);

    void writeUserToFile(User user, String fileName);

    void openFileToRead(String fileName);

    void closeFileAfterRead(String fileName);

    ArrayList<User> readInformationUser(String fileName);

    ArrayList<Bank> readInformationBank(String fileName);

    ArrayList<BankManagement> readInformationBmg(String fileName);

    void showInformationUser(ArrayList<User> users);

    void showInformationBank(ArrayList<Bank> banks);

    void showInformationBmg(ArrayList<BankManagement> users);

    String getIDUser();

    String getNameBank(int option);

    String getTypeBank(int option);

    boolean checkExitUserID(String userID, ArrayList<User> users);

    boolean checkExitUserIDInBmg(String userID, ArrayList<BankManagement> bmgs);

    boolean checkExitBankID(String bankID, ArrayList<Bank> banks);

    Bank currentBank(String bankID, ArrayList<Bank> banks);

    User currentUser(String userID, ArrayList<User> users);

    void updateFile(ArrayList<BankManagement>bmgs, String fileName);

    boolean checkExitAccount(String IdBank, ArrayList<BankManagement> brms);


}
