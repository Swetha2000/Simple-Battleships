import java.io.BufferedReader;
import java.io.FileReader;

public class BattleshipSearch
{
	private int[][] battleGridMatrix=new int[25][25];
	private SearchStrategy strategy=new HorizontalSearch();
	private Ship[] shipsFound=new Ship[2];
	public void setStrategy(SearchStrategy s)
	{
		this.strategy = s;
	}
	public SearchStrategy getStrategy() 
	{
		return this.strategy;
	}
	public void buildGrid(String input) 
	{
		String[] splitInput=input.split("[\\D|\\s]+");
		int prevCoordX=-1,prevCoordY=-1,shipNumber=0;
		for (int i=1;i<splitInput.length;i+=2)
		{
			int coord1=Integer.parseInt(splitInput[i]);
			int coord2=Integer.parseInt(splitInput[i+1]);
			if (coord1!=prevCoordX && coord2!=prevCoordY)
				shipNumber++;
			battleGridMatrix[coord1][coord2]=shipNumber;
			prevCoordX=coord1;
			prevCoordY=coord2;
		}
	}
	public void findShips() 
	{
		this.shipsFound = strategy.doSearch(this.battleGridMatrix);
	}
	public static void main(String[] args) 
	{
		try
		{
			BufferedReader inputFile=new BufferedReader(new FileReader("input.txt"));
			for (int i=1;i<4;i++) 
			{
				BattleshipSearch ship= new BattleshipSearch();
				ship.buildGrid(inputFile.readLine());
				System.out.printf("Game %d\n", i);
				
				ship.setStrategy(new HorizontalSearch());
				ship.findShips();
				System.out.printf("Strategy: %s \n", ship.getStrategy().getName());
				System.out.printf("Number of cells searched: %d\n", ship.getStrategy().getChecked());
				System.out.printf("%s %s\n", ship.shipsFound[0].getCoordsFormatted(), ship.shipsFound[1].getCoordsFormatted());
				
				ship.setStrategy(new RandomSearch());
				ship.findShips();
				System.out.printf("Strategy: %s \n", ship.getStrategy().getName());
				System.out.printf("Number of cells searched: %d\n", ship.getStrategy().getChecked());
				System.out.printf("%s %s\n", ship.shipsFound[0].getCoordsFormatted(), ship.shipsFound[1].getCoordsFormatted());

				ship.setStrategy(new StrategicSearch());
				ship.findShips();
				System.out.printf("Strategy: %s \n", ship.getStrategy().getName());
				System.out.printf("Number of cells searched: %d\n", ship.getStrategy().getChecked());
				System.out.printf("%s %s\n", ship.shipsFound[0].getCoordsFormatted(), ship.shipsFound[1].getCoordsFormatted());
				System.out.println();
			}
			inputFile.close();
		}
		catch(java.io.FileNotFoundException e) {
			System.out.println("Please place the input.txt file in the working directory.");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}