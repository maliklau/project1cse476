package edu.msu.maliklau.project1;

import java.io.Serializable;

/**
 * Created by Savanna on 3/13/2017.
 */

public class Player implements Serializable {

    String name = null;
    boolean turn = false;
    boolean winner = false;

    Player(String name){
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
