package io.smallrye.config.inject;

import jakarta.enterprise.context.ApplicationScoped;

import org.jboss.weld.junit5.WeldInitiator;
import org.jboss.weld.junit5.WeldJunit5Extension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(WeldJunit5Extension.class)
class SupplierInjectionConfigProviderClassLoaderFactoryTest extends SupplierInjectionTest {

    protected WeldInitiator createWeld() {
        return WeldInitiator.from(ConfigExtension.class, SupplierBean.class, TestConfigProviderClassLoaderFactory.class)
                .addBeans()
                .activate(ApplicationScoped.class)
                .inject(this)
                .build();
    }

    @Test
    @Override
    void supplier() {
        super.supplier();
        TestConfigProviderClassLoaderFactory.checkClassLoaderHook(SupplierBean.class, "myProp");
        TestConfigProviderClassLoaderFactory.checkClassLoaderHook(SupplierBean.class, "supplierMyProp");
        TestConfigProviderClassLoaderFactory.checkClassLoaderHook(SupplierBean.class, "supplierInteger");
        TestConfigProviderClassLoaderFactory.checkClassLoaderHook(SupplierBean.class, "supplierOptionalInteger");
    }

    @Test
    @Override
    void dynamicSupplier() {
        super.dynamicSupplier();
        TestConfigProviderClassLoaderFactory.checkClassLoaderHook(SupplierBean.class, "supplierDynamic");
    }
}
