public class StrategicSearch extends SearchStrategy 
{
	@Override
	public Ship[] doSearch(int[][] grid) 
	{
		super.doSearch(grid);
		Ship[] returnVal=new Ship[2];
		int[] targetVal={0,0};
		int index=0;
		for (int i=0;i<25*25;i+=2 )
		{
			int x=i%25,y=i/25;
			numGridsChecked++;
			if (grid[x][y]!=0 && targetVal[grid[x][y]-1]==0) 
			{
				targetVal[grid[x][y]-1]++;
				returnVal[index++] = this.propagate(x,y,grid,grid[x][y]);
				if (1==targetVal[0] && 1==targetVal[1])
					return returnVal;
			}
		}
		return returnVal;
	}
	@Override
	public String getName() {
		return "Strategic Search";
	}
}