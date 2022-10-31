package main;

import inputs.KeyboardInputs;
import inputs.MouseInpunts;

import javax.swing.*;
import java.awt.*;

import static main.Game.GAME_HEIGHT;
import static main.Game.GAME_WIDTH;



public class GamePanel extends JPanel {
    private MouseInpunts mouseInpunts;
    private Game game;



    public GamePanel(Game game) {


        mouseInpunts = new MouseInpunts(this);
        this.game = game;


        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInpunts);
        addMouseMotionListener(mouseInpunts);

    }




    private void setPanelSize() {
        Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
        setPreferredSize(size);
        System.out.println("size : " + GAME_WIDTH + " : " + GAME_HEIGHT);

    }

    public void updateGame() {

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.render(g);

    }


    public Game getGame() {
        return game;
    }
}
