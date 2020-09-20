package exceptions;

public class AccountIsLockedException extends Exception{
    private final long timePassed;

    public AccountIsLockedException(long timePassed) {
        this.timePassed = timePassed;
    }

    @Override
    public String getMessage() {
        return "wait " + this.timePassed + " sec";
    }
}
