package OOP1.practice3.controller.Impl;

import OOP1.practice3.controller.IDataController;
import OOP1.practice3.model.*;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class DataController implements IDataController {
    private FileWriter fileWriter;
    private BufferedWriter bufferedWriter;
    private PrintWriter printWriter;
    private Scanner scanner;


    @Override
    public void openFileToWrite(String fileName) {
        try {
            fileWriter = new FileWriter(fileName, true);
            bufferedWriter = new BufferedWriter(fileWriter);
            printWriter = new PrintWriter(bufferedWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeBankToFile(Bank bank, String fileName) {
        /*
        * String numOfAccount, String nameAccount,
                String typeOfAccount, String startDate, String endDate*/
        openFileToWrite(fileName);
        printWriter.println(bank.getNumOfAccount() + "|" +
                bank.getNameBank() + "|" + bank.getTypeOfAccount()
                + "|" + bank.getStartDate() + "|" + bank.getEndDate());
        closeFileAfterWrite(fileName);
    }

    @Override
    public void closeFileAfterWrite(String fileName) {
        try {
            printWriter.close();
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeBMGToFile(BankManagement bmg, String fileName) {
        /*
         * Bank bank, float overBalance, String state*/
        openFileToWrite(fileName);
        printWriter.println(bmg.getUser().getId() + "|" + bmg.getBank().getNumOfAccount() + "|" +
                bmg.getOverBalance() + "|" + bmg.getState());
        closeFileAfterWrite(fileName);
    }

    @Override
    public void writeUserToFile(User user, String fileName) {
        /*
         * String id, String fullName, String address, int age*/
        openFileToWrite(fileName);
        printWriter.println(user.getId() + "|" + user.getFullName() +
                "|" + user.getAddress() + "|" + user.getAge());
        closeFileAfterWrite(fileName);
    }

    @Override
    public void openFileToRead(String fileName) {
        File file = new File(fileName);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            scanner = new Scanner(Paths.get(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void closeFileAfterRead(String fileName) {
        scanner.close();
    }

    @Override
    public ArrayList<User> readInformationUser(String fileName) {
        var listUser = new ArrayList<User>();
        openFileToRead(fileName);
        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            User user = convertDataUser(data);
            listUser.add(user);
        }
        closeFileAfterRead(fileName);
        return listUser;
    }

    private User convertDataUser(String data) {
        /*
         * String id, String fullName, String address, int age*/
        String[] datas = data.split("\\|");
        User user = new User(datas[0], datas[1], datas[2], Integer.parseInt(datas[3]));
        return user;
    }

    @Override
    public ArrayList<Bank> readInformationBank(String fileName) {
        var listBank = new ArrayList<Bank>();
        openFileToRead(fileName);
        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            Bank bank = convertDataBank(data);
            listBank.add(bank);
        }
        closeFileAfterRead(fileName);
        return listBank;
    }

    private Bank convertDataBank(String data) {
        String[] datas = data.split("\\|");
        Bank bank = new Bank(datas[0], datas[1], datas[2], datas[3], datas[4]);
        return bank;
    }

    @Override
    public ArrayList<BankManagement> readInformationBmg(String fileName) {
        var bmgs = new ArrayList<BankManagement>();
        openFileToRead(fileName);
        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            BankManagement bmg = convertDataBmg(data);
            bmgs.add(bmg);
        }
        closeFileAfterRead(fileName);
        return bmgs;
    }

    private BankManagement convertDataBmg(String data) {
        String[] datas = data.split("\\|");
        BankManagement bankManagement = new BankManagement(new User(datas[0]),
                new Bank(datas[1]), Float.parseFloat(datas[2]), datas[3]);
        return bankManagement;
    }

    @Override
    public void showInformationUser(ArrayList<User> users) {
        /*
         * String id, String fullName, String address, int age
         * */
        System.out.printf("%-15s%-20s%-20s%-20s\n", "ID", "FULL NAME", "ADDRESS", "AGE");
        for (var user : users) {
            System.out.printf("%-15s%-20s%-20s%-20s\n", user.getId(),
                    user.getFullName(), user.getAddress(), user.getAge());

        }
    }

    @Override
    public void showInformationBank(ArrayList<Bank> banks) {
        System.out.printf("%-20s%-25s%-20s%-15s%-15s\n", "NumOfAccount", "NameBank", "Type Of Account",
                "Start Date", "End Date");
        for (var bank : banks) {
            System.out.printf("%-20s%-25s%-20s%-15s%-15s\n", bank.getNumOfAccount(),
                    bank.getNameBank(),
                    bank.getTypeOfAccount(), bank.getStartDate(), bank.getEndDate());
        }
    }

    @Override
    public void showInformationBmg(ArrayList<BankManagement> bmgs) {
        System.out.printf("%-20s%-20s%-15s%-15s\n", "USER", "NUM OF ACCOUNT", "OVER BALANCE", "STATE");
        for (var bmg : bmgs) {
            System.out.printf("%-20s%-20s%-15s%-15s\n",
                    bmg.getUser().getId(),
                    bmg.getBank().getNumOfAccount(), bmg.getOverBalance(), bmg.getState());
        }

    }

    @Override
    public String getIDUser() {
        String id = "0123456789";
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < 9; ++i) {
            int index = generateID(0, id.length());
            str.append(id.charAt(index));
        }
        return str.toString();
    }

    @Override
    public String getNameBank(int option) {
        String result = null;
        switch (option) {
            case 1: {
                result = NameBank.BIDV.getValue();
                break;
            }
            case 2: {
                result = NameBank.VIETCOMBANK.getValue();
                break;
            }
            case 3: {
                result = NameBank.MB.getValue();
                break;
            }
            case 4: {
                result = NameBank.TPBANK.getValue();
                break;
            }
            case 5: {
                result = NameBank.VPBANK.getValue();
                break;
            }
        }
        return result;
    }

    @Override
    public String getTypeBank(int option) {
        String result = null;
        switch (option) {
            case 1: {
                result = TypeBank.SAVING_ACCOUNT.getValue();
                break;
            }
            case 2: {
                result = TypeBank.FIXED_ACCOUNT.getValue();
                break;
            }
            case 3: {
                result = TypeBank.PERSONAL_ACCOUNT.getValue();
                break;
            }
            case 4: {
                result = TypeBank.SPEND_ACCOUNT.getValue();
                break;
            }
        }
        return result;
    }

    @Override
    public boolean checkExitUserID(String userID, ArrayList<User> users) {
        for (int i = 0; i < users.size(); ++i) {
            if (userID.equals(users.get(i).getId())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkExitUserIDInBmg(String userID, ArrayList<BankManagement> bmgs) {
        for (int i = 0; i < bmgs.size(); ++i) {
            if (userID.equals(bmgs.get(i).getUser().getId())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkExitBankID(String bankID, ArrayList<Bank> banks) {
        for (int i = 0; i < banks.size(); ++i) {
            if (bankID.equals(banks.get(i).getNumOfAccount())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Bank currentBank(String bankID, ArrayList<Bank> banks) {
        for (int i = 0; i < banks.size(); ++i) {
            if (bankID.equals(banks.get(i).getNumOfAccount())) {
                return banks.get(i);
            }
        }
        return null;
    }

    @Override
    public User currentUser(String userID, ArrayList<User> users) {
        for (int i = 0; i < users.size(); ++i) {
            if (userID.equals(users.get(i).getId())) {
                return users.get(i);
            }
        }
        return null;
    }

    @Override
    public void updateFile(ArrayList<BankManagement> bmgs, String fileName) {
        File file = new File(fileName);
        if (file.exists()) {
            file.delete();
        }
        openFileToWrite(fileName);
        for (var bmg : bmgs) {
            printWriter.println(bmg.getUser().getId() + "|" + bmg.getBank().getNumOfAccount() + "|" +
                    bmg.getOverBalance() + "|" + bmg.getState());
        }
        closeFileAfterWrite(fileName);
    }

    @Override
    public boolean checkExitAccount(String IdBank, ArrayList<BankManagement> brms) {
        for (int i = 0; i < brms.size(); ++i) {
            if (IdBank.equals(brms.get(i).getBank().getNumOfAccount())) {
                return true;
            }
        }
        return false;
    }


    public int generateID(int min, int max) {
        Random rand = new Random();
        return rand.nextInt(max - min) + min;
    }
}
