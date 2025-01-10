package DungeonRPG;

import static com.raylib.Colors.BLUE;
import static com.raylib.Raylib.DrawRectangle;

public class NPC extends Actor implements Interactable{


    public NPC(int x, int y) {
        super(x, y);
    }

    @Override
    public void move(Direction d) {

    }

    @Override
    public void render() {
        DrawRectangle(this.x, this.y, 10, 10, BLUE);
    }

    @Override
    public void interact() {

    }
}
