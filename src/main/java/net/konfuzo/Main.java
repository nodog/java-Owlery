package net.konfuzo;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Random;

public class Main {
    final private static Integer OWLERY_SIZE = 20;

    public static void main(String[] args) {
        Queue<Owl> owlQueue = Owlery.createPublicOwls(Main.OWLERY_SIZE);
        Owlery owlery = new Owlery(owlQueue);
        for (int iDay = 1; iDay < 6; iDay++) {
            sendOwlForDay(owlery, iDay);
        }

    }

    private static void sendOwlForDay(Owlery owlery, int day) {
        // Day 1
        // students write their messages
        Random random = new Random();
        ArrayList<String> messages = new ArrayList<String>();
        int nMessages = random.nextInt(OWLERY_SIZE - 1) + 1;
        for (int i = 0; i < nMessages; i++ ) {
            messages.add("MAIN_MSG_" + i);
        }
        int nDispatched = owlery.attachAndDispatch(messages);
        System.out.println(nDispatched + " messages sent by owl on day " + day);
        System.out.println("------------------------------------------------------------");
    }
}