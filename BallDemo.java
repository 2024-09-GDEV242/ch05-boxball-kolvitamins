import java.awt.Color;
import java.util.Random;
import java.util.ArrayList;

/**
 * Class BallDemo - a short demonstration showing animation with the 
 * Canvas class. 
 *
 * @author Jeffrey Kolvites
 * @version 2024.10.18
 */

public class BallDemo   
{
    private Canvas myCanvas;
    private BoxBall boxBall;
    private ArrayList<BoxBall> boxList;
    /**
     * Create a BallDemo object. Creates a fresh canvas and makes it visible.
     */
    public BallDemo()
    {
        myCanvas = new Canvas("Ball Demo", 600, 500);
    }

    /**
     * Simulate two bouncing balls
     */
    public void bounce()
    {
        int ground = 400;   // position of the ground line

        myCanvas.setVisible(true);

        // draw the ground
        myCanvas.setForegroundColor(Color.BLACK);
        myCanvas.drawLine(50, ground, 550, ground);

        // create and show the balls
        BouncingBall ball = new BouncingBall(50, 50, 16, Color.BLUE, ground, myCanvas);
        ball.draw();
        BouncingBall ball2 = new BouncingBall(70, 80, 20, Color.RED, ground, myCanvas);
        ball2.draw();

        // make them bounce
        boolean finished =  false;
        while (!finished) {
            myCanvas.wait(50);           // small delay
            ball.move();
            ball2.move();
            // stop once ball has travelled a certain distance on x axis
            if(ball.getXPosition() >= 550 || ball2.getXPosition() >= 550) {
                finished = true;
            }
        }
    }
    
    /**
     * Creates a box then puts n amount of balls in the box. Gives each ball a random position, diameter,
     * and color. 
     * @param n amount of balls to create in the box
     */
    public void boxBounce(int n)
    {
        myCanvas.setVisible(true);
        
        //draw the box
        myCanvas.drawLine(50,400,550,400);
        myCanvas.drawLine(50,100,550,100);
        myCanvas.drawLine(50,100,50,400);
        myCanvas.drawLine(550,100,550,400);
        
        //for random color at start
        boxList = new ArrayList<BoxBall>();
        Random xRand = new Random();
        Random yRand = new Random();
        Random diaRand = new Random();
        Random colorRand = new Random();
        
        //create balls with random position, diameter and color
        for(int i = 0; i < n; i++)
        {
            int r = colorRand.nextInt(200) + 15;
            int g = colorRand.nextInt(200) + 15;
            int b = colorRand.nextInt(200) + 15;
            Color randomColor = new Color(r,g,b);
            boxBall = new BoxBall(xRand.nextInt(400), yRand.nextInt(550), diaRand.nextInt(10) + 10, randomColor, 400, 100, 50, 550, myCanvas);
            boxList.add(boxBall);
        }
        
        //make balls move and redraw lines as they bounce
        boolean finished = false;
        while(!finished){
            for(int i = 0; i < boxList.size(); i++)
            {
                BoxBall boxBalls = boxList.get(i); 
                boxBalls.draw();
                boxBalls.boxMove();
                myCanvas.drawLine(50,400,550,400);
                myCanvas.drawLine(50,100,550,100);
                myCanvas.drawLine(50,100,50,400);
                myCanvas.drawLine(550,100,550,400);
            }
            myCanvas.wait(50);
        }
    }
    
}
