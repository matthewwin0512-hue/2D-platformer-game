package game;

import java.awt.event.*;

public class PlayerControls extends KeyAdapter {
    private Player student; // The student character to control

    public PlayerControls(Player student) {
        this.student = student;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_LEFT) {
            student.startWalking(-5); // Move left
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            student.startWalking(5); // Move right
        } else if (keyCode == KeyEvent.VK_SPACE) {
            student.jump(12); // Jump
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_RIGHT) {
            student.stopWalking(); // Stop moving
        }
    }
}