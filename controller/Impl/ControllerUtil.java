package OOP1.practice3.controller.Impl;

import OOP1.practice3.controller.IControllerUtils;
import OOP1.practice3.model.Bank;
import OOP1.practice3.model.BankManagement;
import OOP1.practice3.model.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ControllerUtil implements IControllerUtils {

    @Override
    public float getTotalAmount(String userID, String bankID, ArrayList<BankManagement> bmgs) {
        for (int i = 0; i < bmgs.size(); ++i) {
            if (userID.equals(bmgs.get(i).getUser().getId())
                    && bankID.equals(bmgs.get(i).getBank().getNumOfAccount())) {
                return bmgs.get(i).getOverBalance();
            }
        }
        return 0;
    }

    @Override
    public ArrayList<BankManagement> updateList(BankManagement bmg, ArrayList<BankManagement> bmgs) {
        boolean isUpdate = false;
        for (int i = 0; i < bmgs.size(); ++i) {
            if (bmg.getBank().getNumOfAccount().equals(bmgs.get(i).getBank().getNumOfAccount())
                    && bmg.getUser().getId().equals((bmgs.get(i).getUser().getId()))) {
                bmgs.set(i, bmg);
                isUpdate = true;
            }
        }
        if (!isUpdate) {
            bmgs.add(bmg);
        }
        return bmgs;
    }

    @Override
    public float getAmount(String numAccount, ArrayList<BankManagement> bmgs) {
        for (int i = 0; i < bmgs.size(); ++i) {
            if (numAccount.equals(bmgs.get(i).getBank().getNumOfAccount())) {
                return bmgs.get(i).getOverBalance();
            }
        }
        return 0;
    }

    @Override
    public ArrayList<BankManagement> transferAmount(String fromAccount, String toAccount,
                                                    float amount, ArrayList<BankManagement> bmgs) {
        float totalFromOfAmount = getAmount(fromAccount, bmgs) - amount;
        float totalReceiveAmount = getAmount(toAccount, bmgs) + amount;
        updateAmount(fromAccount, totalFromOfAmount, bmgs);
        updateAmount(toAccount, totalReceiveAmount, bmgs);
        return bmgs;

    }

    @Override
    public ArrayList<User> searchByName(String name, ArrayList<User> users) {
        String regex = ".*" + name.toLowerCase() +".*";
        var result = new ArrayList<User>();
        for(int i = 0; i < users.size(); ++i){
            if(users.get(i).getFullName().toLowerCase().matches(regex)){
                result.add(users.get(i));
            }
        }
        return result;
    }

    @Override
    public ArrayList<Bank> sortByDate(ArrayList<Bank> banks) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        for(int i = 0; i < banks.size(); ++i){
            for(int j = banks.size() - 1; j>0; j--){
                Bank bj = banks.get(j);
                Bank bbj = banks.get(j-1);
                try {
                    Date time1 = format.parse(bj.getStartDate());
                    Date time2 = format.parse(bbj.getStartDate());
                    long resTime = time1.getTime() - time2.getTime();
                    if(resTime < 0){
                        banks.set(j-1, bj);
                        banks.set(j, bbj);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        return banks;
    }

    @Override
    public ArrayList<BankManagement> deleteUser(ArrayList<BankManagement> bmgs, String userID) {
        for(int i = 0; i < bmgs.size(); ++i){
            if(userID.equals(bmgs.get(i).getUser().getId())){
                bmgs.remove(i);
            }
        }
        return bmgs;
    }

    public static ArrayList<BankManagement> updateAmount(String fromAccount, float amount,
                                                         ArrayList<BankManagement> bmgs) {
        for (int i = 0; i < bmgs.size(); ++i) {
            if (fromAccount.equals(bmgs.get(i).getBank().getNumOfAccount())) {
                bmgs.get(i).setOverBalance(amount);
            }
        }
        return bmgs;
    }




}
