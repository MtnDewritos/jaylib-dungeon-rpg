package DungeonRPG;

import java.util.HashMap;
import java.util.Map;

import static DungeonRPG.Character.EquipmentSlot.*;
import static com.raylib.Colors.YELLOW;
import static com.raylib.Raylib.DrawRectangle;
import static java.util.Map.entry;
import static DungeonRPG.Fighter.Stat.*;

public class ItemActor extends Actor implements Interactable {

    private Item item;

    public ItemActor(int x, int y, Item item) {
        super(x, y);
        this.item = item;
    }

    @Override
    public void move(Direction d) {

    }

    @Override
    public void render() {
        DrawRectangle(this.x, this.y, 10, 10, YELLOW);
    }

    @Override
    public void interact() {
        if(this.item instanceof Equipment){
            DungeonManager.getPlayer().equip((Equipment) this.item);
        }

        DungeonManager.removeActor(this.x,this.y);
    }
}
