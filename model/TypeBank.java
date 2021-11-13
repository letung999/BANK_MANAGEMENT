package OOP1.practice3.model;

public enum TypeBank {
    SAVING_ACCOUNT("TAI KHOAN TIET KIEM"),
    FIXED_ACCOUNT("TAI KHOAN CO KY HAN"),
    PERSONAL_ACCOUNT("TAI KHOAN CA NHAN"),
    SPEND_ACCOUNT("TAI KHOAN THANH TOAN");

    private String value;

    TypeBank(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
