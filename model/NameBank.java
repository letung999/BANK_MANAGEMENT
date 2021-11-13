package OOP1.practice3.model;

public enum NameBank {
    BIDV("Ngan Hang Thuong Mai"),
    VIETCOMBANK("Ngan Hang Ngoai Thuong"),
    MB("Ngan Hang Quan Doi"),
    TPBANK("Ngan Hang Tien Phong"),
    VPBANK("Ngan Hang Viet Nam Thinh Vuong");

    private String value;

    NameBank(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
