package main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.atomic.AtomicInteger;

@SpringBootApplication
public class Main {

    private static int i = 0;
    private static boolean start = false;

    public static void main(String[] args){
        System.out.println("Starting spring...");
        Thread thread = new Thread(() ->{
            while (!start){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                i++;
            }
        });
        thread.start();
        SpringApplication.run(Main.class, args);
        start = true;
        System.out.println("Done(" + ((double) i / 10) + "s)");
    }

}
