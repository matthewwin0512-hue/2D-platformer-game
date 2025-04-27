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
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                student.startWalking(-6);
                student.setFacingRight(false);
                break;
            case KeyEvent.VK_RIGHT:
                student.startWalking(6);
                student.setFacingRight(true);
                break;
            case KeyEvent.VK_SPACE:
                student.jump(12);
                break;

            case KeyEvent.VK_A: // Attack key
                if (student.canAttack()) {
                    student.attack();
                }
                break;
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