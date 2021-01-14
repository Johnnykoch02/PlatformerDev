import java.awt.Color;
import java.awt.Rectangle;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.ImageIcon;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class Player {

    Gameplay gameplay;
    int x;
    int y;
    int width;
    int height;

    private double xVelocity;
    private double yVelocity;

    private int jumpCounter;

    Rectangle hitBox;

    // booleans
    public boolean keyJump;
    public boolean keyLeft;
    public boolean keyRight;
    public boolean keyDown;
    public boolean keyUp;

    
    private boolean northwest, north, northeast, east, southeast, south, southwest, west;
    private boolean isJumping;
    private boolean onGround;

    private BufferedImageHandler bImgHndlr;
    private BufferedImage charSprite;

    public Player(int x, int y, Gameplay gameplay) {

        this.gameplay = gameplay;
        this.x = x;
        this.y = y;

        width = gameplay.unitSize * 1;
        height = gameplay.unitSize * 1;
        hitBox = new Rectangle(x, y, width, height);
        
        

        try {
            bImgHndlr = new BufferedImageHandler("Char");
            bImgHndlr.loadImgs();
            
            
        

        } catch (IOException e) {
           
        };
    

    }

    public void refresh() {
       charSprite = bImgHndlr.charBufferedImage(keyLeft, keyRight, keyJump);
       
        // Vertical Movement
        if (keyLeft && keyRight || !keyLeft && !keyRight)
            xVelocity *= 0.8;
        else if (keyLeft && !keyRight)
            xVelocity--;
        else if (!keyLeft && keyRight)
            xVelocity++;

        // velocity control
        if (xVelocity > 0 && xVelocity < 0.75 || xVelocity < 0 && xVelocity > -0.75)
            xVelocity = 0;
        if (xVelocity > 5)
            xVelocity = 5;
        else if (xVelocity < -5)
            xVelocity = -5;

        if (keyJump && jumpCounter == 0) {

            yVelocity = -6;
            jumpCounter++;

            isJumping = true;

        

        }


        yVelocity += .35;

        x += xVelocity;
        y += yVelocity;

        hitBox.x = x;
        hitBox.y = y;


        //-y is up +y is down 

            if(xVelocity < 0 && yVelocity < 0){
                northwest = true;
                north = false;
                northeast = false;
                east = false;
                southeast = false;
                south = false;
                southwest = false;
                west = false;
            }
            else if(xVelocity == 0 && yVelocity < 0) {
                northwest = false;
                north = true;
                northeast = false;
                east = false;
                southeast = false;
                south = false;
                southwest = false;
                west = false;
            }
            else if(xVelocity > 0 && yVelocity < 0) {
                northwest = false;
                north = false;
                northeast = true;
                east = false;
                southeast = false;
                south = false;
                southwest = false;
                west = false;
            }
            else if(xVelocity > 0 && yVelocity == 0) {
                northwest = false;
                north = false;
                northeast = false;
                east = true;
                southeast = false;
                south = false;
                southwest = false;
                west = false;
            }
            else if(xVelocity > 0 && yVelocity > 0) {
                northwest = false;
                north = false;
                northeast = false;
                east = false;
                southeast = true;
                south = false;
                southwest = false;
                west = false;
            }
            else if(x == 0 && yVelocity > 0) {
                northwest = false;
                north = false;
                northeast = false;
                east = false;
                southeast = false;
                south = true;
                southwest = false;
                west = false;
            }
            else if(xVelocity < 0 && yVelocity > 0) {
                northwest = false;
                north = false;
                northeast = false;
                east = false;
                southeast = false;
                south = false;
                southwest = true;
                west = false;
            }
            else if(xVelocity < 0 && yVelocity == 0) {
                northwest = false;
                north = false;
                northeast = false;
                east = false;
                southeast = false;
                south = false;
                southwest = false;
                west = true;
            }  

       
        for(int i = 0; i < gameplay.map.size(); i++) {


            if (gameplay.map.get(i).getRect().intersects(hitBox))
            {   
                int t = (gameplay.map.get(i).getType());
                if( t == 1)
                    {
                    if(northwest) {
                        yVelocity = -yVelocity*1.3;
                        xVelocity = -xVelocity/2;
                    }
                    else if(north) {
                        yVelocity = -yVelocity*1.3;
                    }
                    else if(northeast) {
                        yVelocity = -yVelocity*1.3;
                        xVelocity = -xVelocity/2;
                    }
                    else if(east) {
                        xVelocity= -xVelocity/2;
                    }
                    else if(southeast) {
                        yVelocity = -yVelocity*1.3;
                        xVelocity = -xVelocity/2;
                    }
                    else if(south) {
                        yVelocity = 0;
                    }
                    else if(southwest) {
                        yVelocity = -yVelocity*1.3;
                        xVelocity = -xVelocity/2;
                    }
                    else if(west) {
                        xVelocity = -xVelocity/2;
                    }

                }
                if(isJumping)
                {
                jumpCounter = 0;
                isJumping = false;
                }

                // if(hitBox.y < gameplay.map.get(i).getY() && !isJumping && t == 1)
                // {
                
                // yVelocity = 0;
                    
                // }

                // else if(hitBox.y < gameplay.map.get(i).getY() && !isJumping && t == 2)
                // {
                // y = gameplay.map.get(i).getY() - 20;
                // yVelocity /=2;
                // xVelocity/=2;
                    
                // }
                

             


            }

        }

    }

    public void draw(Graphics2D gtd) throws FileNotFoundException {

        gtd.drawImage(charSprite, x, y, null);
        gtd.setColor(Color.black);
        gtd.drawRect(x, y, width, height);
        

    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}

