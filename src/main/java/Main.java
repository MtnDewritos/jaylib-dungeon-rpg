import DungeonRPG.*;
import DungeonRPG.Character;

import static DungeonRPG.Actor.Direction.UP;
import static DungeonRPG.Actor.Direction.DOWN;
import static DungeonRPG.Actor.Direction.LEFT;
import static DungeonRPG.Actor.Direction.RIGHT;
import static com.raylib.Raylib.*;
import static com.raylib.Colors.*;


public class Main {
    public static void main(String[] args) {
        String appHomeDir = System.getenv("APP_HOME");
        System.out.println(appHomeDir);


        int screenWidth = 1200;
        int screenHeight = 900;

        int virtualScreenWidth = 400;
        int virtualScreenHeight = 300;
        float virtualRatio = (float)screenWidth/(float)virtualScreenWidth;

        InitWindow(screenWidth, screenHeight, "test");

        Camera2D worldSpaceCamera = new Camera2D();  // Game world camera
        worldSpaceCamera.zoom(1.0f);

        Camera2D screenSpaceCamera = new Camera2D(); // Smoothing camera
        screenSpaceCamera.zoom(1.0f);

        RenderTexture target = new RenderTexture();
        target = LoadRenderTexture(virtualScreenWidth, virtualScreenHeight); // This is where we'll draw all our objects.

        // The target's height is flipped (in the source Rectangle), due to OpenGL reasons
        Rectangle sourceRec = new Rectangle().x(0.0f).y(0.0f).width((float)target.texture().width()).height(-(float)target.texture().height());

        Rectangle destRec = new Rectangle().x(-virtualRatio).y(-virtualRatio).width(screenWidth + (virtualRatio*2)).height(screenHeight + (virtualRatio*2));

        Vector2 origin = new Vector2().x(0).y(0);

        float cameraX = 0.0f;
        float cameraY = 0.0f;


        //InitWindow(800, 600, "Demo");
        SetTargetFPS(60);

        DungeonManager.populateFloor();




        while (!WindowShouldClose()) {
            //Update

            DungeonManager.update();

            // Set the camera's target to the values computed above
            Vector2 camTargetV = new Vector2().x(DungeonManager.getPlayer().getX()- virtualScreenWidth/2.0f).y(DungeonManager.getPlayer().getY()- virtualScreenHeight/2.0f);
            screenSpaceCamera.target(camTargetV);

            // Round worldSpace coordinates, keep decimals into screenSpace coordinates
            worldSpaceCamera.target().x((float)Math.floor(screenSpaceCamera.target().x()));

            screenSpaceCamera.target().x(screenSpaceCamera.target().x() - worldSpaceCamera.target().x());
            screenSpaceCamera.target().x(screenSpaceCamera.target().x() * virtualRatio);

            worldSpaceCamera.target().y((float)Math.floor(screenSpaceCamera.target().y()));
            screenSpaceCamera.target().y(screenSpaceCamera.target().y() - worldSpaceCamera.target().y());
            screenSpaceCamera.target().y(screenSpaceCamera.target().y() * virtualRatio);

            BeginTextureMode(target);
                ClearBackground(RAYWHITE);
                BeginMode2D(worldSpaceCamera);

                DungeonManager.render();



                EndMode2D();
            EndTextureMode();



            //UpdateCamera(camera, CAMERA_ORBITAL);
            BeginDrawing();
                ClearBackground(RED);

                    BeginMode2D(screenSpaceCamera);
                        DrawTexturePro(target.texture(), sourceRec, destRec, origin, 0.0f, WHITE);
                    EndMode2D();

                DrawText("Screen resolution: " + screenWidth + "x" + screenHeight, 10, 10, 20, DARKBLUE);
                DrawText("World resolution: " + virtualScreenWidth + "x" + virtualScreenHeight, 10, 40, 20, DARKGREEN);

                EndDrawing();
        }
        CloseWindow();
    }
}