package DungeonRPG;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import static DungeonRPG.Character.EquipmentSlot.ARM_R;
import static DungeonRPG.Fighter.Stat.*;
import static DungeonRPG.Fighter.Stat;
import static com.raylib.Colors.BROWN;
import static com.raylib.Raylib.DrawRectangle;
import static java.util.Map.entry;

public class Chest extends Actor implements Interactable {


    public Chest(int x, int y) {
        super(x, y);
    }

    @Override
    public void move(Direction d) {

    }

    @Override
    public void render() {
        DrawRectangle(this.x, this.y, 10, 10, BROWN);
    }

    @Override
    public void interact() {
        Item i = new Equipment("Sword", ARM_R, new HashMap<Stat, Integer>(Map.ofEntries(entry(DMG,3))));
        DungeonManager.getActorMap().put(new Point(this.x,this.y), new ItemActor(this.x,this.y, i));
    }
}
