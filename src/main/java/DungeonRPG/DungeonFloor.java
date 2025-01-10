package DungeonRPG;

import java.awt.*;
import java.util.HashMap;

public class DungeonFloor {

    public HashMap<Point, DungeonTile> getTileMap() {
        return tileMap;
    }

    private final HashMap<Point, DungeonTile> tileMap = new HashMap<Point, DungeonTile>();

    public DungeonFloor(int sizeX, int sizeY){
        int centerX = 200;
        int centerY = 150;
        int evenOffsetX = 0;
        if (sizeX % 2 == 0){
            evenOffsetX = -5;
        }
        int evenOffsetY = 0;
        if (sizeY % 2 == 0){
            evenOffsetY = -5;
        }
        sizeX = sizeX*10;
        sizeY = sizeY*10;

        for(int x = 0; x <= sizeX; x+=10){
            for(int y = 0; y <= sizeY; y+=10){

                //int worldX = x + centerX - sizeX/2 - 5;
                //int worldY = y + centerY - sizeY/2 - 5;

                Point p = new Point(x,y);

                if(x == 0 || y == 0 || x == sizeX || y == sizeY){
                    tileMap.put(p, new DungeonTile(x,y, DungeonTile.TileType.WALL));
                    //System.out.println(x + " " + y);
                }
                else{
                    tileMap.put(p, new DungeonTile(x,y, DungeonTile.TileType.FLOOR));
                }
            }
        }
    }
}
