import java.util.ArrayList;

public class tile
{
    private boolean open;
    private int x;
    private int y;
    private ArrayList<Integer> walls;
    
    public tile(int xPos, int yPos)
    {
        this.open = true;
        this.walls = new ArrayList();
        for (int i = 0; i < 4; i++)
        {
            this.walls.add(Integer.valueOf(i + 1));
        }
        this.x = xPos;
        this.y = yPos;
    }
    
    public int getX()
    {
        return this.x;
    }
    
    public int getY()
    {
        return this.y;
    }
    
    public void changeState()
    {
        this.open = false;
    }
    
    public boolean getState()
    {
        return this.open;
    }
    
    public ArrayList<Integer> getWalls()
    {
        return this.walls;
    }
}