import java.awt.*;
import java.io.IOException;

public class DanmakuScene implements Scene{


    private Insets insets;
    private AnimatedSprite playerSprite;
    private PlayableCharacter playerCharacter;
    private EnemyCharacter enemyCharacter;

    public DanmakuScene() throws IOException {

    }

    public void init() throws IOException {

        insets = Game.window.getInsets();
        playerCharacter = new PlayableCharacter(5.0, "Reisen", 6, "ReisenProjectile.png");
        enemyCharacter = Game.enemyCharacter;


        Game.observable.add(playerCharacter, Observable.EventID.KEY_PRESSED, Observable.EventID.KEY_RELEASED);

    }

    @Override
    public void close() {

    }

    @Override
    public void render(Graphics graphics) {

        graphics.drawImage(Game.ressourceManager.getTexture("Background3.png"), 0 + insets.left, 0 + insets.top, null);

        Game.playerProjectileManager.drawProjectiles(graphics);
        Game.enemyProjectileManager.drawProjectiles(graphics);

        graphics.drawImage(Game.ressourceManager.getTexture("UIBackground3.png"), 700 + insets.left, 0 + insets.top, null);
        graphics.drawImage(Game.ressourceManager.getTexture("LoginShade.png"), 700 + insets.left, 0 + insets.top, null);

        playerCharacter.draw(graphics);
        enemyCharacter.draw(graphics);


    }

    @Override
    public void simulate() {
        Game.projectileScript.executeInstruction();
        Game.playerProjectileManager.moveProjectiles();
        Game.enemyProjectileManager.moveProjectiles();

        playerCharacter.update();
        enemyCharacter.update();
    }

    @Override
    public void setName(String name) {

    }

}
