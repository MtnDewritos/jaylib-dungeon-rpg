package DungeonRPG;


import java.awt.*;
import java.util.ArrayList;

public class Inventory {

    private ArrayList<Item> items;

    public Inventory(){
        items = new ArrayList<>();
    }

    public void addItem(Item i){
        items.add(i);
    }
    public void removeItem(Item i){
        items.remove(i);
    }
    public void dropItem(Item i, Point p){
        //check for free space on the ground and put item there
        removeItem(i);

    }

    public void drawInventory(){

    }

}
