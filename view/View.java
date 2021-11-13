package OOP1.practice3.view;

import OOP1.practice3.controller.Impl.ControllerUtil;
import OOP1.practice3.controller.Impl.DataController;
import OOP1.practice3.exception.InvalidDateException;
import OOP1.practice3.exception.InvalidNameException;
import OOP1.practice3.model.Bank;
import OOP1.practice3.model.BankManagement;
import OOP1.practice3.model.User;

import java.util.ArrayList;
import java.util.Scanner;

public class View {
    public static void main(String[] args) {
        var input = new Scanner(System.in);
        var dataController = new DataController();
        var util = new ControllerUtil();
        var userFileName = "USER.DAT";
        var bankFileName = "BANK.DAT";
        var bankManagementFileName = "BANK_MG.DAT";
        var bmgs = new ArrayList<BankManagement>();
        var users = new ArrayList<User>();
        var banks = new ArrayList<Bank>();
        var bmg = new ArrayList<BankManagement>();
        int option;
        do {
            System.out.println("*******************************MENU*******************************");
            System.out.println("1.Register Information User To File");
            System.out.println("2.Register Information Bank To File");
            System.out.println("3.Add Amount To The Bank");
            System.out.println("4.Transfer Amount");
            System.out.println("5.Show Information User");
            System.out.println("6.Show Information Bank");
            System.out.println("7.Show Information BankManagement");
            System.out.println("8.Search By Name User!");
            System.out.println("9.Sort By Date Register In BankManagement");
            System.out.println("10.Delete User in Management");
            System.out.println("0.Exit!");
            System.out.println("You choose!");
            option = input.nextInt();
            input.nextLine();
            switch (option) {
                case 0: {
                    System.out.println("exit!");
                    break;
                }
                case 1: {
                    User user = new User();
                    String id, fullName, address;
                    int age;
                    id = dataController.getIDUser();
                    do {
                        System.out.println("Enter Your Name");
                        fullName = input.nextLine();
                        try {
                            user.setFullName(fullName);
                            break;
                        } catch (InvalidNameException e) {
                            e.printStackTrace();
                        }
                    } while (true);
                    System.out.println("Enter Your Address");
                    address = input.nextLine();
                    System.out.println("Enter Your Age");
                    age = input.nextInt();
                    user = new User(id, fullName, address, age);
                    dataController.writeUserToFile(user, userFileName);
                    break;
                }
                case 2: {
                    String numOfAccount, nameBank,
                            typeOfAccount, startDate, endDate;
                    int optionBank, optionType;
                    Bank bank = new Bank();
                    numOfAccount = dataController.getIDUser();
                    do {
                        System.out.println("Enter Name Bank");
                        System.out.println("1.BIDV\n2.VIETCOMBANK\n3.MB\n4.TPBANK\n5.VPBANK");
                        optionBank = input.nextInt();
                        nameBank = dataController.getNameBank(optionBank);
                    } while (optionBank < 1 || optionBank > 5);

                    do {
                        System.out.println("Enter Type Bank");
                        System.out.println("1.SAVING_ACCOUNT\n2.FIXED_ACCOUNT" +
                                "\n3.PERSONAL_ACCOUNT\n4.SPEND_ACCOUNT");
                        optionType = input.nextInt();
                        typeOfAccount = dataController.getTypeBank(optionType);
                    } while (optionType < 1 || optionType > 4);
                    input.nextLine();
                    do {
                        System.out.println("Enter start Date");
                        startDate = input.nextLine();
                        try {
                            bank.setStartDate(startDate);
                            break;
                        } catch (InvalidDateException e) {
                            e.printStackTrace();
                        }
                    } while (true);

                    do {
                        System.out.println("Enter end Date");

                        endDate = input.nextLine();
                        try {
                            bank.setEndDate(endDate);
                            break;
                        } catch (InvalidDateException e) {
                            e.printStackTrace();
                        }
                    } while (true);

                    bank = new Bank(numOfAccount, nameBank, typeOfAccount, startDate, endDate);
                    dataController.writeBankToFile(bank, bankFileName);
                    break;

                }
                case 3: {
                    bmgs = dataController.readInformationBmg(bankManagementFileName);
                    users = dataController.readInformationUser(userFileName);
                    banks = dataController.readInformationBank(bankFileName);
                    String userID, bankID;
                    float overBalance;
                    String state;
                    do {
                        System.out.println("****************************USER****************************");
                        dataController.showInformationUser(users);
                        System.out.println("Enter UserID");
                        userID = input.nextLine();
                        if (dataController.checkExitUserID(userID, users)) {
                            break;
                        } else {
                            System.out.println("User is not register");
                        }
                    } while (true);

                    do {
                        System.out.println("****************************BANK****************************");
                        dataController.showInformationBank(banks);
                        System.out.println("Enter BankID");
                        bankID = input.nextLine();
                        if ((dataController.checkExitBankID(bankID, banks))
                                && !(dataController.checkExitAccount(bankID, bmgs))) {
                            break;
                        } else {
                            System.out.println("The Bank is not register or registered");
                        }
                    } while (true);
                    float totalAmount = util.getTotalAmount(userID, bankID, bmgs);
                    System.out.println("Enter Amount: ");
                    overBalance = input.nextFloat();
                    totalAmount += overBalance;
                    String[] states = {"ACTIVE", "INACTIVE", "Temporarily lock press yes"};
                    int x;
                    do {
                        System.out.println("1.ACTIVE\n2.INACTIVE\n3.Temporarily lock press yes");
                        System.out.println("Enter state");
                        x = input.nextInt();
                        state = states[x - 1];

                    } while (x < 1 || x > 3);
                    Bank currentBank = dataController.currentBank(bankID, banks);
                    User currentUser = dataController.currentUser(userID, users);
                    BankManagement bankManagement = new BankManagement(currentUser, currentBank, totalAmount, state);
                    bmgs = util.updateList(bankManagement, bmgs);
                    dataController.updateFile(bmgs, bankManagementFileName);
                    break;

                }
                case 4: {
                    bmgs = dataController.readInformationBmg(bankManagementFileName);
                    users = dataController.readInformationUser(userFileName);
                    banks = dataController.readInformationBank(bankFileName);
                    String toNumAccount, fromAccount;
                    float amountTransfer;
                    System.out.println("****************************BMG****************************");
                    dataController.showInformationBmg(bmgs);
                    do {
                        System.out.println("Enter from number Account");
                        fromAccount = input.nextLine();
                        System.out.println("Enter to number Account");
                        toNumAccount = input.nextLine();
                        if (dataController.checkExitAccount(toNumAccount, bmgs)
                                && dataController.checkExitAccount(fromAccount, bmgs)) {
                            break;
                        } else {
                            System.out.println("NUM OF ACCOUNT IS NOT SUITABLE");
                        }
                    } while (true);

                    do {
                        System.out.println("Enter your Amount to Transfer");
                        amountTransfer = input.nextFloat();
                        if (amountTransfer > util.getAmount(fromAccount, bmgs)) {
                            System.out.println("over amount to transfer !");
                        } else {
                            break;
                        }
                    } while (true);
                    bmgs = util.transferAmount(fromAccount, toNumAccount, amountTransfer, bmgs);
                    dataController.updateFile(bmgs, bankManagementFileName);
                    break;
                }
                case 5: {
                    System.out.println("****************************USER****************************");
                    users = dataController.readInformationUser(userFileName);
                    dataController.showInformationUser(users);
                    break;
                }
                case 6: {
                    System.out.println("****************************BANK****************************");
                    banks = dataController.readInformationBank(bankFileName);
                    dataController.showInformationBank(banks);
                    break;
                }
                case 7: {
                    System.out.println("****************************BMG****************************");
                    bmgs = dataController.readInformationBmg(bankManagementFileName);
                    dataController.showInformationBmg(bmgs);
                    break;
                }
                case 8:{
                    users = dataController.readInformationUser(userFileName);
                    var result = new ArrayList<User>();
                    String name;
                    System.out.println("Enter Name To Search");
                    name = input.nextLine();
                    result = util.searchByName(name, users);
                    if(result.size() == 0){
                        System.out.println("No Information in List");
                    }
                    else {
                        dataController.showInformationUser(result);
                    }
                    break;
                }
                case 9:{
                    banks = dataController.readInformationBank(bankFileName);
                    banks = util.sortByDate(banks);
                    System.out.println("****************************BANK****************************");
                    dataController.showInformationBank(banks);
                    break;
                }
                case 10:{
                    System.out.println("****************************BMG****************************");
                    bmgs = dataController.readInformationBmg(bankManagementFileName);
                    dataController.showInformationBmg(bmgs);
                    String userID;
                    System.out.println();
                    do {
                        System.out.println("Enter your userID to Delete or 'x' to cancel");
                        userID = input.nextLine();
                        if(dataController.checkExitUserIDInBmg(userID, bmgs)){
                            util.deleteUser(bmgs, userID);
                        }
                        else{
                            System.out.println("userID is false or not exit");
                        }
                    }while (!userID.equals("x"));
                    break;
                }

            }
        } while (option != 0);
    }
}
