package com.johnsonstuff.BasicContainer;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BasicContainerApplicationTests {

    @Test
    void testHelloMethodDirectly() {
        // We are just treating this as a regular Java Class. 
        // No Spring Boot magic involved.
        BasicContainerApplication app = new BasicContainerApplication();
        
        String result = app.hello("Phil");
        
        assertEquals("Hello Phil!", result);
    }
}