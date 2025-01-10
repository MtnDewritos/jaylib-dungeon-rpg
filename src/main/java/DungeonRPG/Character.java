package DungeonRPG;

import java.awt.*;
import java.util.HashMap;

import static DungeonRPG.Actor.Direction.*;
import static DungeonRPG.Actor.Direction.DOWN;
import static DungeonRPG.Fighter.Stat.*;

public abstract class Character extends Fighter {

    protected int exp;
    private int levelUpThreshold = 100;

    private HashMap<EquipmentSlot, Equipment> equippedItems = new HashMap<>();
    private final Inventory inventory;

    public Character(int str, int dex, int vit, int intelligence, int lvl, int x, int y) {
        super(str, dex, vit, intelligence, lvl, x, y);
        this.hp = 10 + vit * 2;
        this.ac = 5;
        this.inventory = new Inventory();
    }

    public enum EquipmentSlot{
        HEAD,
        ARM_R,
        ARM_L,
        TORSO,
        FEET,
        HANDS
    }

    @Override
    public void move(Direction d) {
        int x = this.x;
        int y = this.y;
        if (d == RIGHT)
            x+=10;
        if (d == LEFT)
            x-=10;
        if (d == UP)
            y-=10;
        if (d == DOWN)
            y+=10;

        if (DungeonManager.tileIsFree(x,y)){
            this.x = x;
            this.y = y;
        }
        Point p = new Point(x,y);
        if(DungeonManager.getActorMap().containsKey(p)) {
            Actor a = DungeonManager.getActorMap().get(p);
            if (a instanceof Enemy) {
                attack((Enemy)a);
            }
            else if (a instanceof Interactable){
                ((Interactable) a).interact();
            }
        }
        //System.out.println(this.x + " " + this.y);
    }

    public void equip(Equipment e){
        if (equippedItems.containsKey(e.getSlot())){
            unEquip(e.getSlot());
        }
        for (Stat s : e.getStatMap().keySet()){
            switch(s){
                case STR -> this.str += e.getStatMap().get(STR);
                case AC -> this.ac += e.getStatMap().get(AC);
                case HP -> this.hp += e.getStatMap().get(HP);
                case DEX -> this.dex += e.getStatMap().get(DEX);
                case DMG -> this.dmg += e.getStatMap().get(DMG);
                case INT -> this.intelligence += e.getStatMap().get(INT);
                case VIT -> this.vit += e.getStatMap().get(VIT);
                case TOHIT -> this.toHit += e.getStatMap().get(TOHIT);
            }
        }
        equippedItems.put(e.getSlot(), e);
    }

    public void unEquip(EquipmentSlot slot){
        Equipment e = equippedItems.get(slot);
        for (Stat s : e.getStatMap().keySet()){
            switch(s){
                case STR -> this.str -= e.getStatMap().get(STR);
                case AC -> this.ac -= e.getStatMap().get(AC);
                case HP -> this.hp -= e.getStatMap().get(HP);
                case DEX -> this.dex -= e.getStatMap().get(DEX);
                case DMG -> this.dmg -= e.getStatMap().get(DMG);
                case INT -> this.intelligence -= e.getStatMap().get(INT);
                case VIT -> this.vit -= e.getStatMap().get(VIT);
                case TOHIT -> this.toHit -= e.getStatMap().get(TOHIT);
            }
        }
        equippedItems.remove(e.getSlot());

    }

    public void die(){
        DungeonManager.playerDeath();
    }
    private void gainExp(int exp){
        this.exp += exp;
        if(this.exp >= this.levelUpThreshold){
            this.lvl += 1;
            this.levelUpThreshold = this.exp + lvl * 100 ;
            this.exp = 0;

            this.str += 2;
            this.dex += 1;
            this.vit += 2;

        }

    }

    public Inventory getInventory(){
        return inventory;
    }

}
