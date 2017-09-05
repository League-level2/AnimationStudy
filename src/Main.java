import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;

public class Main extends JComponent implements ActionListener, Runnable
{
    private ImageIcon[] images = new ImageIcon[8];
    private int widthOfScreen = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
    private int heightOfScreen = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
    private JFrame mainGameWindow = new JFrame("NewGame");//Makes window with title "NewGame"
    private Rectangle2D.Double floor = new Rectangle2D.Double(0, 0, 100, 100);
    private Timer paintTicker = new Timer(20, this); //Ticks every 20 milliseconds (50 times per second); calls on actionPerformed() when it ticks.
    private Timer animationTicker = new Timer(100, this);
    private int imageCounter = 0;
    private int iconX = 1000;

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Main());
    }

    public void run()
    {
        for (int i = 0; i < (images.length); i++)
        {
            images[i] = new ImageIcon("src/pic" + i + ".PNG");
        }
        mainGameWindow.setTitle("Animation Study");
        mainGameWindow.setSize(widthOfScreen, heightOfScreen);
        mainGameWindow.add(this);//Adds the paint method to the JFrame
        mainGameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainGameWindow.setVisible(true);
        paintTicker.start();
        animationTicker.start();
    }

    public void paint(Graphics g)
    {
        Graphics2D g2 = (Graphics2D)g;
        ImageIcon img1 = images[imageCounter];
        img1.paintIcon(this, g2, iconX, 0);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == paintTicker)
        {
            repaint();
        }
        if (e.getSource() == animationTicker)
        {
            imageCounter = (imageCounter + 1) % 8;
            iconX -= 20;
            repaint();
        }
    }
}
