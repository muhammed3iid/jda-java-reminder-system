package org.example.Models;

import java.util.ArrayList;

public class SingletonModel {

    private static SingletonModel instance;
    ArrayList<String> list;

    private SingletonModel() {
        list = new ArrayList<>();
    }

    public static synchronized SingletonModel getInstance() {
        if (instance == null) {
            instance = new SingletonModel();
        }
        return instance;
    }

    public ArrayList<String> getList(){
        return list;
    }

}
