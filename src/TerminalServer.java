import exceptions.NoCashException;

public class TerminalServer {
    private int cashBallance = 1999;
    private final String pin = "1234";

    public int getBalance() {
        return cashBallance;
    }

    public int pushCash(int val) {
        cashBallance += val;
        return cashBallance;
    }

    public int takeCash(int val) throws NoCashException {
        if(val > cashBallance){
            throw new NoCashException();
        } else if (val % 100 != 0) {
            throw new IllegalArgumentException("The amount must be a multiple of 100.");
        } else {
            cashBallance -= val;
            return cashBallance;
        }
    }

    public int makeCash() throws NoCashException {
        if(cashBallance > 0) {
            int random = (int) (Math.random() * cashBallance * 2 - cashBallance);
            cashBallance = random > 0 ? random : 0;
        } else {
            throw new NoCashException();
        }
        return cashBallance;
    }
}


