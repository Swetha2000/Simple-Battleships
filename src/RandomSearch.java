import java.util.Random;

public class RandomSearch extends SearchStrategy 
{
	@Override
	public Ship[] doSearch(int[][] grid) 
	{
		super.doSearch(grid);
		Ship[] returnVal=new Ship[2];
		Random rand=new Random();
		returnVal[0]=null;
		returnVal[1]=null;
		int[] targetVal={0,0};
		int index=0;
		while (null==returnVal[0] || null==returnVal[1]) 
		{
			int x=rand.nextInt(25);
			int y=rand.nextInt(25);
			this.numGridsChecked++;
			if (grid[x][y] != 0 && targetVal[grid[x][y]-1] == 0) 
			{
				targetVal[grid[x][y]-1]++;
				returnVal[index++] = this.propagate(x, y, grid, grid[x][y]);
			}
		}
		return returnVal;
	}
	@Override
	public String getName() {
		return "Random Search";
	}
}