package DungeonRPG;

import static com.raylib.Colors.RED;
import static com.raylib.Raylib.DrawRectangle;

public class Goblin extends Enemy{


    public Goblin(int str, int dex, int vit, int intelligence, int lvl, int x, int y, int exp) {
        super(str, dex, vit, intelligence, lvl, x, y, exp);
        this.hp = 5 + vit;
    }

    @Override
    protected void attack(Fighter f) {
        if(doesAttackHit(f.getAC(), f.getLvl())){
            f.takeDamage(this.dmg + this.str + this.lvl);
        }
    }

    @Override
    public void render() {
        DrawRectangle(this.x, this.y, 10, 10, RED);
    }
}
