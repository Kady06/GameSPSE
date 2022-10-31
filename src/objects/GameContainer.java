package objects;

import main.Game;

import static utilz.Constants.ObjectConstants.BOX;

public class GameContainer extends GameObject{

    public GameContainer(int x, int y, int objType) {
        super(x, y, objType);
        createHitBox();
    }

    private void createHitBox() {

        if (objType == BOX) {
            initHitbox(25, 18);
            xDrawOffset = (int) (7 * Game.SCALE);
            yDrawOffset = (int) (12 * Game.SCALE);

        } else {
            initHitbox(23, 25);
            xDrawOffset = (int) (8 * Game.SCALE);
            yDrawOffset = (int) (5 * Game.SCALE);
        }

        hitbox.y += yDrawOffset + (int) (Game.SCALE * 2);
        hitbox.x += xDrawOffset / 2;
    }

    public void update() {
        if (doAnimation)
            updateAnimationTick();
    }

}
