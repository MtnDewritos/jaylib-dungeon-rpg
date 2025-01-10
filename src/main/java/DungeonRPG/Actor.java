package DungeonRPG;

public abstract class Actor {

    public enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT;
    }

    protected int x, y;

    public Actor(int x, int y){
        this.x = x;
        this.y = y;
    }

    public abstract void move(Direction d);

    public abstract void render();

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
