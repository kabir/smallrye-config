package io.smallrye.config.inject;

import jakarta.enterprise.inject.spi.InjectionPoint;

public interface ConfigProducerClassLoaderFactory {
    ClassLoader getClassLoader(InjectionPoint ip);
}
