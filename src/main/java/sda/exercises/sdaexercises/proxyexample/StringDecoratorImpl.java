package sda.exercises.sdaexercises.proxyexample;

import org.springframework.stereotype.Service;


/**
 * tymczasowy przykład już nie używany
 */
public class StringDecoratorImpl implements StringDecorator {
    @Override
    public String decorateTest(String text) {
        return text + "Test";
    }

    @Override
    public String decorateSuperHipper(String text) {
        return text + "Implementation";
    }

    @Override
    public String decorateJavaIsCool(String text) {
        return null;
    }
}
