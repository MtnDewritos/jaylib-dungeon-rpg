package DungeonRPG;

import java.util.Random;

public abstract class Fighter extends Actor {

    protected int hp;
    protected int str;
    protected int dex;
    protected int vit;
    protected int intelligence;
    protected int dmg;
    protected int ac;
    protected int lvl;
    protected int toHit;

    public enum Stat {
        HP,
        STR,
        DEX,
        VIT,
        INT,
        DMG,
        AC,
        TOHIT;
    }


    public Fighter(int str, int dex, int vit, int intelligence, int lvl, int x, int y) {
        super(x,y);
        this.str = str;
        this.dex = dex;
        this.vit = vit;
        this.intelligence = intelligence;
        this.lvl = lvl;
    }


    public void takeDamage(int dmg){
        hp -= dmg;
        if(hp <= 0){
            die();
        }
    }

    public int getAC(){
        return this.ac + this.dex + 5 * lvl;
    }

    public int getHp() {
        return hp;
    }

    public int getStr() {
        return str;
    }

    public int getDex() {
        return dex;
    }

    public int getVit() {
        return vit;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getDmg() {
        return dmg;
    }

    public int getAc() {
        return ac;
    }

    public int getLvl() {
        return lvl;
    }

    public int getToHit() {
        return toHit;
    }

    public boolean doesAttackHit(int enemyAC, int enemyLvl) {
        int toHit = this.toHit + this.dex + 5 * this.lvl;
        float chanceToHit = 100f * (float)toHit *10f / (((float)toHit*10f + (float)enemyAC)) * 2f * (float)this.lvl / ((float)this.lvl + (float)enemyLvl);
        int rand = new Random().nextInt(100);
        return chanceToHit >= rand + 1;
    }

    abstract public void die();
    abstract protected void attack(Fighter f);

}
