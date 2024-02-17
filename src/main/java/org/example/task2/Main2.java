package org.example.task2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main2 {

    public static void main(String[] args) {

        long timeout = 200;
        List<String> recipients = Arrays.asList("Al", "Mike", "Bob", "Joe", "Bill", "Donald");

        EventSource eventSource = new EventSource();

        List<Event> arrayList = new ArrayList<>();

        System.out.println("start");
        while (true) {
            arrayList = eventSource.readData();

            for (String recipient : recipients) {
                Result result = Result.REJECTED;
                while (result == Result.REJECTED) {
                    result = sendData(recipient, arrayList);
                    if (result == Result.REJECTED) {
                        try {
                            Thread.sleep(timeout);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
            }
        }
    }

    public static Result sendData(String recipient, List<Event> events) {
        Random randomSender = new Random();
        double send = randomSender.nextDouble();
        if (send < 0.98) {
            System.out.println("message to " + recipient + " SUCCESS");
            return Result.ACCEPTED;
        }
        System.out.println("message to " + recipient + " FAILURE");
        return Result.REJECTED;
    }
}
