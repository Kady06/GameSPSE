package gamestates;

import main.Game;
import ui.PauseButton;
import ui.UrmButton;
import utilz.LoadSave;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static utilz.Constants.UI.URMButtons.URM_SIZE;

public class GameFinished extends State implements Statemethods{

    private UrmButton menuB;
    private BufferedImage backgroundImg, backgroundImgPink;
    private int menuX, menuY, menuWidth, menuHeight;


    public GameFinished(Game game) {
        super(game);
        loadButtons();
        loadBackground();
        backgroundImgPink = LoadSave.GetSpriteAtlas(LoadSave.MENU_BACKGROUND_IMG);
    }

    private void loadButtons() {
        int menuX = (int) (387 * Game.SCALE);
        int menuY = (int) (325 * Game.SCALE);

        menuB = new UrmButton(menuX, menuY, URM_SIZE, URM_SIZE, 2);
    }

    private void loadBackground() {

    }

    @Override
    public void update() {
        menuB.update();

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(backgroundImg, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT, null);

        menuB.draw(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (isIn(e, menuB)) {
            menuB.setMousePressed(true);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (isIn(e, menuB)) {
            if (menuB.isMousePressed())
                Gamestate.state = Gamestate.MENU;
        }

        menuB.resetBools();


    }

    @Override
    public void mouseMoved(MouseEvent e) {
        menuB.setMouseOver(false);

        if (isIn(e, menuB))
            menuB.setMouseOver(true);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
            Gamestate.state = Gamestate.MENU;

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    private boolean isIn (MouseEvent e, PauseButton b){
        return b.getBounds().contains(e.getX(), e.getY());
    }

}
