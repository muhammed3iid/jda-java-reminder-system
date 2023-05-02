package org.example;

import java.util.ArrayList;

public class Model {

    ArrayList<String> list;

    public Model(){
        list = new ArrayList<>();
        list.add("Veni, Vedi, Veci.");
        list.add("Garbage in, Garbage out.");
        list.add("Broke is temporary, poor is eternal.");
    }

    public ArrayList<String> getList(){
        return list;
    }



}
