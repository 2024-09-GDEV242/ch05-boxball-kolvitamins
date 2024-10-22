import java.awt.*;
import java.awt.geom.*;
import java.util.Random;
import java.util.ArrayList;

/**
 * Class BoxBall - a graphical ball that observes the effect of gravity. The ball
 * has the ability to move. Details of movement are determined by the ball itself. It
 * will fall downwards, accelerating with time due to the effect of gravity, and bounce
 * upward again when hitting the ground.
 *
 * This movement can be initiated by repeated calls to the "move" method.
 * 
 * @author Michael Kölling (mik)
 * @author David J. Barnes
 * @author Bruce Quig
 *
 * @version 2016.02.29
 */

public class BoxBall
{
    private Ellipse2D.Double circle;
    private Color color;
    private int diameter;
    private int xPosition;
    private int yPosition;
    private final int bottom;
    private final int top;
    private final int left;
    private final int right;
    private Canvas canvas;
    private int ySpeed = 7;
    private int xSpeed = 7;
    private Random randomSpeed;
    
    /**
     * Constructor for objects of class BoxBall
     *
     * @param xPos  the horizontal coordinate of the ball
     * @param yPos  the vertical coordinate of the ball
     * @param ballDiameter  the diameter (in pixels) of the ball
     * @param ballColor  the color of the ball
     * @param bottomPos  the position of the ground (where the wall will bounce)
     * @param topPos  the position of the top wall
     * @param leftPos  the position of the left wall
     * @param rightPos  the position of the right wall
     * @param drawingCanvas  the canvas to draw this ball on
     */
    public BoxBall(int xPos, int yPos, int ballDiameter, Color ballColor,
                        int bottomPos, int topPos, int leftPos, int rightPos, Canvas drawingCanvas)
    {
        xPosition = xPos;
        yPosition = yPos;
        color = ballColor;
        diameter = ballDiameter;
        bottom = bottomPos;
        top = topPos;
        left = leftPos;
        right = rightPos;
        canvas = drawingCanvas;
    }

    /**
     * Draw this ball at its current position onto the canvas.
     **/
    public void draw()
    {
        canvas.setForegroundColor(color);
        canvas.fillCircle(xPosition, yPosition, diameter);
    }

    /**
     * Erase this ball at its current position.
     **/
    public void erase()
    {
        canvas.eraseCircle(xPosition, yPosition, diameter);
    }    

    /**
     * Move this ball according to its position and speed and redraw. If bouncing off a wall reverse at a 
     * random speed and color.
     **/
    public void boxMove()
    {
        
        erase();
        Random randomSpeed = new Random();
        Random colorRand = new Random();
        
        yPosition += ySpeed + randomSpeed.nextInt(2);// x speed and y speed randomized, but not 0
        xPosition += xSpeed + randomSpeed.nextInt(2);
        
        float r = colorRand.nextFloat();
        float g = colorRand.nextFloat();
        float b = colorRand.nextFloat();
        Color randomColor = new Color(r,g,b);

        // check if it has hit the ground
        if (yPosition >= (bottom - diameter)){
            yPosition = (int)(bottom - diameter);
            ySpeed = -ySpeed + randomSpeed.nextInt(5);
            color = randomColor;
        }
        
        else if (yPosition <= top){
            yPosition = (int)(top);
            ySpeed = -ySpeed + randomSpeed.nextInt(5);
            color = randomColor;
        }
        
        else if (xPosition >= (right - diameter)){
            xPosition = (int)(right - diameter);
            xSpeed = -xSpeed + randomSpeed.nextInt(5);
            color = randomColor;
        }
        
        else if (xPosition <= (left)){
            xPosition = (int)(left);
            xSpeed = -xSpeed + randomSpeed.nextInt(5);
            color = randomColor;
        }

        // draw again at new position
        draw();
    }    

    /**
     * return the horizontal position of this ball
     */
    public int getXPosition()
    {
        return xPosition;
    }

    /**
     * return the vertical position of this ball
     */
    public int getYPosition()
    {
        return yPosition;
    }
}
