package DungeonRPG;

import static com.raylib.Colors.GREEN;
import static com.raylib.Raylib.DrawRectangle;

public class Warrior extends Character {


    public Warrior(int str, int dex, int vit, int intelligence, int lvl, int x, int y) {
        super(str, dex, vit, intelligence, lvl, x, y);
    }

    @Override
    public void takeDamage(int dmg) {
        super.takeDamage(dmg);
    }

    @Override
    protected void attack(Fighter f) {
        float dmg = (this.dmg + this.str/2.0f) * (100.0f + this.lvl)/100.0f;

        //chance to hit
        if(doesAttackHit(f.getAC(), f.getLvl())){
            f.takeDamage((int)dmg);
        }

    }

    @Override
    public void render() {
        DrawRectangle(this.x, this.y, 10, 10, GREEN);
    }
}
