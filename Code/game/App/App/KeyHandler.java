package game.App.App;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed;

    @Override
    public void keyTyped(KeyEvent e) {} //we don't need this

    @Override
    public void keyPressed(KeyEvent e) {
        // If one of the following keys is pressed, it updates the corresponding variable
        int code = e.getKeyCode(); //each key on a keyboard has an associated keycode (e.g. A = 65)
        if (code == KeyEvent.VK_W) {upPressed = true;}
        if (code == KeyEvent.VK_S) {downPressed = true;}
        if (code == KeyEvent.VK_A) {leftPressed = true;}
        if (code == KeyEvent.VK_D) {rightPressed = true;}
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // If one of the following keys is pressed, it updates the corresponding variable
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {upPressed = false;}
        if (code == KeyEvent.VK_S) {downPressed = false;}
        if (code == KeyEvent.VK_A) {leftPressed = false;}
        if (code == KeyEvent.VK_D) {rightPressed = false;}
    }
}
