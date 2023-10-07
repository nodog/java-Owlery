package net.konfuzo;

import java.util.Random;
import java.util.UUID;

public class Owl implements Runnable {
    String name;
    String message;
    Boolean returnRequest;
    private Boolean isReturnMessage = false;
    private Owlery homeOwlery;

    Random random;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getReturnRequest() {
        return returnRequest;
    }

    public void setReturnRequest(Boolean returnRequest) {
        this.returnRequest = returnRequest;
    }


    public Owl(String name) {
        this.name = "OWL-" + name;
        this.message = "";
        this.returnRequest = false;
        this.random = new Random();
    }

    @Override
    public void run() {
        // fly from owlery to destination - take time
        int timeToDestination = random.nextInt(1000) + 200;
        passTime(timeToDestination);

        // deliver message
        System.out.println("owl " + this.name + " delivered message " + message);
        // decide recipientAsksForReturn
        Boolean recipientAsksForImmediateReturn = (random.nextFloat() < 0.3);
        // if ! returnRequest or ! recipientAsksForReturn {rest}
        if (!( returnRequest || recipientAsksForImmediateReturn )) {
            // rest
            int restTime = random.nextInt(2000) + 1000;
            passTime(restTime);

        } else {
            message = "RETURN_FOR_" + message ;
            this.isReturnMessage = true;
        }

        // fly from destination to owlery (add to owlCount)
        passTime(timeToDestination);

        // deliver any return
        if (isReturnMessage) {
            System.out.println("owl " + name + " delivered return message " + message);
        }

        // add to owlCount
        this.homeOwlery.returnOwltoOwlery(this);

    }

    private static void passTime(int timeToDestination) {
        try {
            Thread.sleep(timeToDestination);
        } catch (InterruptedException e) {
            System.out.println("passTime giving problems");
        }
    }

    @Override
    public String toString() {
        return this.name;
    }

    public void setHomeOwlery(Owlery owlery) {
        this.homeOwlery = owlery;
    }

    public Owlery getHomeOwlery() {
        return this.homeOwlery;
    }
}
