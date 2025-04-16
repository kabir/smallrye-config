package io.smallrye.config.inject;

import static io.smallrye.config.inject.TestConfigProviderClassLoaderFactory.checkClassLoaderHook;

import jakarta.enterprise.context.ApplicationScoped;

import org.jboss.weld.junit5.WeldInitiator;
import org.jboss.weld.junit5.WeldJunit5Extension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(WeldJunit5Extension.class)
class IndexedPropertiesInjectionConfigProducerClassLoaderFactoryTest extends IndexedPropertiesInjectionTest {

    @Override
    protected WeldInitiator createWeld() {
        return WeldInitiator.from(ConfigExtension.class, IndexedBean.class, TestConfigProviderClassLoaderFactory.class)
                .addBeans()
                .activate(ApplicationScoped.class)
                .inject(this)
                .build();
    }

    @Test
    @Override
    void indexed() {
        super.indexed();
        checkClassLoaderHook(IndexedBean.class, "host0");
        checkClassLoaderHook(IndexedBean.class, "host1");
        checkClassLoaderHook(IndexedBean.class, "host2");
        checkClassLoaderHook(IndexedBean.class, "hosts");
        checkClassLoaderHook(IndexedBean.class, "hostsSet");
        checkClassLoaderHook(IndexedBean.class, "converted");
        checkClassLoaderHook(IndexedBean.class, "defaults");
        checkClassLoaderHook(IndexedBean.class, "overrideDefaults");
        checkClassLoaderHook(IndexedBean.class, "overrideIndexed");
        checkClassLoaderHook(IndexedBean.class, "comma");
    }

    @Test
    @Override
    void optionals() {
        super.optionals();
        checkClassLoaderHook(IndexedBean.class, "optionalEmpty");
        checkClassLoaderHook(IndexedBean.class, "optionalDefaults");
        checkClassLoaderHook(IndexedBean.class, "optionalIndexed");
    }

    @Test
    @Override
    void suppliers() {
        super.suppliers();
        checkClassLoaderHook(IndexedBean.class, "supplierEmpty");
        checkClassLoaderHook(IndexedBean.class, "supplierDefaults");
        checkClassLoaderHook(IndexedBean.class, "supplierIndexed");
    }
}
