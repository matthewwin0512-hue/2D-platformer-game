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
        // Initialize BGM system
        BGM.setup();
        loadLevel(currentLevelNumber);
    }

    private void loadLevel(int levelNumber) {
        if (currentLevel != null) {
            currentLevel.destroy();
        }

        switch(levelNumber) {
            /**
            case 1:
                currentLevel = new Level1(this);
                BGM.playLevelBGM("level1");
                break;
             **/
            case 1:
                currentLevel = new Level2(this);
                BGM.playLevelBGM("level2");
                break;
                /**
            case 3:
                currentLevel = new Level3(this);
                BGM.playLevelBGM("boss"); // Example: level 3 uses boss music
                break;
                 **/
            default:
                throw new IllegalArgumentException("Invalid level number");
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