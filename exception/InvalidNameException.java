package OOP1.practice3.exception;

public class InvalidNameException extends Exception{
    String fullName;

    public InvalidNameException(String message, String fullName) {
        super(message);
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

}
