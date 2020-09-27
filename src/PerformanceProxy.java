import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

class PerformanceProxy implements InvocationHandler {
    private final Object myObject;

    PerformanceProxy(Object object) {
        this.myObject = object;
    }

    @Override
    public Object invoke(Object object, Method method, Object[] objects) throws Throwable {
        if (method.isAnnotationPresent(Metric.class)) {
            long before = System.nanoTime();
            Object result = method.invoke(myObject, objects);
            System.out.println("Method work time " + (System.nanoTime() - before) + " ms");
            return result;
        }
        return method.invoke(myObject, objects);
    }
}