package com.ladgelson.envvariablesspring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyRestController {

    private final Environment environment;

    private final ClassWithEnvProperties classWithEnvProperties;

    @Value("${property.viaValue}")
    private Integer viaValue;

    public MyRestController(Environment environment, ClassWithEnvProperties classWithEnvProperties) {
        this.environment = environment;
        this.classWithEnvProperties = classWithEnvProperties;
    }

    @GetMapping("/viaEnvironment")
    public ResponseEntity<String> viaEnvironment() {
        Integer value = environment.getProperty("property.qtd", Integer.class);
        return ResponseEntity.ok(value.toString());
    }

    @GetMapping("/viaConfigurationProperties")
    public ResponseEntity<String> viaConfigurationProperties() {
        return ResponseEntity.ok(classWithEnvProperties.toString());
    }

    @GetMapping("/viaValue")
    public ResponseEntity<String> viaValue() {
        return ResponseEntity.ok(viaValue.toString());
    }

}
