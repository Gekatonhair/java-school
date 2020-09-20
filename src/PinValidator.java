import exceptions.IncorrectFormatException;


public class PinValidator {
    private final String correctPinCode = "1234";

    public boolean validate(String pinCode) throws IncorrectFormatException {
        boolean isCorrect = false;
        try{
            if (pinCode.matches("^\\d{4}$")) {
                isCorrect = pinCode.equals(correctPinCode);
            } else {
                throw new IncorrectFormatException();
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        finally {
            return isCorrect;
        }
    }
}