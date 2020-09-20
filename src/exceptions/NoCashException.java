package exceptions;

public class NoCashException extends Throwable {
    @Override
    public String getMessage() {
        return "You ain't got that cash. Enter a smaller value!";
    }
}
