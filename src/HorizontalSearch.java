public class HorizontalSearch extends SearchStrategy
{
	@Override
	public Ship[] doSearch(int[][] grid) 
	{
		super.doSearch(grid);
		Ship[] returnVal=new Ship[2];
		int index=0;
		int[] targetVal={0,0};
		for (int j=0;j<grid.length;j++) 
		{
			for (int i=0;i<grid[0].length;i++) 
			{
				numGridsChecked++;
				if (grid[i][j]!=0 && targetVal[grid[i][j]-1]==0) 
				{
					targetVal[grid[i][j]-1]++;
					returnVal[index++]=this.propagate(i,j,grid,grid[i][j]);
					if (1==targetVal[0] && 1==targetVal[1]) {
						return returnVal;
					}
				}
			}
		}
		return returnVal;
	}
	@Override
	public String getName() {
		return "Horizontal Sweep";
	}
}