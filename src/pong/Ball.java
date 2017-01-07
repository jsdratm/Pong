/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pong;

/**
 *
 * @author jsdratm
 */
public class Ball {
    private int ballPositionX;
    private int ballPositionY;
    private int moveRateX;
    private int moveRateY;
    private int ballDiameter;
    private boolean isBallAccelerated;

    public Ball(int ballPositionXIn, int ballPositionYIn, int ballDiameterIn)
    {
        ballPositionX = ballPositionXIn;
        ballPositionY = ballPositionYIn;
        ballDiameter = ballDiameterIn;
        isBallAccelerated = false;
    }

    public boolean getIsBallAccelerated()
    {
        return isBallAccelerated;
    }

    public void accelerateBall()
    {
        if (!isBallAccelerated)
        {
            if (moveRateY < 0)
            {
                moveRateY--;
            }
            else
            {
                moveRateY++;
            }
            isBallAccelerated = true;
        }
    }

    public void decelerateBall()
    {
        if (isBallAccelerated)
        {
            if (moveRateY < 0)
            {
                moveRateY++;
            }
            else
            {
                moveRateY--;
            }
            isBallAccelerated = false;
        }
    }

    public int getBallDiameter()
    {
        return ballDiameter;
    }

    public int getBallPositionX()
    {
        return ballPositionX;
    }

    public int getBallPositionY()
    {
        return ballPositionY;
    }

    public int getBallMoveRateX()
    {
        return moveRateX;
    }

    public int getBallMoveRateY()
    {
        return moveRateY;
    }

    public void setBallPosition(int xPosition, int yPosition)
    {
        ballPositionX = xPosition;
        ballPositionY = yPosition;
    }

    public void setBallMoveRate(int xRate, int yRate)
    {
        moveRateX = xRate;
        moveRateY = yRate;
    }

    public void reverseBallMoveRateX()
    {
        moveRateX *= -1;
    }

    public void reverseBallMoveRateY()
    {
        moveRateY *= -1;
    }
}
