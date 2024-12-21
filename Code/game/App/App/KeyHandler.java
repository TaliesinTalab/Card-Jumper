package game.App.App;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    private boolean upPressed, downPressed, leftPressed, rightPressed;

    // Getter
    public boolean getUpPressed() {
        return upPressed;
    }
    public boolean getDownPressed() {
        return downPressed;
    }
    public boolean getLeftPressed() {
        return leftPressed;
    }
    public boolean getRightPressed() {
        return rightPressed;
    }

    // Other Methods

    @Override
    public void keyTyped(KeyEvent e) {} //we don't need this

    /**
     * If one of the following keys is pressed, it updates the corresponding variable
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode(); //each key on a keyboard has an associated keycode (e.g. A = 65)
        if (code == KeyEvent.VK_W) {upPressed = true;}
        if (code == KeyEvent.VK_S) {downPressed = true;}
        if (code == KeyEvent.VK_A) {leftPressed = true;}
        if (code == KeyEvent.VK_D) {rightPressed = true;}
    }

    /**
     * If one of the following keys is pressed, it updates the corresponding variable
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {upPressed = false;}
        if (code == KeyEvent.VK_S) {downPressed = false;}
        if (code == KeyEvent.VK_A) {leftPressed = false;}
        if (code == KeyEvent.VK_D) {rightPressed = false;}
    }
}
