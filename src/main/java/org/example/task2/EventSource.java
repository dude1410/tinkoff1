package org.example.task2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class EventSource implements Runnable {

    private List<Event> info = new ArrayList<>();

    private Random random;

    private final long TIMEOUT_MILLIS = 200;

    public EventSource() {
        random = new Random();
    }

    public List<Event> readData() {

        List<Event> answer = new ArrayList<>();
        synchronized (info) {
            Collections.copy(answer, info);
            info = new ArrayList<>();
        }
        return answer;
    }

    @Override
    public void run() {
        while (true) {
            int amount = random.nextInt(10);
            synchronized (info) {
                for (int i = 0; i < amount; i++) {
                    info.add(new Event(random.nextInt(100), "someInfo"));
                }
            }
            try {
                Thread.sleep(TIMEOUT_MILLIS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
