package io.smallrye.config.inject;

import static io.smallrye.config.inject.TestConfigProviderClassLoaderFactory.checkClassLoaderHook;

import jakarta.enterprise.context.ApplicationScoped;

import org.jboss.weld.junit5.WeldInitiator;
import org.jboss.weld.junit5.WeldJunit5Extension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(WeldJunit5Extension.class)
class ConfigInjectionConfigProviderClassLoaderFactoryTest extends ConfigInjectionTest {
    protected WeldInitiator createWeld() {
        return WeldInitiator
                .from(ConfigExtension.class, ConfigInjectionTest.ConfigBean.class, TestConfigProviderClassLoaderFactory.class)
                .addBeans()
                .activate(ApplicationScoped.class)
                .inject(this)
                .build();
    }

    @Test
    @Override
    void inject() {
        super.inject();
        checkClassLoaderHook(ConfigBean.class, "reasons");
        checkClassLoaderHook(ConfigBean.class, "reasonsSupplier");
        checkClassLoaderHook(ConfigBean.class, "reasonsOptional");
        checkClassLoaderHook(ConfigBean.class, "versions");
        checkClassLoaderHook(ConfigBean.class, "versionsDefault");
        checkClassLoaderHook(ConfigBean.class, "numbersList");
        checkClassLoaderHook(ConfigBean.class, "numbersSet");
        checkClassLoaderHook(ConfigBean.class, "numbersArray");
        checkClassLoaderHook(ConfigBean.class, "numbers");
        checkClassLoaderHook(ConfigBean.class, "myProp");
        checkClassLoaderHook(ConfigBean.class, "expansion");
        checkClassLoaderHook(ConfigBean.class, "secret");
        checkClassLoaderHook(ConfigBean.class, "myPropProfile");
        checkClassLoaderHook(ConfigBean.class, "config");
        checkClassLoaderHook(ConfigBean.class, "smallRyeConfig");
        checkClassLoaderHook(ConfigBean.class, "hyphenatedEnum");
        checkClassLoaderHook(ConfigBean.class, "missingExpression");
        checkClassLoaderHook(ConfigBean.class, "missingExpressionOptional");
        checkClassLoaderHook(ConfigBean.class, "missingExpressionOptionalInt");
    }

    @Test
    @Override
    void injectConfigValue() {
        super.injectConfigValue();
        checkClassLoaderHook(ConfigBean.class, "configValue");
        checkClassLoaderHook(ConfigBean.class, "configValueMissing");
        checkClassLoaderHook(ConfigBean.class, "configValueEmpty");
    }

    @Test
    @Override
    void optionals() {
        super.optionals();
        checkClassLoaderHook(ConfigBean.class, "unknown");
    }

    @Test
    @Override
    void converters() {
        super.converters();
        checkClassLoaderHook(ConfigBean.class, "convertedValueOptional");
    }
}
