import exceptions.IncorrectFormatException;

public interface Terminal<T> {

    void start() throws IncorrectFormatException;
    void stop();
}
