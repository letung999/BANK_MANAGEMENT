package OOP1.practice3.model;

public class BankManagement {
    private User user;
    private Bank bank;
    private float overBalance;
    private String state;

    public BankManagement(Bank bank) {
        this.bank = bank;
    }

    public BankManagement(User user, Bank bank, float overBalance, String state) {
        this.user = user;
        this.bank = bank;
        this.overBalance = overBalance;
        this.state = state;
    }

    public User getUser() {
        return user;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public float getOverBalance() {
        return overBalance;
    }

    public void setOverBalance(float overBalance) {
        this.overBalance = overBalance;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "BankManagement{" +
                "userID=" + user.getId() +
                ", bankID=" + bank.getNumOfAccount() +
                ", overBalance=" + overBalance +
                ", state='" + state + '\'' +
                '}';
    }
}
