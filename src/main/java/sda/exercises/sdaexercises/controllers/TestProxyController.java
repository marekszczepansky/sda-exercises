package sda.exercises.sdaexercises.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sda.exercises.sdaexercises.proxyexample.StringDecorator;

@RestController
@RequestMapping("proxy")
public class TestProxyController {

    @Autowired
    private StringDecorator stringDecorator;


    @GetMapping
    String getProxy(@RequestParam String text){
        return stringDecorator.decorateSuperHipper(stringDecorator.decorateTest(text)) + " " + stringDecorator.decorateJavaIsCool(text);
    }

}
