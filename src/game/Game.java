package game;

import javax.swing.*;

// Your main game entry point //

public class Game {
    private Level currentLevel;
    private int currentLevelNumber = 1;
    private JFrame frame;

    public int getCurrentLevelNumber() {
        return currentLevelNumber;
    }

    public Game() {
        frame = new JFrame("City Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 750);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        // Initialize BGM system
        BGM.setup();
        loadLevel(currentLevelNumber);
    }

    private void loadLevel(int levelNumber) {
        if (currentLevel != null) {
            currentLevel.stop();
            frame.remove(currentLevel.getView());
            currentLevel.destroy();
        }

        switch(levelNumber) {

            case 1:
                currentLevel = new Level1(this);
                BGM.playLevelBGM("level1");
                break;


            case 2:
                currentLevel = new Level2(this);
                BGM.playLevelBGM("level2");
                break;


            case 3:
                currentLevel = new Level3(this);
                BGM.playLevelBGM("level3");
                break;

            default:
                throw new IllegalArgumentException("Invalid level number");
        }

        frame.add(currentLevel.getView());
        frame.revalidate();
        frame.repaint();

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