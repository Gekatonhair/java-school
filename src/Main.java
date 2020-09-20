import exceptions.IncorrectFormatException;

public class Main {

    public static void main(String ...arg) throws IncorrectFormatException {
        TerminalServer server = new TerminalServer();
        PinValidator pinValidator = new PinValidator();

        Terminal terminal = new TerminalImpl(server, pinValidator);
        terminal.start();
    }
}