import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Viewer
{
    public static void main(String[] args)
    {
        int w = 0;
        int h = 0;
        int size = 0;
        
        int option = 0;
        
        option = JOptionPane.showOptionDialog(null, "Would you like to set parameters for a maze or have the computer generate them?", "Pertinent Question!", 0, 1, null, new String[] { "Me!", "Computer (Game)." }, null);
        
        if (option == 0)
        {
            w = Integer.parseInt(JOptionPane.showInputDialog(null, "How wide should the maze be (columns)? \nMin: 2, Max: 64."));
            while (w > 64)
            {
                JOptionPane.showMessageDialog(null, "Invalid width, please try again.");
                w = Integer.parseInt(JOptionPane.showInputDialog(null, "How wide should the maze be (columns)? \nMin: 2, Max: 64."));
            }
            
            h = Integer.parseInt(JOptionPane.showInputDialog(null, "How tall should the maze be (rows)? \nMin: 2, Max: 64."));
            while (h > 64)
            {
                JOptionPane.showMessageDialog(null, "Invalid height, please try again.");
                h = Integer.parseInt(JOptionPane.showInputDialog(null, "How tall should the maze be (rows)? \nMin: 2, Max: 64."));
            }
            
            size = Integer.parseInt(JOptionPane.showInputDialog(null, "How large do you want the maze (in pixel width)? \nMin: 5, Max: 35."));
            do
            {
                JOptionPane.showMessageDialog(null, "Invalid size, please try again.");
                size = Integer.parseInt(JOptionPane.showInputDialog(null, "How large do you want the maze (in pixel width)? \nMin: 5, Max: 35."));
                
                if (size <= 35) break;  } while (size < 5);
        }
        else if (option == 1)
        {
            w = 2;
            h = 2;
            size = 14;
        }
        
        JFrame frame = new JFrame();
        Component j = new Component(w, h, size, (int)frame.getSize().getWidth(), (int)frame.getSize().getHeight());
        
        if (option == 1) {
            j.changePlay();
        }
        frame.setTitle("Maze Game");
        frame.setSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width / 2, Toolkit.getDefaultToolkit().getScreenSize().height / 2));
        frame.add(j);
        frame.setDefaultCloseOperation(3);
        frame.addComponentListener(new Viewer.1(j, frame));
        
        frame.addKeyListener(new Viewer.2(j));
        
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setVisible(true);
    }
}