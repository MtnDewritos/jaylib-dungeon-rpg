package DungeonRPG;

import java.util.HashMap;
import java.util.Map;

import static DungeonRPG.Character.EquipmentSlot.*;
import static com.raylib.Colors.YELLOW;
import static com.raylib.Raylib.DrawRectangle;
import static java.util.Map.entry;
import static DungeonRPG.Fighter.Stat.*;

public class ItemActor extends Actor implements Interactable {
    public ItemActor(int x, int y) {
        super(x, y);
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
        DungeonManager.getPlayer().equip(new Equipment("Sword", ARM_R, new HashMap<Fighter.Stat, Integer>(Map.ofEntries(entry(DMG,3)))));
        DungeonManager.removeActor(this.x,this.y);
    }
}
