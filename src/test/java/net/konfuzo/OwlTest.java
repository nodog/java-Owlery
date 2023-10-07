package net.konfuzo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

class OwlTest {
    Owl owl;

    @BeforeEach
    void setUp() {
        owl = new Owl("SETUP_1");
    }

    @Test
    void shouldNameANewOwl() {
        Assertions.assertFalse(owl.toString().isEmpty());
    }

    @Test
    void shouldSetHomeOwlery() {
        Queue<Owl> owlQueue = new LinkedList<Owl>();
        Owlery owlery = new Owlery(owlQueue);
        owl.setHomeOwlery(owlery);
        Assertions.assertEquals(0, owlery.getSize());
        Assertions.assertEquals(owlery, owl.getHomeOwlery());
    }
}