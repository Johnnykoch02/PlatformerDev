public class Camera {

    private double x, y;
    public int width;
    public int height;


    public Camera(double x, double y) {

        this.x = x;
        this.y = y;

        width = 1280;
        height = 720;


    }

    public void refresh(Player player) {

        x = -player.x + width/2;
        y = -player.y + height/2;

    }
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    
}
