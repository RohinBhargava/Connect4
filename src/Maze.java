import java.util.ArrayList;
import java.util.Stack;

public class Maze
{
    private int width;
    private int height;
    private tile[][] tiles;
    private Stack<tile> x;
    
    public Maze(int w, int h)
    {
        this.width = w;
        this.height = h;
        this.tiles = new tile[w][h];
        for (int u = 0; u < this.width; u++)
            for (int o = 0; o < this.height; o++)
                this.tiles[u][o] = new tile(u, o);
        this.x = new Stack();
        tile init = this.tiles[(w - 1)][(h - 1)];
        init.changeState();
        this.x.push(init);
    }
    
    public tile[][] getMazeLayout()
    {
        return this.tiles;
    }
    
    public ArrayList<tile> generateList(tile t)
    {
        int exception = 0;
        int exception2 = 0;
        
        int q = t.getX();
        int r = t.getY();
        
        if ((q == 0) && (r == 0))
        {
            exception = 1;
            exception2 = 4;
        }
        else if ((q == 0) && (r == this.height - 1))
        {
            exception = 3;
            exception2 = 4;
        }
        else if ((q == this.width - 1) && (r == 0))
        {
            exception = 1;
            exception2 = 2;
        }
        else if ((q == this.width - 1) && (r == this.height - 1))
        {
            exception = 2;
            exception2 = 3;
        }
        else if (q == 0)
        {
            exception = 4;
        }
        else if (q == this.width - 1)
        {
            exception = 2;
        }
        else if (r == 0)
        {
            exception = 1;
        }
        else if (r == this.height - 1)
        {
            exception = 3;
        }
        
        ArrayList returner = new ArrayList();
        
        for (int i = 0; i < 4; i++)
        {
            if ((i + 1 != exception) && (i + 1 != exception2))
            {
                if ((i + 1 == 1) && (this.tiles[q][(r - 1)].getState()))
                    returner.add(this.tiles[q][(r - 1)]);
                if ((i + 1 == 2) && (this.tiles[(q + 1)][r].getState()))
                    returner.add(this.tiles[(q + 1)][r]);
                if ((i + 1 == 3) && (this.tiles[q][(r + 1)].getState()))
                    returner.add(this.tiles[q][(r + 1)]);
                if ((i + 1 == 4) && (this.tiles[(q - 1)][r].getState())) {
                    returner.add(this.tiles[(q - 1)][r]);
                }
            }
        }
        
        return returner;
    }
    
    public void MazeGenerator()
    {
        if (this.x.size() == 0) {
            return;
        }
        tile temp = (tile)this.x.pop();
        
        ArrayList list = generateList(temp);
        
        if (list.size() != 0)
        {
            this.x.push(temp);
            int choose = (int)(Math.random() * list.size());
            tile question = (tile)list.get(choose);
            removeWall(temp, question);
            question.changeState();
            this.x.push(question);
        }
        
        MazeGenerator();
    }
    
    public static void removeWall(tile cell, tile cell2)
    {
        if (cell2.getX() - cell.getX() == 1)
        {
            for (int i = 0; i < cell.getWalls().size(); i++)
                if (((Integer)cell.getWalls().get(i)).intValue() == 2)
                    cell.getWalls().remove(i);
            cell2.getWalls().remove(3);
        }
        else if (cell2.getX() - cell.getX() == -1)
        {
            for (int i = 0; i < cell.getWalls().size(); i++)
                if (((Integer)cell.getWalls().get(i)).intValue() == 4)
                    cell.getWalls().remove(i);
            cell2.getWalls().remove(1);
        }
        
        if (cell2.getY() - cell.getY() == 1)
        {
            for (int i = 0; i < cell.getWalls().size(); i++)
                if (((Integer)cell.getWalls().get(i)).intValue() == 3)
                    cell.getWalls().remove(i);
            cell2.getWalls().remove(0);
        }
        else if (cell2.getY() - cell.getY() == -1)
        {
            for (int i = 0; i < cell.getWalls().size(); i++)
                if (((Integer)cell.getWalls().get(i)).intValue() == 1)
                    cell.getWalls().remove(i);
            cell2.getWalls().remove(2);
        }
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public int getWidth() {
        return this.width;
    }
}