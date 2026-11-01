package main;

import java.util.ArrayList;

public class Logger {

    ArrayList<String> logs = new ArrayList<>();

    public Logger(){}

    void log(String mes){
        System.out.println(mes);
        logs.add(mes);
    }

}
