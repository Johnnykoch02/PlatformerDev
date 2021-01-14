import java.awt.Color;
import javax.swing.JFrame;

public class App {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        JFrame frame = new JFrame("Platformer: by John");
        Gameplay gameplay = new Gameplay();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1280,750);
        frame.setResizable(false);
        frame.setBackground(Color.LIGHT_GRAY);
		frame.setVisible(true);
        frame.add(gameplay);
        frame.setLocationRelativeTo(null);
        frame.addKeyListener(new KeyChecker(gameplay));

	
		

	}

}
