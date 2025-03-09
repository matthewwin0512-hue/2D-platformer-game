package game;

import java.awt.event.*;

/**
 * A class representing the keyboard controls for the player character.
 * This class extends KeyAdapter to handle key press and release events for player movement.
 */
public class PlayerControls extends KeyAdapter {
    private Player student; // The player character to control

    public PlayerControls(Player student) {
        this.student = student; // Initialize the player reference
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode(); // Get the key code of the pressed key

        // Move the player left when the LEFT arrow key is pressed
        if (keyCode == KeyEvent.VK_LEFT) {
            student.startWalking(-5); // Move left at a speed of -5
        }
        // Move the player right when the RIGHT arrow key is pressed
        else if (keyCode == KeyEvent.VK_RIGHT) {
            student.startWalking(5); // Move right at a speed of 5
        }
        // Make the player jump when the SPACE key is pressed
        else if (keyCode == KeyEvent.VK_SPACE) {
            student.jump(12); // Jump with a force of 12
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode(); // Get the key code of the released key

        // Stop the player's movement when the LEFT or RIGHT arrow key is released
        if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_RIGHT) {
            student.stopWalking(); // Stop the player's movement
        }
    }
}