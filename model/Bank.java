package OOP1.practice3.model;

import OOP1.practice3.exception.InvalidDateException;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Bank {
    private String numOfAccount;
    private String nameBank;
    private String typeOfAccount;
    private String startDate;
    private String endDate;

    public Bank() {

    }

    public Bank(String numOfAccount) {
        this.numOfAccount = numOfAccount;
    }

    public Bank(String numOfAccount, String nameBank,
                String typeOfAccount, String startDate, String endDate) {
        this.numOfAccount = numOfAccount;
        this.nameBank = nameBank;
        this.typeOfAccount = typeOfAccount;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getNumOfAccount() {
        return numOfAccount;
    }

    public void setNumOfAccount(String numOfAccount) {
        this.numOfAccount = numOfAccount;
    }

    public String getNameBank() {
        return nameBank;
    }

    public void setNameBank(String nameBank) {
        this.nameBank = nameBank;
    }

    public String getTypeOfAccount() {
        return typeOfAccount;
    }

    public void setTypeOfAccount(String typeOfAccount) {
        this.typeOfAccount = typeOfAccount;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) throws InvalidDateException {
        String regex = "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[13-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(startDate);
        if (matcher.find()) {
            this.startDate = startDate;
        } else {
            String msg = "Date format is false";
            throw new InvalidDateException(msg, startDate);
        }
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) throws InvalidDateException {
        String regex = "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[13-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(endDate);
        if (matcher.find()) {
            this.endDate = endDate;
        } else {
            String msg = "Date format is false";
            throw new InvalidDateException(msg, endDate);
        }
    }

    @Override
    public String toString() {
        return "Bank{" +
                "numOfAccount='" + numOfAccount + '\'' +
                ", nameBank='" + nameBank + '\'' +
                ", typeOfAccount='" + typeOfAccount + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }
}
