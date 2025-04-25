package game;

import city.cs.engine.*;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;

import javax.swing.*;

import java.awt.*;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

// Your main game entry point //

public class Game {
    private Level currentLevel;
    private int currentLevelNumber = 1;

    public int getCurrentLevelNumber() {
        return currentLevelNumber;
    }

    public Game() {
        loadLevel(currentLevelNumber);
    }

    private void loadLevel(int levelNumber) {
        if (currentLevel != null) {
            currentLevel.destroy();
        }

        switch(levelNumber) {
            //case 1: currentLevel = new Level1(this); break;
            case 1: currentLevel = new Level2(this); break;
            //case 3: currentLevel = new Level3(this); break;
            default: throw new IllegalArgumentException("Invalid level number");
        }

        currentLevel.start();
    }

    public void goToNextLevel() {
        currentLevelNumber++;
        loadLevel(currentLevelNumber);
    }

    public static void main(String[] args) {
        new Game();
    }
}