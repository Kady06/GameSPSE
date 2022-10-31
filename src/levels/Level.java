package levels;

import entities.Crabby;
import entities.ucitele.Hradsky;
import main.Game;
import objects.Cannon;
import objects.GameContainer;
import objects.Potion;
import objects.Spike;
import utilz.HelpMethods;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static utilz.HelpMethods.*;

public class Level {


    private BufferedImage img;
    private int[][] lvlData;
    private ArrayList<Crabby> crabs;
    private ArrayList<Potion> potions;
    private  ArrayList<GameContainer> containers;
    private ArrayList<Spike> spikes;
    private ArrayList<Cannon> cannons;
    private int lvlTilesWide;
    private int maxTilesOffset;
    private int maxLvlOffsetX;
    private Point playerSpawn;
    private ArrayList<Hradsky> hradsky;



    public Level(BufferedImage img) {
        this.img = img;
        createLevelData();
        createEnemies();
        createPotions();
        createContainers();
        createSpikes();
        createCannons();
        calcLvlOffsets();
        calcPlayerSpawn();

    }

    private void createCannons() {
        cannons = HelpMethods.GetCannons(img);

    }

    private void createSpikes() {
        spikes = HelpMethods.GetSpikes(img);

    }

    private void createContainers() {
        containers = HelpMethods.GetContainers(img);
    }

    private void createPotions() {
        potions = HelpMethods.GetPotions(img);
    }

    private void calcPlayerSpawn() {
        playerSpawn = GetPlayerSpawn(img);

    }

    private void calcLvlOffsets() {
        lvlTilesWide = img.getWidth();
        maxTilesOffset = lvlTilesWide - Game.TILES_IN_WIDTH;
        maxLvlOffsetX = Game.TILES_SIZE * maxTilesOffset;

    }

    private void createEnemies() {
        crabs = GetCrabs(img);
        hradsky = GetHradsky(img);

    }

    private void createLevelData() {
        lvlData = GetLevelData(img);

    }

    public int getSpriteIndex(int x, int y) {
        return lvlData[y][x];
    }

    public int[][] getLevelData() {
        return lvlData;
    }

    public int getLvlOffsetX() {
        return maxLvlOffsetX;
    }
    public ArrayList<Crabby> getCrabs() {
        return crabs;
    }

    public Point getPlayerSpawn() {
        return playerSpawn;
    }

    public ArrayList<Potion> getPotions() {
        return potions;
    }

    public ArrayList<GameContainer> getContainers() {
        return containers;
    }

    public ArrayList<Spike> getSpikes() {
        return spikes;
    }

    public ArrayList<Cannon> getCannons() {
        return cannons;
    }

    public ArrayList<Hradsky> getHradsky() {
        return hradsky;
    }
}
