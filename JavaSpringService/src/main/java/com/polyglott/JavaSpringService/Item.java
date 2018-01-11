package com.polyglott.JavaSpringService;

public class Item {
    private String name;
    private int dmg;

   Item (){

   }

    Item(String name, int dmg){
        this.name = name;
        this.dmg = dmg;
    }

    public int getDmg() {
        return dmg;
    }

    public void setDmg(int dmg) {
        this.dmg = dmg;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
