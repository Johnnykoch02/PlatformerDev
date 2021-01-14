import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Tile {

    private int x;
    private int y;
    private int width;
    private int height;

     private Rectangle hitBox;
    private Gameplay gameplay;
    private BufferedImageHandler tileImgHandlr;
    private BufferedImage tileImg;

    private Player player;
    private boolean collisionCheck;

    private int type;

    public Tile(Gameplay gameplay) {
      

        
        hitBox = new Rectangle( x, y, width, height);
        this.gameplay = gameplay;
        

    }  
    
    public void init() {

        width = 40;
        height = 40;
        hitBox = new Rectangle(x, y, width, height); 
        try {
            tileImgHandlr =new BufferedImageHandler("tile");
            tileImgHandlr.loadImgs();
            tileImg = tileImgHandlr.tileBufferedImage(type);
            }catch(IOException e){
                System.out.println("NOT WORKING");
            }

     }
    
    public void setGP(Gameplay gameplay) {

        this.gameplay = gameplay;

    } 
    
    public void setTileType(int type) {

        this.type = type;
        init();


    }

    public void setPosition(int x, int y) {

        this.x = (x*40) - 40;
        this.y = (y*40) - 40;

        hitBox.x = this.x;
        hitBox.y = this.y;

     

    }

    public int getType() {
        return type;
    }

    public Rectangle getRect() {

        return hitBox;

    }

    public boolean checkCollisions(Player player) {

    


       collisionCheck = false;

        this.player = player;

        if(player.hitBox.intersects(this.hitBox))
        collisionCheck = true;

        return collisionCheck;

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void draw(Graphics2D gtd) throws FileNotFoundException{

        gtd.drawImage(tileImg, x, y, null);
        

    }

    public Rectangle gethB() {
        return hitBox;
    }


}
// 0 - air
// 1 - grass
// 2 - water
