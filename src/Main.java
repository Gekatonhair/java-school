import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Main {
    //1
    private static Object Calculator = new CalculatorImp();

    //2
    public static void showAllMetods(Object object) {
        Class<?> myClass = object.getClass();

        for (Method method : myClass.getMethods()) {
            System.out.println(myClass.getSimpleName() + " : " + method.toString());
        }

        do {
            for (Method method : myClass.getDeclaredMethods()) {
                System.out.println(myClass.getSimpleName() + " : " + method.toString());
            }
            myClass = myClass.getSuperclass();
        } while (myClass != null);
    }

    //3
    public static boolean isGetter(Method method){
        if (!method.getName().startsWith("get")) {
            return false;
        }
        if (method.getParameterTypes().length != 0) {
            return false;
        }
        if (void.class.equals(method.getReturnType())) {
            return false;
        }
        return true;
    }

    public static void showAllGetters(Object object) {
        Class<?> myClass = object.getClass();
        Method[] methods = myClass.getMethods();
        for(Method method : methods) {
            if (isGetter(method)){
                System.out.println(method.getName());
            }
        }
    }

    //4
    public static void isNamesEqualValues(Object object) throws IllegalAccessException {
        Field[] fields =  object.getClass().getDeclaredFields();
        boolean result = true;

        for(Field field : fields) {
            if(field.getType().isAssignableFrom(String.class)){
                String fieldName = field.getName();
                try {
                    field.setAccessible(true);
                    String fieldValue = field.get(object).toString();
                    if (!fieldName.equals(fieldValue)){
                        result = false;
                        System.out.println(fieldName + " is not equal " + fieldValue);
                        break;
                    }
                } catch (Exception e){
                    System.out.println(fieldName + ": " + e.getMessage());
                    result = false;
                    break;
                }
            }
        }
        System.out.println("All names is equal values: " + result);;
    }


    public static void main(String ...arg) throws IllegalAccessException {
        showAllMetods(Calculator);//2
        showAllGetters(Calculator);//3
        isNamesEqualValues(Calculator);//4

        //5
        Calculator cacheProxyCalculator = (Calculator) Proxy.newProxyInstance(CalculatorImp.class.getClassLoader(),
                CalculatorImp.class.getInterfaces(), new CacheProxy(new CalculatorImp()));

        //6
        Calculator performanceProxyCalculator = (Calculator) Proxy.newProxyInstance(CalculatorImp.class.getClassLoader(),
                CalculatorImp.class.getInterfaces(), new PerformanceProxy(new CalculatorImp()));

        System.out.println(performanceProxyCalculator.calc(10));
        System.out.println(cacheProxyCalculator.calc(10));
        System.out.println(performanceProxyCalculator.calc(10));

        //7

    }

}