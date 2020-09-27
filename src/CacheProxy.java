import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;

public class CacheProxy implements InvocationHandler {

    private final Object myObject;

    private final HashMap<Object, Object> cache = new HashMap<>();

    CacheProxy(Object object) {
        this.myObject = object;
    }

    @Override
    public Object invoke(Object object, Method method, Object[] objects) throws Throwable {
        Object fieldName = objects[0];
        if (cache.containsKey(fieldName))
            return cache.get(fieldName);
        else {
            Object value = method.invoke(myObject, objects);
            cache.put(fieldName, value);
            return value;
        }
    }
}
