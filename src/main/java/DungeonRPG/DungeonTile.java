package DungeonRPG;

import static com.raylib.Raylib.*;
import static com.raylib.Colors.*;

public class DungeonTile {

    private final int x;
    private final int y;

    private final TileType type;
    public enum TileType {
        FLOOR,
        WALL;
    }

    public DungeonTile(int x, int y, TileType type){
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public void drawTile(){
        switch (type){
            case WALL -> DrawRectangle(x, y, 10, 10, BLACK);
            case FLOOR -> DrawRectangle(x, y, 10, 10, GRAY);
        }
    }

    public TileType getType() {
        return type;
    }

}
