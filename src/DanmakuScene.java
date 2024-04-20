import java.awt.*;
import java.io.IOException;

public class DanmakuScene implements Scene{


    private Insets insets;
    private AnimatedSprite playerSprite;

    private Label scoreLabel;
    private Label score;
    private Label remainingLivesLabel;
    private Label remainingLives;
    private PlayableCharacter playerCharacter;
    private EnemyCharacter enemyCharacter;

    private int scoreCount;
    private int lifeCount;
    private int deathCount;




    public DanmakuScene() throws IOException {

    }

    public void init() throws IOException {

        insets = Game.window.getInsets();
        playerCharacter = new PlayableCharacter(5.0, "Reisen", 6, "ReisenProjectile.png");
        enemyCharacter = Game.enemyCharacter;
        scoreCount = 0;
        lifeCount = Integer.parseInt(Game.projectileScript.getScriptHeader().getAttribute("LifeCount"));
        //System.out.println(Game.projectileScript.getScriptHeader().getAttribute("LifeCount"));
        //lifeCount = 4;
        deathCount = 0;

        scoreLabel = new Label(Game.dialogManager.getDialog("score_indicator_label"), "sign_in", Color.WHITE);
        scoreLabel.setPosition(725, 300);

        score = new Label("", "sign_in", Color.WHITE);
        score.setPosition(725, 340);
        score.setText("0");

        remainingLivesLabel = new Label(Game.dialogManager.getDialog("remaining_lives_label"), "sign_in", Color.WHITE);
        remainingLivesLabel.setPosition(725, 400);
        remainingLives = new Label(Integer.toString(lifeCount), "sign_in", Color.WHITE);
        remainingLives.setPosition(725, 440);

        Game.observable.add(playerCharacter, Observable.EventID.KEY_PRESSED, Observable.EventID.KEY_RELEASED);

    }

    @Override
    public void close() {
        Game.projectileScript.clearScript();
        scoreCount = 0;
        playerCharacter.clearKeyInputs();
        Game.playerProjectileManager.clear();
        Game.enemyProjectileManager.clear();
        playerCharacter.resetPosition();
        enemyCharacter.resetPosition();
    }

    public double getPlayerEnemyDistance(){
        double distanceX = Math.abs(Math.abs(enemyCharacter.getPositionX()) - Math.abs(playerCharacter.getPositionX()));
        double distanceY = Math.abs(Math.abs(enemyCharacter.getPositionY()) - Math.abs(playerCharacter.getPositionY()));
        return Math.sqrt(Math.pow(distanceX, 2) + Math.pow(distanceY, 2));
    }
    public void scorePoints(){
        int hitCount = Game.playerProjectileManager.getCollisionCountWithRadius(enemyCharacter.getPositionX(), enemyCharacter.getPositionY(), enemyCharacter.getCollisionRadius());
        //if(hitCount == 0){
            //return;
        //}
        double distance = getPlayerEnemyDistance();
        int _score = 1000 - (int)distance;
        if(_score < 0){
            _score = 0;
        }
        scoreCount += hitCount * _score;
        score.setText(Integer.toString(scoreCount));
        //System.out.println(Integer.toString(hitCount * _score));
    }

    public void finishGame(){

        Game.getSceneManager().setScenesToRender(2);
        try{
            Game.getSceneManager().push("mapResultScene");
        }
        catch(Exception e){

        }
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

        scoreLabel.draw(graphics);
        score.draw(graphics);
        remainingLivesLabel.draw(graphics);
        remainingLives.draw(graphics);
    }

    @Override
    public void simulate() {
        Game.projectileScript.executeInstruction();
        Game.playerProjectileManager.moveProjectiles();
        Game.enemyProjectileManager.moveProjectiles();

        playerCharacter.update();
        enemyCharacter.update();

        if(deathCount != playerCharacter.getDeathCount()){

            deathCount = playerCharacter.getDeathCount();
            System.out.println(Integer.toString(lifeCount - deathCount) + " lives remaining");
            scoreCount /= 2;
        }

        scorePoints();
        remainingLives.setText(Integer.toString(lifeCount - deathCount));

        if(Game.projectileScript.isFinished()){
            System.out.println("Script has finished running");
            Variables.stageCleared = true;
            Variables.score = scoreCount;
            finishGame();
        }
        if(lifeCount - deathCount < 1){
            Variables.stageCleared = false;
            Variables.score = 0;
            scoreCount = 0;
            scorePoints();
            render(Game.getGraphics());
            finishGame();
        }
    }

    @Override
    public void setName(String name) {
    }
}