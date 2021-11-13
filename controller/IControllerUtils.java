package OOP1.practice3.controller;

import OOP1.practice3.model.Bank;
import OOP1.practice3.model.BankManagement;
import OOP1.practice3.model.User;

import java.util.ArrayList;

public interface IControllerUtils {
    float getTotalAmount(String userID, String bankID, ArrayList<BankManagement> bmgs);

    ArrayList<BankManagement> updateList(BankManagement bmg, ArrayList<BankManagement> bmgs);

    float getAmount(String numAccount, ArrayList<BankManagement> bmgs);

    ArrayList<BankManagement> transferAmount(String toAccount, String fromAccount, float amount,
                                             ArrayList<BankManagement> bmgs);

    ArrayList<User> searchByName(String name, ArrayList<User> users);

    ArrayList<Bank> sortByDate(ArrayList<Bank> banks);

    ArrayList<BankManagement> deleteUser(ArrayList<BankManagement> bmgs, String userID);


}
