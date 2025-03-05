package game;

import city.cs.engine.*;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;

import javax.swing.*;

import java.awt.*;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Your main game entry point
 */
public class Game {


    /** Initialise a new Game. */
    public Game() {

        //1. make an empty game world
        World world = new World();

        //2. populate it with bodies (ex: platforms, collectibles, characters)

        //make a ground platform
        Shape shape = new BoxShape(30, 0.5f);
        StaticBody ground = new StaticBody(world, shape);
        ground.setPosition(new Vec2(0f, -11.5f));

        // make a suspended platform
        Shape platformShape = new BoxShape(3, 0.5f);
        StaticBody platform1 = new StaticBody(world, platformShape);
        platform1.setPosition(new Vec2(3, -5f));

        StaticBody platform2 = new StaticBody(world, platformShape);
        platform2.setPosition(new Vec2(-5, -7f));

        //make a character (with an overlaid image)
        Shape studentShape = new BoxShape(01,2f);
        Player student = new Player(world, studentShape);
        student.setPosition(new Vec2(-14, -5));

        //Add the StudentController to the student
        PlayerControls studentController = new PlayerControls(student);

        //3. make a view to look into the game world
        BackgroundView view = new BackgroundView(world, 700, 600, "data/360_F_717598564_BcH9JsPcokbf9ddcgi8wXDmbsMUyr8Y8.jpg");
        view.addKeyListener(studentController);

        view.setFocusable(true);
        view.requestFocusInWindow();

        //optional: draw a 1-metre grid over the view
        // view.setGridResolution(1);


        //4. create a Java window (frame) and add the game
        //   view to it
        final JFrame frame = new JFrame("City Game");
        frame.add(view);

        // enable the frame to quit the application
        // when the x button is pressed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        // don't let the frame be resized
        frame.setResizable(false);
        // size the frame to fit the world view
        frame.pack();
        // finally, make the frame visible
        frame.setVisible(true);

        //optional: uncomment this to make a debugging view
         JFrame debugView = new DebugViewer(world, 700, 600);

        // start our game world simulation!
        world.start();
    }

    /** Run the game. */
    public static void main(String[] args) {

        new Game();
    }
}
