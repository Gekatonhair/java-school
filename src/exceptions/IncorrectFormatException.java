package exceptions;

public class IncorrectFormatException extends Exception {

    @Override
    public String getMessage() {
        return "Incorrect data format - use only only numeric char";
    }
}
