package io.smallrye.config.inject;

import static io.smallrye.config.inject.SecuritySupport.getContextClassLoader;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import jakarta.enterprise.inject.spi.InjectionPoint;

public class TestConfigProviderClassLoaderFactory implements ConfigProducerClassLoaderFactory {
    static Map<String, Set<String>> injectionPointsByClass = new HashMap<>();

    @Override
    public ClassLoader getClassLoader(InjectionPoint ip) {
        if (ip.getMember() != null) {
            String className = ip.getMember().getDeclaringClass().getSimpleName();
            String name = ip.getMember().getName();
            Set<String> set = injectionPointsByClass.computeIfAbsent(className, s -> new HashSet<>());
            set.add(name);
        }
        return getContextClassLoader();
    }

    public static void checkClassLoaderHook(Class<?> injectedClass, String injectedName) {
        Set<String> names = injectionPointsByClass.get(injectedClass.getSimpleName());
        assertTrue(names.contains(injectedName),
                String.format("'%s' not found in recorded ConfigBean injection points", injectedName));
    }

}