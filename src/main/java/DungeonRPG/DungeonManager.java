package DungeonRPG;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import static DungeonRPG.Actor.Direction.*;
import static DungeonRPG.Actor.Direction.DOWN;
import static DungeonRPG.Character.EquipmentSlot.ARM_R;
import static DungeonRPG.DungeonTile.TileType.WALL;
import static DungeonRPG.Fighter.Stat.DMG;
import static com.raylib.Raylib.*;
import static java.util.Map.entry;

public final class DungeonManager {

    static DungeonFloor[] DFArray = new DungeonFloor[]{new DungeonFloor(80, 25)};

    private static DungeonFloor currentFloor = DFArray[0];



    private static Character player;

    private static HashMap<Point,Actor> actorMap = new HashMap<>();

    public static void populateFloor(){
        //move player init when there are more floors
        player = new Warrior(5, 5, 5, 3, 1, 200, 100);
        Enemy e = new Goblin(3, 5, 3, 1, 1, 250, 100, 10);
        Enemy e2 = new Goblin(3, 5, 3, 1, 1, 150, 200, 10);
        ItemActor a = new ItemActor(100, 100, new Equipment("Sword", ARM_R, new HashMap<Fighter.Stat, Integer>(Map.ofEntries(entry(DMG,3)))));
        Chest c = new Chest(300, 100);
        actorMap.put(new Point(250,100), e);
        actorMap.put(new Point(150,200), e2);
        actorMap.put(new Point(100,100), a);
        actorMap.put(new Point(300,100), c);
    }

    public static DungeonFloor getCurrentFloor() {
        return currentFloor;
    }

    public static boolean tileIsFree(int x, int y){
        //System.out.println(x + " " + y);
        if(currentFloor.getTileMap().get(new Point(x,y)).getType() == WALL){
            return false;
        }
        else return !actorMap.containsKey(new Point(x, y));
    }

    public static HashMap<Point, Actor> getActorMap() {
        return actorMap;
    }

    public static void update(){
        // input
        if(IsKeyPressed(KEY_RIGHT) || IsKeyPressed(KEY_LEFT) || IsKeyPressed(KEY_UP) || IsKeyPressed(KEY_DOWN)){
            if (IsKeyPressed(KEY_RIGHT)) {
                player.move(RIGHT);
            }
            if (IsKeyPressed(KEY_LEFT)) {
                player.move(LEFT);
            }
            if (IsKeyPressed(KEY_UP)) {
                player.move(UP);
            }
            if (IsKeyPressed(KEY_DOWN)) {
                player.move(DOWN);
            }
            Actor[] actors = actorMap.values().toArray(new Actor[0]);
            for(Actor a : actors){
                if (a instanceof Enemy) {
                    ((Enemy)a).act();
                }
            }
        }


    }

    public static void render(){

        for(DungeonTile dt : DungeonManager.getCurrentFloor().getTileMap().values()){
            dt.drawTile();
        }
        player.render();
        for(Actor a : actorMap.values()){
            a.render();
        }

    }

    public static void removeActor(int x, int y){
        actorMap.remove(new Point(x,y));
    }

    public static Character getPlayer() {
        return player;
    }
    public static void updateActor(int x, int y, int newX, int newY){
        Actor a = actorMap.get(new Point(x,y));
        removeActor(x, y);
        actorMap.put(new Point(newX, newY), a);
    }

    public static void playerDeath(){
        actorMap.clear();
        populateFloor();

    }

}
