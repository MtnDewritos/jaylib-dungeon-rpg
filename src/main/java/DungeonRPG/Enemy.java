package DungeonRPG;

import java.util.ArrayList;
import java.util.Random;

import static DungeonRPG.Actor.Direction.*;

public abstract class Enemy extends Fighter {

    protected int exp;

    public Enemy(int str, int dex, int vit, int intelligence, int lvl, int x, int y, int exp) {
        super(str, dex, vit, intelligence, lvl, x, y);
        this.exp = exp;
    }

    @Override
    public void die(){
        DungeonManager.removeActor(this.x, this.y);
    }

    @Override
    public void move(Direction d){
        int oldX = this.x;
        int oldY = this.y;
        if (d == RIGHT)
            this.x+=10;
        if (d == LEFT)
            this.x-=10;
        if (d == UP)
            this.y-=10;
        if (d == DOWN)
            this.y+=10;
        DungeonManager.updateActor(oldX, oldY, this.x, this.y);
    }

    public void act(){
        if(DungeonManager.getPlayer().getX() == this.x &&
                (DungeonManager.getPlayer().getY() == this.y + 10 || DungeonManager.getPlayer().getY() == this.y - 10)){
            attack(DungeonManager.getPlayer());
        }
        else if(DungeonManager.getPlayer().getY() == this.y &&
                (DungeonManager.getPlayer().getX() == this.x + 10 || DungeonManager.getPlayer().getX() == this.x - 10)){
            attack(DungeonManager.getPlayer());
        }
        else{
            ArrayList<Direction> dirs = getMovableDirections();
            int i = new Random().nextInt(dirs.size());
            move(dirs.get(i));
        }

    }

    protected ArrayList<Direction> getMovableDirections(){
        ArrayList<Direction> directions = new ArrayList<>();
        if(DungeonManager.tileIsFree(this.x, this.y+10)){
            directions.add(DOWN);
        }
        if(DungeonManager.tileIsFree(this.x, this.y-10)){
            directions.add(UP);
        }
        if(DungeonManager.tileIsFree(this.x-10, this.y)){
            directions.add(LEFT);
        }
        if(DungeonManager.tileIsFree(this.x+10, this.y)){
            directions.add(RIGHT);
        }
        return directions;
    }

}
