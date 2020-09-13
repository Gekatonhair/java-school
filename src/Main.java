import java.util.Arrays;

public class Main {
    enum Measure {
        FARENGATE,
        CELSIUM
    }

    public static void main(String ...arg) {
        System.out.println("Start");
        convert(10, Measure.CELSIUM, Measure.CELSIUM);

        int[] arr = {11, 3, 14, 16, 7};
        sort(arr);
    }

    private static void convert(double temp, Measure measureFrom, Measure measureTo) {
        String result;
        switch (measureFrom){
            case CELSIUM:
                switch (measureTo){
                    case CELSIUM:
                        result = String.valueOf(temp + " C");
                        break;
                    case FARENGATE:
                        double _result = temp * 1.8 + 32;
                        result = String.valueOf(_result + " F");
                        break;
                    default:
                        result = "Error of measureTo";
                }
                break;
            case FARENGATE:
                switch (measureTo){
                    case CELSIUM:
                        double _result = (temp - 32) / 1.8;
                        result = String.valueOf(_result + " F");
                        break;
                    case FARENGATE:
                        result = String.valueOf(temp + " F");
                        break;
                    default:
                        result = "Error of measureTo";
                }
                break;
            default:
                result = "Error of measureFrom";
        }
        System.out.println(result);
    }

    private static void sort (int[] arr) {
        boolean fl = false;
        int temp;
        while(!fl) {
            fl = true;
            for (int i = 0; i < arr.length-1; i++) {
                if(arr[i] > arr[i+1]){
                    fl = false;

                    temp = arr[i];
                    arr[i] = arr[i+1];
                    arr[i+1] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}