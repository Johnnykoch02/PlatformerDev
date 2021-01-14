import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.image.BufferedImage;


public class Gameplay extends JPanel implements ActionListener {

    private Player player;
    private Timer gameTimer;
    private Camera cam;
    private BufferedImage level;
    BufferedImageHandler bf;

    
    public int unitSize = 40;
    public int screenX = 1280;
    public int screenY = 720;

    
    


    //mapping!!!

    ArrayList<Tile> map = new ArrayList<Tile>();


    //movement booleans



    public Gameplay(){

        
        
        player = new Player( 640, 360, this);
        cam = new Camera( 0, 0);
     
        try {
            bf = new BufferedImageHandler("");
            bf.loadImgs();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        level = bf.levelBufferedImage(1);
        
        loadImgLevel(level);



        //for map


        gameTimer = new Timer();
        gameTimer.schedule(new TimerTask(){

            @Override
            public void run() {
                // TODO Auto-generated method stub
                
                cam.refresh(player);
                player.refresh();
                
                repaint();

            }
        }, 0, 17);

    }

    public void paint(Graphics g) {
        super.paint(g);
        // for every unit(40px) draw line down screen
        for (int i = 0; i <= screenX; i += unitSize) {
            g.setColor(Color.blue);
            g.drawLine(i, 0, i, screenY);
        }

        // for every unit(40px) draw line across screen
        for (int i = 0; i <= screenY; i += unitSize) {
            g.setColor(Color.blue);
            g.drawLine(0, i, screenX, i);

        }

        // draw the map from the array values.

        Graphics2D gtd = (Graphics2D) g;

        gtd.translate( cam.getX(), cam.getY());

        try {
            player.draw(gtd);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try{
        for (Tile tile : map) {
           
            tile.draw(gtd);

            }
        }catch(NullPointerException e){
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        gtd.translate( -cam.getX(), -cam.getY());

    gtd.dispose();

    }



	public void keyPressed(KeyEvent e) {
        switch(e.getKeyChar()){
            case 'a':
            player.keyLeft = true;
            break;

            case 'd':
            player.keyRight = true;
            break;

            case ' ':
            player.keyJump = true;
            break;

            case 'w':
            player.keyUp = true;
            break;

            case 's':
            player.keyDown = true;
            break;
        }

	}

	public void keyReleased(KeyEvent e) {

        switch(e.getKeyChar()){
            case 'a':
            player.keyLeft = false;
            break;

            case 'd':
            player.keyRight = false;
            break;

            case ' ':
            player.keyJump = false;
            break;

            case 'w':
            player.keyUp = false;
            break;

            case 's':
            player.keyDown = false;
            break;



        }

    }
    
    public void loadImgLevel(BufferedImage img) {
        int h = img.getHeight();
        int w = img.getWidth();

        int m = 0;

        for(int xx = 0; xx <w; xx++) {
            for(int yy = 0; yy <h; yy++)
            {
                int pixel = img.getRGB(xx, yy);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

                    if(red == 185 && green == 122 && blue == 86)
                    {
                        map.add(new Tile(this));
                        map.get(m).setTileType(1);
                        map.get(m).setPosition(xx, yy);
                        m++;
                    }
                    if(red == 63 && green == 72 && blue == 204)
                    {
                        map.add(new Tile(this));
                        map.get(m).setTileType(2);
                        map.get(m).setPosition(xx, yy);
                        m++;
                    }
                    if(red == 0 && green == 0 && blue == 0)
                    {
                        player.setX(xx);
                        player.setY(yy*40);
                    }
                }
                
        }

        

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

    }


    
}
