package com.example.springbreaker.quoters;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * Это штука, которая навешивает профилирование на методы тех классов, у которых указана аннотация @Profiling
 */
public class ProfilingHandlerBeanPostProcessor implements BeanPostProcessor {

    // запоминает названия оригинальных классов (до того, как обернули в прокси)
    private Map<String, Class> map = new HashMap<>();
    private ProfilingController controller = new ProfilingController();

    public ProfilingHandlerBeanPostProcessor() throws Exception{
        MBeanServer platformMBeanServer = ManagementFactory.getPlatformMBeanServer();
        platformMBeanServer.registerMBean(controller, new ObjectName("profiling", "name", "controller"));
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();
        if (beanClass.isAnnotationPresent(Profiling.class)) {
            map.put(beanName, beanClass);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class beanClass = map.get(beanName);
        if(beanClass!=null) {

            // создает объект из нового класса, который генерится на лету.
            return Proxy.newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(), new InvocationHandler() {

                //тут должна быть логика, которая будет в каждом методе класса, который сгенерируется на лету
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    if(controller.isEnabled()) {
                        System.out.println("Профилирую.....");
                        long before = System.nanoTime();
                        Object retVal = method.invoke(bean, args);
                        long after = System.nanoTime();
                        System.out.println(after-before);
                        System.out.println("Все, попрофилировал");
                        return retVal;
                    }
                    else {
                        return method.invoke(bean, args);
                    }
                }
            });
        }

        return bean;
    }

}
