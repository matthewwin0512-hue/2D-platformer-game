package game;

import city.cs.engine.*;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;

import javax.swing.*;

import java.awt.*;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import game.levels.Level1;

/** Your main game entry point */

public class Game {
    private JFrame frame;

    /** Initialise a new Game. */
    public Game() {
        frame = new JFrame("City Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.setResizable(false);

        new Level1(frame); // All game content moves HERE
    }

    /** Run the game. */
    public static void main(String[] args) {
        new Game();
    }
}