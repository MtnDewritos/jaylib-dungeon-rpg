package DungeonRPG;

import DungeonRPG.Character.EquipmentSlot;

import java.util.HashMap;

import static DungeonRPG.Fighter.*;

public class Equipment extends Item{

    //hashmap stat amount to add
    private final HashMap<Stat, Integer> statMap;
    private final EquipmentSlot slot;
    private final String name;

    public Equipment(String name, EquipmentSlot slot, HashMap<Stat, Integer> statMap){
        this.statMap = statMap;
        this.name = name;
        this.slot = slot;
    }

    public HashMap<Stat, Integer> getStatMap() {
        return statMap;
    }

    public EquipmentSlot getSlot() {
        return slot;
    }

    public String getName() {
        return name;
    }
}
