public abstract class SearchStrategy 
{
	protected int[][] dupGrid;
	protected Ship[] doSearch(int[][] grid) 
	{
		this.dupGrid=new int[grid.length][grid[0].length];
		for (int j=0;j<dupGrid.length;j++) 
		{
			for (int i=0;i<dupGrid[0].length;i++) 
				dupGrid[i][j]=0;
		}
		return null;
	};
	protected int numGridsChecked=0;
	public int getChecked()
	{
		return this.numGridsChecked;
	}	
	public abstract String getName();
	private boolean checkLeft(int x,int y,int[][] grid,int target) 
	{
		if (x>0) 
		{
			if (this.dupGrid[x-1][y]==0)
				this.numGridsChecked++;
			if (grid[x-1][y]==target) 
			{
				this.dupGrid[x-1][y]=target;
				return true;
			}
			else
				this.dupGrid[x-1][y]=-1;
		}	
		return false;
	}
	private boolean checkRight(int x,int y,int[][] grid,int target) 
	{
		if (x<24) 
		{
			if (this.dupGrid[x+1][y]==0) 
				this.numGridsChecked++;
			if (grid[x+1][y]==target) 
			{
				this.dupGrid[x+1][y]=target;
				return true;
			}
			else
				this.dupGrid[x+1][y]=-1;
		}
		return false;
	}
	private boolean checkUp(int x,int y,int[][] grid,int target) 
	{
		if (y>0) 
		{
			if (this.dupGrid[x][y-1]==0) 
				this.numGridsChecked++;
			if (grid[x][y-1]==target) 
			{
				this.dupGrid[x][y-1]=target;
				return true;
			}
			else 
				this.dupGrid[x][y-1]=-1;
		}
		return false;
	}
	private boolean checkDown(int x,int y,int[][] grid,int target) 
	{
		if (y<24) 
		{
			if (this.dupGrid[x][y+1]==0) 
				this.numGridsChecked++;
			if (grid[x][y+1]==target) 
			{
				this.dupGrid[x][y+1]=target;
				return true;
			}
			else 
				this.dupGrid[x][y+1]=-1;
		}
		return false;
	}
	protected Ship propagate(int x,int y,int[][] grid,int target) 
	{
		int top=y,left=x,len = 0;
		if (checkLeft(x,y,grid,target) || checkRight(x,y,grid,target)) 
		{
			//check left
			while (checkLeft(left,y,grid,target)) {
				left--;
			}
			int trace=left;
			//check right
			while (checkRight(trace++,y,grid,target)) {
			}
			len=trace-left;
			return new Ship(left,top,len,Ship.Orientations.Horizontal);
		}
		else if (checkUp(x,y,grid,target) || checkDown(x,y,grid,target))
		{
			//check up
			while (checkUp(x,top,grid,target)) {
				top--;
			}
			//check down
			int trace=top;
			while (checkDown(x,trace++,grid,target));
			len=(trace-top);
			return new Ship(left,top,len,Ship.Orientations.Vertical);
		}
		return null;
	};
}