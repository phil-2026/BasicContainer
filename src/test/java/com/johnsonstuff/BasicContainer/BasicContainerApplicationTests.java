package com.johnsonstuff.BasicContainer;

import org.junit.jupiter.api.Test;
import org.springframework.boot.info.BuildProperties;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BasicContainerApplicationTests {

    @Test
    void testHelloMethodDirectly() {
        // We are just treating this as a regular Java Class. 
        // No Spring Boot magic involved.
        //BasicContainerApplication app = new BasicContainerApplication();    
        //String result = app.hello("Phil");        
        //assertEquals("Hello Phil! Welcome to the Cross-Project-Demo(4)!", result);

        Properties properties = new Properties();
        properties.setProperty("version", "1.0.0");

        BuildProperties buildProperties = new BuildProperties(properties);

        BasicContainerApplication app =
            new BasicContainerApplication(buildProperties);

        String response = app.hello("Phil");

        assertEquals(
            "Hello Phil! Welcome to Cross-Project-Demo (v1.0.0)!",
            response
        );        

    }
}