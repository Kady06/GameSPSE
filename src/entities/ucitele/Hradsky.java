package entities.ucitele;

import entities.Enemy;
import entities.Player;
import main.Game;

import java.awt.geom.Rectangle2D;

import static utilz.Constants.Directions.RIGHT;
import static utilz.Constants.EnemyConstants.*;

public class Hradsky extends Enemy {

    private int attackBoxOffsetX;

    public Hradsky(float x, float y) {
        super(x, y, HRADSKY_WIDTH, HRADSKY_HEIGHT, HRADSKY);
        initHitbox(0,0);
         initAttackBox();
    }

    private void initAttackBox() {
        attackBox = new Rectangle2D.Float(x, y, (int) (82 * Game.SCALE), (int) (19 * Game.SCALE));
        attackBoxOffsetX = (int) (Game.SCALE * 30);
    }

    public void update(int[][] lvlData, Player player) {
        updateBehavior(lvlData, player);
        updateAnimationTick();
        updateAttackBox();
    }



    private void updateAttackBox() {
        attackBox.x = hitbox.x - attackBoxOffsetX;
        attackBox.y = hitbox.y;
    }
    private void updateBehavior(int[][] lvlData, Player player) {
        if (firstUpdate)
            firstUpdateCheck(lvlData);


        if (inAir)
            updateInAir(lvlData);
        else {
            switch (state) {
                case IDLE:
                    newState(RUNNING);
                    break;
                case RUNNING:
                    if (canSeePlayer(lvlData, player)) {
                        turnTowardsPlayer(player);
                        if (isPlayerCloseForAttack(player))
                            newState(ATTACK);
                    }
                    move(lvlData);
                    break;

                case ATTACK:
                    if (aniIndex == 0)
                        attackChecked = false;

                    if (aniIndex == 3 && !attackChecked)
                        checkEnemyHit(attackBox ,player);
                    break;
                case HIT:
                    break;
            }
        }
    }

    public int flipX() {
        if (walkDir == RIGHT)
            return width;
        else
            return 0;
    }

    public int flipW() {
        if (walkDir == RIGHT)
            return -1;
        else
            return 1;

    }

}
