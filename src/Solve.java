import java.util.ArrayList;
import java.util.Stack;

public class Solve
{
    private Maze m;
    private Stack<tile> container;
    private int[][] visited;
    
    Solve(Maze maze)
    {
        this.m = maze;
        this.container = new Stack();
        tile init = maze.getMazeLayout()[0][0];
        this.container.push(init);
        this.visited = new int[maze.getWidth()][maze.getHeight()];
        for (int i = 0; i < maze.getWidth(); i++)
            for (int j = 0; j < maze.getHeight(); j++)
                this.visited[i][j] = 0;
        this.visited[0][0] += 1;
    }
    
    public ArrayList<tile> checkAdjacentLocations(tile t)
    {
        ArrayList returner = new ArrayList();
        
        if ((t.getX() > 0) && (!this.m.getMazeLayout()[(t.getX() - 1)][t.getY()].getWalls().contains(Integer.valueOf(2))) && (this.visited[(t.getX() - 1)][t.getY()] < 1))
            returner.add(this.m.getMazeLayout()[(t.getX() - 1)][t.getY()]);
        if ((t.getX() < this.m.getWidth() - 1) && (!this.m.getMazeLayout()[(t.getX() + 1)][t.getY()].getWalls().contains(Integer.valueOf(4))) && (this.visited[(t.getX() + 1)][t.getY()] < 1))
            returner.add(this.m.getMazeLayout()[(t.getX() + 1)][t.getY()]);
        if ((t.getY() > 0) && (!this.m.getMazeLayout()[t.getX()][(t.getY() - 1)].getWalls().contains(Integer.valueOf(3))) && (this.visited[t.getX()][(t.getY() - 1)] < 1))
            returner.add(this.m.getMazeLayout()[t.getX()][(t.getY() - 1)]);
        if ((t.getY() < this.m.getHeight() - 1) && (!this.m.getMazeLayout()[t.getX()][(t.getY() + 1)].getWalls().contains(Integer.valueOf(1))) && (this.visited[t.getX()][(t.getY() + 1)] < 1)) {
            returner.add(this.m.getMazeLayout()[t.getX()][(t.getY() + 1)]);
        }
        return returner;
    }
    
    public Stack<tile> getPath()
    {
        return this.container;
    }
    
    public Stack<tile> solve(Stack<tile> stack)
    {
        tile temp = (tile)stack.pop();
        
        if (temp == this.m.getMazeLayout()[(this.m.getWidth() - 1)][(this.m.getHeight() - 1)]) {
            return stack;
        }
        ArrayList adjLocs = checkAdjacentLocations(temp);
        
        if (adjLocs.size() > 0)
        {
            stack.push(temp);
            int choose = (int)Math.random() * adjLocs.size();
            tile insert = (tile)adjLocs.get(choose);
            stack.push(insert);
            this.visited[insert.getX()][insert.getY()] += 1;
        }
        
        return solve(stack);
    }
}