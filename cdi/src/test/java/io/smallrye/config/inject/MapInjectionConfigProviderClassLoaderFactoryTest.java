package io.smallrye.config.inject;

import static io.smallrye.config.inject.TestConfigProviderClassLoaderFactory.checkClassLoaderHook;

import jakarta.enterprise.context.ApplicationScoped;

import org.jboss.weld.junit5.WeldInitiator;
import org.jboss.weld.junit5.WeldJunit5Extension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(WeldJunit5Extension.class)
public class MapInjectionConfigProviderClassLoaderFactoryTest extends MapInjectionTest {

    @Override
    public WeldInitiator createWeld() {
        return WeldInitiator.from(ConfigExtension.class, MapBean.class, TestConfigProviderClassLoaderFactory.class)
                .addBeans()
                .activate(ApplicationScoped.class)
                .inject(this)
                .build();
    }

    @Test
    @Override
    void map() {
        super.map();
        checkClassLoaderHook(MapBean.class, "map");
        checkClassLoaderHook(MapBean.class, "defaults");
        checkClassLoaderHook(MapBean.class, "converted");
    }

    @Test
    @Override
    void optionals() {
        checkClassLoaderHook(MapBean.class, "optionalEmpty");
        checkClassLoaderHook(MapBean.class, "optionalDefaults");
        checkClassLoaderHook(MapBean.class, "defaults");
    }
}
