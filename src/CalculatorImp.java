public class CalculatorImp implements Calculator{
    public static final String MONDAY = "MONDAY";
    private static final String TUESDAY = "TUESDAY";
    private static final int integerValue = 12;

    @Override
    public int calc(int number) {
        if(number < 0)
            return 0;
        if (number == 0)
            return 1;
        else
            return number * calc(number - 1);
    }

    public static void main(String[] args) {
        System.out.println(new CalculatorImp().calc(4));
    }
}
