package OOP1.practice3.model;

import OOP1.practice3.exception.InvalidNameException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {
    private String id;
    private String fullName;
    private String address;
    private int age;

    public User() {

    }

    public User(String id) {
        this.id = id;
    }

    public User(String id, String fullName, String address, int age) {
        this.id = id;
        this.fullName = fullName;
        this.address = address;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) throws InvalidNameException {
        this.fullName = fullName;
        String regex = "^[A-Z\\s]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(fullName);
        if (matcher.find()) {
            this.fullName = fullName;
        } else {
            String msg = "Name is not suitable !";
            throw new InvalidNameException(msg, fullName);
        }
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {

        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", fullName='" + fullName + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                '}';
    }
}
