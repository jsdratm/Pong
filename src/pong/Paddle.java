/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pong;

/**
 *
 * @author jsdratm
 */
public class Paddle {
    private int paddlePositionX;
    private int paddlePositionY;
    private int paddleHeight;
    private int paddleWidth;

    public Paddle(int paddlePositionXIn, int paddlePositionYIn, int paddleWidthIn, int paddleHeightIn)
    {
        paddlePositionX = paddlePositionXIn;
        paddlePositionY = paddlePositionYIn;
        paddleHeight = paddleHeightIn;
        paddleWidth = paddleWidthIn;
    }

    public int getPaddlePositionX()
    {
        return paddlePositionX;
    }

    public int getPaddlePositionY()
    {
        return paddlePositionY;
    }

    public int getPaddleHeight()
    {
        return paddleHeight;
    }

    public int getPaddleWidth()
    {
        return paddleWidth;
    }

    public void setPaddlePosition(int xPosition, int yPosition)
    {
        paddlePositionX = xPosition;
        paddlePositionY = yPosition;
    }
}
