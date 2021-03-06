package sda.exercises.sdaexercises.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sda.exercises.sdaexercises.proxyexample.StringDecorator;

import java.lang.reflect.Proxy;

@Configuration
public class ProxyConfiguration {

    /**
     * <code>decoratePattern</code> method adds a "Pattern" part at the end of given string
     * example:
     * decorateTest("example") -> "exampleTest"
     * decorateImplementation("example") -> "exampleImplementation"
     *
     * @return stringDecorator bean
     */
    @Bean
    StringDecorator getStringDecorator() {
        final Class<?>[] interfaces = new Class<?>[]{StringDecorator.class};
        final Object baseObject = new Object();

        final StringDecorator proxyInstance = (StringDecorator) Proxy.newProxyInstance(
                StringDecorator.class.getClassLoader(),
                interfaces,
                (proxy, method, args) -> {
                    final String methodName = method.getName();
                    final String keyWord = "decorate";
                    if (methodName.startsWith(keyWord)) {
                        String subString = methodName.substring(keyWord.length());
                        final String text = (String) args[0];
                        return text + subString;
                    }
                    return method.invoke(baseObject, args);
                }
        );

        return proxyInstance;
    }
}
