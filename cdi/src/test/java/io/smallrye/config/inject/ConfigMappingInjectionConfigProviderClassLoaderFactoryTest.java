package io.smallrye.config.inject;

import static io.smallrye.config.inject.TestConfigProviderClassLoaderFactory.checkClassLoaderHook;

import org.jboss.weld.junit5.WeldInitiator;
import org.junit.jupiter.api.Test;

//TODO needs more work
public class ConfigMappingInjectionConfigProviderClassLoaderFactoryTest extends ConfigMappingInjectionTest {
    @Override
    protected WeldInitiator getWeld() {
        return WeldInitiator
                .from(
                        ConfigExtension.class,
                        ConfigMappingInjectionTest.class,
                        Server.class,
                        Client.class,
                        ConfigMappingBean.class,
                        TestConfigProviderClassLoaderFactory.class)
                .inject(this)
                .build();
    }

    @Test
    @Override
    void configMapping() {
        super.configMapping();
        checkClassLoaderHook(Server.class, "theHost");
    }

    @Test
    @Override
    void discoveredMapping() {
        super.discoveredMapping();
    }

    @Test
    @Override
    void overridePrefix() {
        super.overridePrefix();
    }

    @Test
    @Override
    void select() {
        super.select();
    }

    @Test
    @Override
    void overridePrefixBean() {
        super.overridePrefixBean();
    }
}
