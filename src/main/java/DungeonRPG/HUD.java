package DungeonRPG;

import static com.raylib.Colors.DARKGREEN;
import static com.raylib.Raylib.DrawText;

public final class HUD {

    public static void DrawHP(){


        DrawText("HP: " + DungeonManager.getPlayer().getHp(), 10, 40, 20, DARKGREEN);
    }

}
