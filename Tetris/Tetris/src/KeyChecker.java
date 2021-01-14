import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyChecker extends KeyAdapter{

    Gameplay gameplay;
    
    public KeyChecker(Gameplay gameplay) {

        this.gameplay = gameplay;

    }

    @Override
    public void keyPressed(KeyEvent e) {
        gameplay.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {

        gameplay.keyReleased(e);

    }
}