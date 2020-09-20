import exceptions.IncorrectFormatException;
import exceptions.AccountIsLockedException;
import exceptions.NoCashException;

import java.util.Scanner;
import java.util.Date;


public class TerminalImpl<T> implements Terminal<T> {
    Scanner input;
    private final TerminalServer server;
    private final PinValidator pinValidator;
    private int countFail = 3;
    private boolean isLock = false;
    private Date lockTime = new Date();

    private final String GET_ACTIONS_TEXT = "Choose action number:\n" +
            "1 - cash ballance\n" +
            "2 - push cash\n" +
            "3 - take cash\n" +
            "4 - make cash\n"+
            "5 - exit\n";
    private final String INPUT_PIN_TEXT ="Input you pin-code: ";
    private final String INPUT_CASH_TEXT ="Input value: ";
    private final String INCORRECT_PIN_TEXT = "Wrong pin, number of remaining attempts is ";

    public TerminalImpl(TerminalServer server, PinValidator pinValidator) {
        this.server = server;
        this.pinValidator = pinValidator;
    }

    @Override
    public void start() throws IncorrectFormatException {
        showMessage("Start terminal");
        this.input = new Scanner(System.in);
        //if(requestCorrectPin()){
            requestActions();
        //}
    }

    @Override
    public void stop() {
        showMessage("Stop terminal");
        input.close();
    }

    private void showMessage (String text) {
        System.out.println(text);
    }



    private void showIncorrectPinMessage () {
        showMessage(INCORRECT_PIN_TEXT + countFail + "\n");
    }

    private boolean requestCorrectPin() throws IncorrectFormatException {
        showMessage(INPUT_PIN_TEXT);
        while (input.hasNextLine()) {
            String pinCode = input.nextLine();
            if (isLock) {
                long passTimeSecond = 10 - (new Date().getTime() - lockTime.getTime())/1000;
                try{
                    System.out.println(passTimeSecond > 0);
                    if(passTimeSecond > 0) {
                        throw new AccountIsLockedException(passTimeSecond);
                    } else {
                        isLock = false;
                        countFail = 3;
                        showMessage(INPUT_PIN_TEXT);
                    }
                } catch (Exception e){
                    showMessage(e.getMessage());
                }
            } else {
                if (pinValidator.validate(pinCode)) {
                    break;
                } else {
                    countFail--;
                    showIncorrectPinMessage();
                    if (countFail == 0) {
                        lockTime = new Date();
                        isLock = true;
                    }
                    else {
                        showMessage(INPUT_PIN_TEXT);
                    }
                }
            }
        }
        //input.close();
        return true;
    }

    private void requestActions() {
        showMessage(GET_ACTIONS_TEXT);
        while (input.hasNextLine() ) {

            String action = input.nextLine();
            try{
                switch (action) {
                    case "1"://balance
                        showMessage("Ballance is " + String.valueOf(server.getBalance()));
                        break;
                    case "2"://push
                        showMessage(INPUT_CASH_TEXT);
                        Integer pushVal = Integer.valueOf(input.nextLine());
                        showMessage(String.valueOf(server.pushCash(pushVal)));
                        break;
                    case "3"://take
                        showMessage(INPUT_CASH_TEXT);
                        Integer takeVal = Integer.valueOf(input.nextLine());
                        showMessage(String.valueOf(server.takeCash(takeVal)));
                        break;
                    case "4"://sberbank
                        server.makeCash();
                        showMessage(String.valueOf(server.getBalance()));
                        break;
                    case "5"://exit
                        this.stop();
                        break;
                    default:
                        throw new IncorrectFormatException();
                }
            } catch (Exception | NoCashException e){
                showMessage(e.getMessage());
            }
            showMessage(GET_ACTIONS_TEXT);
        }
    }
}

