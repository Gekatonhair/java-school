import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.InvocationTargetException;

public class BeanUtils {

    private static List<Method> getGetters(Object object) {
        List<Method> list = new ArrayList<>();
        Class<?> myClass = object.getClass();
        Method[] methods = myClass.getMethods();
        for(Method method : methods) {
            if (!method.getName().startsWith("get")) continue;
            if (method.getParameterCount() != 0) continue;
            if (void.class.equals(method.getReturnType())) continue;
            list.add(method);
        }
        return list;
    }

    private static List<Method> getSetters(Object object) {
        List<Method> list = new ArrayList<>();
        Class<?> myClass = object.getClass();
        Method[] methods = myClass.getMethods();
        for(Method method : methods) {
            if (!method.getName().startsWith("set")) continue;
            if (method.getParameterCount() != 1) continue;
            list.add(method);
        }
        return list;
    }

    public static void assign(Object to, Object from) throws InvocationTargetException, IllegalAccessException {
        List<Method> settersTo = getSetters(to);
        List<Method> gettersFrom = getGetters(from);

        if (gettersFrom.size() == 0) {
            for (Method setter : settersTo) {
                Class<?> classSetters = setter.getParameterTypes()[0];
                for (Method getter : gettersFrom) {
                    Class<?> classGetters = getter.getReturnType();
                    if (classSetters.isAssignableFrom(classGetters)) {
                        setter.invoke(to, getter.invoke(from));
                        gettersFrom.remove(getter);
                        break;
                    }
                }
            }
        }
    }
}
