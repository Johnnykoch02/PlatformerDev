import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.awt.image.BufferedImage;


public class BufferedImageHandler {

    private String ImgType;

    //Char img array

    private BufferedImage[] charImg = new BufferedImage[6];   
    private int i;
    private int j;
    private int checkLeft = 0;
    private int checkRight = 0;
    private BufferedImage[] tileImg = new BufferedImage[3];

    private BufferedImage levelTest;
    String dir;
    


public BufferedImageHandler(String ImgType) throws IOException {

   
    dir = System.getProperty("user.dir");
    dir = dir.replace('\\', '/');
   
   


}
public void loadImgs() throws FileNotFoundException, IOException {
      
    charImg[0] = ImageIO.read(new File(dir + "/tetris/recources/Player/PlayerStill.png"));
    charImg[1] = ImageIO.read(new File(dir + "/tetris/recources/Player/PlayerLeftOdd.png"));
    charImg[2] = ImageIO.read(new File(dir + "/tetris/recources/Player/PlayerLeftEven.png"));
    charImg[3] = ImageIO.read(new File(dir + "/tetris/recources/Player/PlayerRightOdd.png"));
    charImg[4] = ImageIO.read(new File(dir + "/tetris/recources/Player/PlayerRightEven.png"));
    charImg[5] = ImageIO.read(new File(dir + "/tetris/recources/Player/PlayerJump.png"));
    tileImg[1] = ImageIO.read(new File(dir + "/tetris/recources/Tiles/1.png"));
    tileImg[2] = ImageIO.read(new File(dir + "/tetris/recources/Tiles/2.png"));
    levelTest = ImageIO.read(new File(dir +  "/tetris/recources/levels/testTerrain.png"));


   //jump

}

public BufferedImage charBufferedImage(boolean left, boolean right, boolean jump) {
 
    i = 0;


    if(jump)
     i = 5;
    if(left && !right)
        if(checkLeft == 0)
        {
            i = 4;
            checkLeft++;
        }
        else if(checkLeft == 1)
        {
            i = 3;
            checkLeft--;
        }
   if(right && !left)
        if(checkRight == 0)
        {
            i = 2; 
            checkRight++;
        }
        else if(checkRight == 1)
        {
            i = 1;
            checkRight--;
        }
    return charImg[i];
}

    public BufferedImage tileBufferedImage(int type) {

        j = 0;

        if(type == 1) {
           j = 1;  
        }
       
        if(type == 2) {
             j = 2;
        }
       


        return tileImg[j];
    }

    public BufferedImage levelBufferedImage(int levelNum) {

        return levelTest;
    }
}