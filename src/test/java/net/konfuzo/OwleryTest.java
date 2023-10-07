package net.konfuzo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Queue;

public class OwleryTest {
    Owlery owlery;
    Integer OWLERY_SIZE = 9;


    @BeforeEach
    void setUp() {
        Queue<Owl> owlQueue = Owlery.createPublicOwls(OWLERY_SIZE);
        owlery = new Owlery(owlQueue);
    }

    @Test
    void shouldCreatePublicOwls() {
        Queue<Owl> owlQueue = Owlery.createPublicOwls(OWLERY_SIZE);
        Assertions.assertEquals(OWLERY_SIZE, owlQueue.size());
    }

    @Test
    void toStringShouldPrintOwlery() {
        Assertions.assertEquals("Owlery", owlery.toString());

    }

    @Test
    void shouldAttachMessagesAndDispatchOwls() {
        ArrayList<String> messages = new ArrayList<String>();
        for (int i = 0; i < OWLERY_SIZE; i++) {
            String testMessage = "TEST_MSG_" + i;
            messages.add(testMessage);
        }
        Integer nDispatched = owlery.attachAndDispatch(messages);
        Assertions.assertEquals(OWLERY_SIZE, nDispatched);
    }

}
