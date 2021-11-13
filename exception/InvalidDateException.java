package OOP1.practice3.exception;

public class InvalidDateException extends Exception{
    String date;

    public InvalidDateException(String message, String date) {
        super(message);
        this.date = date;
    }

    public String getDate() {
        return date;
    }
}
