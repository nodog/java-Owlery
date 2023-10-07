package net.konfuzo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Owlery {
    private final Queue<Owl> owlQueue;

    public Owlery(Queue<Owl> owlQueue) {
        this.owlQueue = owlQueue;
    }

    public static Queue<Owl> createPublicOwls(Integer size) {
        Queue<Owl> anOwlQueue = new LinkedList<Owl>();
        for (int i = 0; i < size; i++) {
            Owl o = new Owl(String.valueOf(i));
            System.out.println("creating owl: " + o);
            anOwlQueue.add(o);
        }
        return anOwlQueue;
    }

    public Integer attachAndDispatch(ArrayList<String> messages) {
        Integer i = 0;
        ExecutorService executorService = Executors.newCachedThreadPool();
        for(String message: messages) {
            Owl owl = this.owlQueue.remove();
            Boolean returnRequest = false;
            owl.setMessage(message);
            owl.setReturnRequest(returnRequest);
            owl.setHomeOwlery(this);
            System.out.println("gave message   " + message + "   to owl " + owl
                    + "   " + owlQueue.size() + " owls are left");
            //Thread thread = new Thread(owl);
            //owl.start();
            executorService.execute(owl);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            i++;
        }
        executorService.shutdown();
        try {
            boolean finished = executorService.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return i;
    }

    public Integer getSize() {
        return owlQueue.size();
    }

    @Override
    public String toString() {
        return "Owlery";
    }

    public void returnOwltoOwlery(Owl owl) {
        this.owlQueue.add(owl);
        System.out.println("there are " + owlQueue.size() + " owls available now");
    }
}
