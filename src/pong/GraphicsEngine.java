/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pong;

/**
 *
 * @author jsdratm
 */
import java.awt.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class GraphicsEngine extends JComponent {

  private Ball ballObject;
  private Paddle paddleObjectUser;
  private Paddle paddleObjectAI;
  private int pointsUser;
  private int pointsAI;
  private int paddlePadding;

  private static Color m_tRed = new Color(255, 0, 0, 150);

  private static Color m_tGreen = new Color(0, 255, 0, 50);

  private static Color m_tBlue = new Color(0, 0, 255, 150);

  private static Font monoFont = new Font("Monospaced", Font.BOLD
      | Font.ITALIC, 36);

  private static Font sanSerifFont = new Font("SanSerif", Font.PLAIN, 12);

  private static Font serifFont = new Font("Serif", Font.BOLD, 24);

  private static ImageIcon java2sLogo = new ImageIcon("java2s.gif");

  public void setReferences(Ball inputBall, Paddle userPaddle, Paddle aiPaddle, int paddlePaddingIn)
  {
      ballObject = inputBall;
      paddleObjectUser = userPaddle;
      paddleObjectAI = aiPaddle;
      paddlePadding = paddlePaddingIn;
  }

  public void updateScores(int userPoints, int aiPoints)
  {
      pointsUser = userPoints;
      pointsAI = aiPoints;
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    // draw entire component white
    g.setColor(Color.black);
    g.fillRect(0, 0, getWidth(), getHeight());

    g.setColor(Color.green);
    g.fillRect(paddleObjectUser.getPaddlePositionX(),
               paddleObjectUser.getPaddlePositionY(),
               paddleObjectUser.getPaddleWidth(),
               paddleObjectUser.getPaddleHeight());

    g.fillRect(paddleObjectAI.getPaddlePositionX(),
               paddleObjectAI.getPaddlePositionY(),
               paddleObjectAI.getPaddleWidth(),
               paddleObjectAI.getPaddleHeight());

    g.setColor(Color.cyan);
    g.fillOval(ballObject.getBallPositionX(),
               ballObject.getBallPositionY(),
               ballObject.getBallDiameter(),
               ballObject.getBallDiameter());

    g.setColor(m_tGreen);

    g.setFont(monoFont);
    FontMetrics fm = g.getFontMetrics();
    int w = fm.stringWidth(Integer.toString(pointsUser));
    int h = fm.getAscent();
    g.drawString(Integer.toString(pointsUser), 5, (getHeight() / 2) + 50 + (h / 4));
    w = fm.stringWidth(Integer.toString(pointsAI));
    h = fm.getAscent();
    g.drawString(Integer.toString(pointsAI), 5,(getHeight() / 2) - 50 + (h / 4));

    //g.fillRect(60, 220, 120, 120);

    /*
    for (int i = 0; i < gridReference.getNumberOfRows(); i++)
    {
        for (int j = 0; j <  gridReference.getNumberOfColumns(); j++)
        {
            if (gridReference.isBoxFilled(i, j))
            {
                g.setColor(Color.black);
                g.fillRect((j * boxSize), (i * boxSize), boxSize, boxSize);
                g.setColor(Color.red);
                g.fillRect(((j * boxSize) + boxOutlineSize),
                           ((i * boxSize) + boxOutlineSize),
                           (boxSize - (boxOutlineSize * 2)),
                           (boxSize - (boxOutlineSize * 2)));
            }
        }
    }*/

    /*
    // yellow circle
    g.setColor(Color.yellow);
    g.fillOval(0, 0, 240, 240);
    // magenta circle
    g.setColor(Color.magenta);
    g.fillOval(160, 160, 240, 240);

    // paint the icon below blue sqaure
    int w = java2sLogo.getIconWidth();
    int h = java2sLogo.getIconHeight();
    java2sLogo.paintIcon(this, g, 280 - (w / 2), 120 - (h / 2));

    // paint the icon below red sqaure
    java2sLogo.paintIcon(this, g, 120 - (w / 2), 280 - (h / 2));

    // transparent red square
    g.setColor(m_tRed);
    g.fillRect(60, 220, 120, 120);

    // transparent green circle
    g.setColor(m_tGreen);
    g.fillOval(140, 140, 120, 120);

    // transparent blue square
    g.setColor(m_tBlue);
    g.fillRect(220, 60, 120, 120);

    g.setColor(Color.black);

    g.setFont(monoFont);
    FontMetrics fm = g.getFontMetrics();
    w = fm.stringWidth("Java Source");
    h = fm.getAscent();
    g.drawString("Java Source", 120 - (w / 2), 120 + (h / 4));

    g.setFont(sanSerifFont);
    fm = g.getFontMetrics();
    w = fm.stringWidth("and");
    h = fm.getAscent();
    g.drawString("and", 200 - (w / 2), 200 + (h / 4));

    g.setFont(serifFont);
    fm = g.getFontMetrics();
    w = fm.stringWidth("Support.");
    h = fm.getAscent();
    g.drawString("Support.", 280 - (w / 2), 280 + (h / 4));
     */
  }
}