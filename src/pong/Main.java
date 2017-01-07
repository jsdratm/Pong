/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pong;

import javax.swing.JFrame;
import java.awt.event.*;
import java.util.Random;


class SimpleThread extends Thread {
    private Ball ballObject;
    private Paddle paddleAI;
    private Paddle paddleUser;
    private int frameWidth;
    private int frameHeight;
    private int paddleShift;
    private int paddlePadding;
    private int userScore;
    private int aiScore;
    private GraphicsEngine graphicsEngine;
    private int reactionCounter;
    private Random randomGenerator;

    public SimpleThread(Ball inputBall, 
                        Paddle inputPaddleUser,
                        Paddle inputPaddleAI,
                        int frameWidthIn,
                        int frameHeightIn,
                        int paddleShiftIn,
                        int paddlePaddingIn,
                        int userScoreIn,
                        int aiScoreIn,
                        GraphicsEngine graphicsEngineIn)
    {
        ballObject = inputBall;
        paddleAI = inputPaddleAI;
        paddleUser = inputPaddleUser;
        frameWidth = frameWidthIn;
        frameHeight = frameHeightIn;
        paddleShift = paddleShiftIn;
        paddlePadding = paddlePaddingIn;
        userScore = userScoreIn;
        aiScore = aiScoreIn;
        graphicsEngine = graphicsEngineIn;
        reactionCounter = -1;
        randomGenerator = new Random();
    }
    public void run()
    {
	while (true)
        {
            int paddleShiftX = 0;

            // Do AI processing (move AI paddle to catch ball)
            // Check if paddle needs to move left
            if (reactionCounter == -1)
            {
                if ((paddleAI.getPaddlePositionX() + (paddleAI.getPaddleWidth() / 2)) > ballObject.getBallPositionX())
                {
                    paddleShiftX = (paddleAI.getPaddlePositionX() + (paddleAI.getPaddleWidth() / 2)) - ballObject.getBallPositionX();

                    if (paddleShiftX > paddleShift)
                    {
                        paddleShiftX = paddleShift;
                    }

                    if (paddleAI.getPaddlePositionX() - paddleShiftX < 0)
                    {
                        paddleShiftX = paddleAI.getPaddlePositionX() - 0;
                    }

                    paddleShiftX *= -1;
                }
                // Otherwise check if paddle needs to move right
                else if ((paddleAI.getPaddlePositionX() + (paddleAI.getPaddleWidth() / 2)) < ballObject.getBallPositionX())
                {
                    paddleShiftX = ballObject.getBallPositionX() - (paddleAI.getPaddlePositionX() + (paddleAI.getPaddleWidth() / 2));
                    if (paddleShiftX > paddleShift)
                    {
                        paddleShiftX = paddleShift;
                    }

                    if (frameWidth - (paddleAI.getPaddlePositionX() + paddleShiftX + paddleAI.getPaddleWidth()) < 0)
                    {
                        paddleShiftX = frameWidth - (paddleAI.getPaddlePositionX() + paddleAI.getPaddleWidth());
                    }
                }

                paddleAI.setPaddlePosition(paddleAI.getPaddlePositionX() + paddleShiftX,
                                           paddleAI.getPaddlePositionY());
            }
            else
            {
                reactionCounter--;
            }

            // Move the ball
            ballObject.setBallPosition(ballObject.getBallPositionX() + ballObject.getBallMoveRateX(),
                                       ballObject.getBallPositionY() + ballObject.getBallMoveRateY());

            // If ball has gotten past AI's paddle
            if (ballObject.getBallPositionY() < paddlePadding)
            {
                // Increment score for user
                userScore++;
                graphicsEngine.updateScores(userScore, aiScore);

                // Reset ball position
                ballObject.setBallPosition(frameWidth / 2, frameHeight / 2);
                ballObject.decelerateBall();
                ballObject.reverseBallMoveRateY();
            }
            // If ball has gotten past user's paddle
            else if (ballObject.getBallPositionY() > (frameHeight - paddlePadding))
            {
                // Increment score for AI
                aiScore++;
                graphicsEngine.updateScores(userScore, aiScore);

                // Reset ball position
                ballObject.setBallPosition(frameWidth / 2, frameHeight / 2);
                ballObject.decelerateBall();
                ballObject.reverseBallMoveRateY();
            }

            // Check if ball needs to reflect off of a paddle
            if (ballObject.getBallMoveRateY() < 0 &&
                ballObject.getBallPositionY() <= (paddleAI.getPaddlePositionY() + paddleAI.getPaddleHeight()) &&
                (ballObject.getBallPositionX() + ballObject.getBallDiameter()) > paddleAI.getPaddlePositionX() &&
                ballObject.getBallPositionX() < (paddleAI.getPaddlePositionX() + paddleAI.getPaddleWidth()))
            {
                ballObject.reverseBallMoveRateY();
                reactionCounter = randomGenerator.nextInt(40);

                if (ballObject.getBallPositionX() < (paddleAI.getPaddlePositionX() + (paddleAI.getPaddleWidth() * .25)) ||
                    ballObject.getBallPositionX() > (paddleAI.getPaddlePositionX() + paddleAI.getPaddleWidth() - (paddleAI.getPaddleWidth() * .25)))
                {
                    ballObject.accelerateBall();
                }
                else
                {
                    ballObject.decelerateBall();
                }
            }
            else if (ballObject.getBallMoveRateY() > 0 &&
                     (ballObject.getBallPositionY() + ballObject.getBallDiameter()) >= (paddleUser.getPaddlePositionY()) &&
                     (ballObject.getBallPositionX() + ballObject.getBallDiameter()) > paddleUser.getPaddlePositionX() &&
                     ballObject.getBallPositionX() < (paddleUser.getPaddlePositionX() + paddleUser.getPaddleWidth()))
            {
                ballObject.reverseBallMoveRateY();
                reactionCounter = randomGenerator.nextInt(40);

                if (ballObject.getBallPositionX() < (paddleUser.getPaddlePositionX() + (paddleUser.getPaddleWidth() * .25)) ||
                    ballObject.getBallPositionX() > (paddleUser.getPaddlePositionX() + paddleUser.getPaddleWidth() - (paddleUser.getPaddleWidth() * .25)))
                {
                    ballObject.accelerateBall();
                }
                else
                {
                    ballObject.decelerateBall();
                }
            }

            // Check if ball needs to reflect off of a side wall
            if (ballObject.getBallMoveRateX() > 0 &&
                (ballObject.getBallPositionX() + (ballObject.getBallDiameter() * 1.25)) > frameWidth)
            {
                ballObject.reverseBallMoveRateX();
                reactionCounter = randomGenerator.nextInt(35);
            }
            else if (ballObject.getBallMoveRateX() < 0 &&
                     ballObject.getBallPositionX() < (ballObject.getBallDiameter() * .25))
            {
                ballObject.reverseBallMoveRateX();
                reactionCounter = randomGenerator.nextInt(35);
            }

            // Check watchdog counter to see if main thread has terminated
            Global.watchdogCounter++;
            if (Global.watchdogCounter > 50)
            {
                break;
            }

            // Wait 100ms
            try
            {
		sleep(25);
	    }
            catch (InterruptedException e)
            {
                // Do nothing
            }
	}
    }
}

/**
 *
 * @author jsdratm
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        int paddleWidth = 50;
        int paddleHeight = 10;
        int ballDiameter = 10;
        int frameHeight = 700;
        final int frameWidth = 500;
        int paddlePadding = 25;
        final int paddleShiftXPerKey = 15;
        final int paddleShiftXAI = 3;
        int userScore = 0;
        int aiScore = 0;
        int watchdogCounter = 0;

        Ball ballObject = new Ball(frameWidth / 2,
                                   frameHeight / 2,
                                   ballDiameter);
        
        ballObject.setBallMoveRate(3, 3);

        final Paddle paddleUser = new Paddle(((frameWidth / 2) - (paddleWidth / 2)),
                                       frameHeight - ((paddlePadding * 2) + paddleHeight),
                                       paddleWidth,
                                       paddleHeight);
        
        Paddle paddleAI = new Paddle(((frameWidth / 2) - (paddleWidth / 2)),
                                       paddlePadding,
                                       paddleWidth,
                                       paddleHeight);

        JFrame mainFrame = new JFrame("Pong by Dunderware");
        mainFrame.setResizable(false);
        GraphicsEngine graphicsEngine = new GraphicsEngine();
        graphicsEngine.setReferences(ballObject, paddleUser, paddleAI, paddlePadding);

        new SimpleThread(ballObject,
                         paddleUser,
                         paddleAI,
                         frameWidth,
                         frameHeight,
                         paddleShiftXAI,
                         paddlePadding,
                         userScore,
                         aiScore,
                         graphicsEngine).start();

        mainFrame.addKeyListener(new KeyAdapter()
        {
            public void keyPressed(KeyEvent e)
            {
                int keyCode = e.getKeyCode();

                // left arrow key
                if (keyCode == KeyEvent.VK_LEFT)
                {
                    int paddleShiftAmount = 0;
                    if (paddleUser.getPaddlePositionX() > 0)
                    {
                        paddleShiftAmount = paddleUser.getPaddlePositionX();
                    }

                    if (paddleShiftAmount > paddleShiftXPerKey)
                    {
                        paddleShiftAmount = paddleShiftXPerKey;
                    }

                    paddleUser.setPaddlePosition(paddleUser.getPaddlePositionX() - paddleShiftAmount,
                                                 paddleUser.getPaddlePositionY());
                }
                // right arrow key
                else if (keyCode == KeyEvent.VK_RIGHT)
                {
                    int paddleShiftAmount = 0;
                    if (frameWidth - (paddleUser.getPaddlePositionX() + paddleUser.getPaddleWidth()) > 0)
                    {
                        paddleShiftAmount = frameWidth - (paddleUser.getPaddlePositionX() + paddleUser.getPaddleWidth());
                    }

                    if (paddleShiftAmount > paddleShiftXPerKey)
                    {
                        paddleShiftAmount = paddleShiftXPerKey;
                    }

                    paddleUser.setPaddlePosition(paddleUser.getPaddlePositionX() + paddleShiftAmount,
                                                 paddleUser.getPaddlePositionY());
                }
            }
        });

        mainFrame.getContentPane().add(graphicsEngine);

        mainFrame.setSize(frameWidth,
                          frameHeight);
        
        mainFrame.setVisible(true);

        while (true)
        {
            mainFrame.repaint();
            Global.watchdogCounter = 0;
            try {
		Thread.currentThread().sleep(50);
	    }
            catch (InterruptedException e)
            {

            }
        }

    }

}
