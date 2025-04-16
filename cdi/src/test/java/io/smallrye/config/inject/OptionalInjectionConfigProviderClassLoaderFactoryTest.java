package io.smallrye.config.inject;

import static io.smallrye.config.inject.TestConfigProviderClassLoaderFactory.checkClassLoaderHook;

import org.jboss.weld.junit5.WeldInitiator;
import org.jboss.weld.junit5.WeldJunit5Extension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(WeldJunit5Extension.class)
class OptionalInjectionConfigProviderClassLoaderFactoryTest extends OptionalInjectionTest {

    @Override
    protected WeldInitiator createWeld() {
        return WeldInitiator.from(ConfigExtension.class,
                OptionalInjectionConfigProviderClassLoaderFactoryTest.class,
                TestConfigProviderClassLoaderFactory.class)
                .addBeans()
                .inject(this)
                .build();
    }

    @Test
    @Override
    void optionalIntInjection() {
        super.optionalIntInjection();
        checkClassLoaderHook(OptionalInjectionTest.class, "optionalInt");
        checkClassLoaderHook(OptionalInjectionTest.class, "optionalLong");
        checkClassLoaderHook(OptionalInjectionTest.class, "optionalDouble");
    }

}
