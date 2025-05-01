package io.smallrye.config.inject;

import jakarta.enterprise.inject.spi.Bean;
import jakarta.enterprise.inject.spi.InjectionPoint;

public interface ConfigProducerClassLoaderFactory {

    /**
     * Get the classloader for an {@code InjectionPoint}
     * @param ip the {@code InjectionPoint}
     * @return the classloader to use. The default behaviour is to use the TCCL at the time of injection.
     */
    ClassLoader getClassLoader(InjectionPoint ip);


    /**
     * Get the classloader for a {@code Bean}. This is called when processing the {@link io.smallrye.config.ConfigMapping}
     * and {@link org.eclipse.microprofile.config.inject.ConfigProperties} annotations.
     *
     * @param bean the bean for the mapping
     * @return the classloader to use. The default behaviour is to use the TCCL at the time of injection.
     */
    ClassLoader getClassLoader(Bean<?> bean);
}
