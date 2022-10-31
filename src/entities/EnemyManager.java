package entities;

import entities.ucitele.Hradsky;
import gamestates.Playing;
import levels.Level;
import utilz.LoadSave;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static utilz.Constants.EnemyConstants.*;

public class EnemyManager {

    private Playing playing;
    private BufferedImage[][] crabbyArr;
    private ArrayList<Crabby> crabbies = new ArrayList<>();

    private BufferedImage[][] hradskyArr;
    private ArrayList<Hradsky> hradove;

    public EnemyManager(Playing playing) {
        this.playing = playing;
        loadEnemyImgs();
    }

    public void loadEnemies(Level level) {
        crabbies = level.getCrabs();
        hradove = level.getHradsky();
    }

    public void update(int[][] lvlData, Player player) {
        boolean isAnyActive = false;
        for (Crabby c : crabbies)
            if (c.isActive()) {
                c.update(lvlData, player);
                isAnyActive = true;
            }
        for (Hradsky h : hradove)
            if (h.isActive()) {
                h.update(lvlData, player);
                isAnyActive = true;
            }
        if (!isAnyActive)
            playing.setLevelCompleted(true);
    }
    public void draw(Graphics g, int xLvlOffset) {
        drawCrabs(g, xLvlOffset);
        drawHradsky(g, xLvlOffset);

    }

    private void drawHradsky(Graphics g, int xLvlOffset) {
        for (Hradsky h : hradove)
            if (h.isActive()) {
                g.drawImage(hradskyArr[h.getState()][h.getAniIndex()], (int) (h.getHitbox().x - xLvlOffset - HRADSKY_DRAWOFFSET_X + h.flipX()), (int) (h.getHitbox().y - HRADSKY_DRAWOFFSET_Y),HRADSKY_WIDTH * h.flipW(), HRADSKY_HEIGHT, null );
            }

    }

    private void drawCrabs(Graphics g, int xLvlOffset) {
        for (Crabby c : crabbies)
            if (c.isActive()) {
                g.drawImage(crabbyArr[c.getState()][c.getAniIndex()], (int) (c.getHitbox().x) - xLvlOffset - CRABBY_DRAWOFFSET_X + c.flipX(), (int) (c.getHitbox().y) - CRABBY_DRAWOFFSET_Y, CRABBY_WIDTH * c.flipW(), CRABBY_HEIGHT, null);
//            c.drawHitbox(g, xLvlOffset);
//                c.drawAttackBox(g, xLvlOffset);
        }

    }

    public void checkEnemyHit(Rectangle2D.Float attackBox) {
        for (Crabby c : crabbies)
            if (c.getCurrentHealth() > 0)
                if (c.isActive())
                    if (attackBox.intersects(c.getHitbox())) {
                        c.hurt(10);
                        return;
                    }
        for (Hradsky h : hradove)
            if (h.getCurrentHealth() > 0)
                if (h.isActive())
                    if (attackBox.intersects(h.getHitbox())) {
                        h.hurt(10);
            }
    }

    private void loadEnemyImgs() {
        crabbyArr = new BufferedImage[5][9];
        BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.CRABBY_SPRITE);
        for (int j = 0; j < crabbyArr.length; j++)
            for (int i = 0; i < crabbyArr[j].length; i++)
                crabbyArr[j][i] = temp.getSubimage(i * CRABBY_WIDTH_DEFAULT, j * CRABBY_HEIGHT_DEFAULT, CRABBY_WIDTH_DEFAULT, CRABBY_HEIGHT_DEFAULT);

        // Hradský
        hradskyArr = new BufferedImage[5][9];
        BufferedImage tempHradsky = LoadSave.GetSpriteAtlas(LoadSave.HRADSKY_SPRITE);
        for (int j = 0; j < crabbyArr.length; j++)
            for (int i = 0; i < crabbyArr[j].length; i++) {
                hradskyArr[j][i] = tempHradsky.getSubimage(i * HRADSKY_WIDTH_DEFAULT, j * HRADKSY_HEIGHT_DEFAULT, HRADSKY_WIDTH_DEFAULT, HRADKSY_HEIGHT_DEFAULT);
            }

    }

    public void resetAllEnemies() {
        for (Crabby c : crabbies)
            c.resetEnemy();
        for (Hradsky h : hradove)
            h.resetEnemy();
    }

}
