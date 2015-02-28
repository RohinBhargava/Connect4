import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Stack;
import javax.swing.JComponent;

public class Component extends JComponent
{
    private static final long serialVersionUID = -5577063034531758539L;
    private Maze k;
    private int cellSize;
    private int xInt;
    private int yInt;
    private ArrayList<tile> tracker;
    private boolean computer;
    private Solve solve;
    private Stack<tile> q;
    private boolean solveToggle;
    
    public Component(int width, int height, int size, int dim1, int dim2)
    {
        this.k = new Maze(width, height);
        this.tracker = new ArrayList();
        this.solve = new Solve(this.k);
        
        this.cellSize = size;
        this.xInt = ((dim1 - size * width) / 2);
        this.yInt = ((dim2 - size * height) / 2);
        this.computer = false;
        this.solveToggle = false;
        
        this.k.MazeGenerator();
        this.tracker.add(this.k.getMazeLayout()[0][0]);
        this.q = this.solve.solve(this.solve.getPath());
    }
    
    public void toggle()
    {
        if (this.solveToggle)
            this.solveToggle = false;
        else {
            this.solveToggle = true;
        }
        repaint();
    }
    
    public void changePlay()
    {
        if (this.computer)
            this.computer = false;
        else {
            this.computer = true;
        }
        repaint();
    }
    
    public void analyzeCell(tile adder, tile recent)
    {
        if (this.tracker.contains(adder))
        {
            this.tracker.remove(recent);
        }
        else {
            this.tracker.add(adder);
        }
        repaint();
    }
    
    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D)g;
        
        for (tile[] t : this.k.getMazeLayout()) {
            for (tile y : t)
            {
                g2.setColor(Color.WHITE);
                for (Integer h : y.getWalls())
                {
                    if (h.intValue() == 1)
                        g2.drawLine(this.xInt + this.cellSize * y.getX(), this.yInt + this.cellSize * y.getY(), this.xInt + this.cellSize * (y.getX() + 1), this.yInt + this.cellSize * y.getY());
                    else if (h.intValue() == 2)
                        g2.drawLine(this.xInt + this.cellSize * (y.getX() + 1), this.yInt + this.cellSize * y.getY(), this.xInt + this.cellSize * (y.getX() + 1), this.yInt + this.cellSize * (y.getY() + 1));
                    else if (h.intValue() == 3)
                        g2.drawLine(this.xInt + this.cellSize * y.getX(), this.yInt + this.cellSize * (y.getY() + 1), this.xInt + this.cellSize * (y.getX() + 1), this.yInt + this.cellSize * (y.getY() + 1));
                    else if (h.intValue() == 4)
                        g2.drawLine(this.xInt + this.cellSize * y.getX(), this.yInt + this.cellSize * y.getY(), this.xInt + this.cellSize * y.getX(), this.yInt + this.cellSize * (y.getY() + 1));
                }
            }
        }
        boolean winner = false;
        
        if (this.solveToggle)
        {
            for (tile t : this.q)
            {
                g2.setColor(Color.GRAY);
                g2.fillRect(this.xInt + 1 + this.cellSize * t.getX(), this.yInt + 1 + this.cellSize * t.getY(), this.cellSize - 1, this.cellSize - 1);
            }
        }
        
        for (tile t : this.tracker)
        {
            if ((t.getX() == this.k.getWidth() - 1) && (t.getY() == this.k.getHeight() - 1))
            {
                winner = true;
            }
            g2.setColor(Color.BLUE);
            g2.fillRect(this.xInt + 1 + this.cellSize * t.getX(), this.yInt + 1 + this.cellSize * t.getY(), this.cellSize - 1, this.cellSize - 1);
        }
        
        g2.setColor(Color.GREEN);
        g2.fillRect(this.xInt + 1, this.yInt + 1, this.cellSize - 1, this.cellSize - 1);
        g2.setColor(Color.RED);
        g2.fillRect(this.xInt + 1 + this.cellSize * (this.k.getWidth() - 1), this.yInt + 1 + this.cellSize * (this.k.getHeight() - 1), this.cellSize - 1, this.cellSize - 1);
        
        if (winner)
        {
            regenerate(true);
        }
    }
    
    public Maze getMaze()
    {
        return this.k;
    }
    
    public void newFrameDim(int width, int height)
    {
        this.xInt = ((width - this.cellSize * this.k.getWidth()) / 2);
        this.yInt = ((height - this.cellSize * this.k.getHeight()) / 2);
        repaint();
    }
    
    public void regenerate(boolean b)
    {
        this.solveToggle = false;
        
        int wid = this.k.getWidth();
        int hei = this.k.getHeight();
        
        if ((b) && (this.computer))
        {
            wid = this.k.getWidth() + 1;
            hei = this.k.getHeight() + 1;
            
            this.xInt -= this.cellSize / 2;
            this.yInt -= this.cellSize / 2;
        }
        
        this.k = new Maze(wid, hei);
        
        this.tracker = new ArrayList();
        this.tracker.add(this.k.getMazeLayout()[0][0]);
        
        this.k.MazeGenerator();
        
        this.solve = new Solve(this.k);
        this.q = this.solve.solve(this.solve.getPath());
        
        repaint();
    }
    
    public ArrayList<tile> getTrail()
    {
        return this.tracker;
    }
    
    public void erase() {
        this.tracker = new ArrayList();
        this.tracker.add(this.k.getMazeLayout()[0][0]);
        
        repaint();
    }
}