import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/***************************************************************************************
*
* NAME: Joshua Nishiguchi
*
* HOMEWORK: 6
*
* CLASS: ICS 211
*
* INSTRUCTOR: Scott Robertson
*
* DATE: March 9, 2016
*
* FILE: MazeGenerator.java
*
* DESCRIPTION: This file contains all the methods required to generate a maze with a stack
*
***************************************************************************************/
public class MazeGenerator {

	private int comp, maxPath, currPath = 0;
	private Grid g;
	private DIR lastDir;
	private Stack<Cell> cellStack;

	/********************************************************************
	 *
	 * Method: MazeGenerator
	 *
	 * Description: Grabs the complexity and maximum paths before assuming an End, and then sets the defaults for the grids.
	 *
	 * @param int complexity - Size
	 * @param int maxPath - Max path size
	 *
	 * @return None
	 *
	 ********************************************************************/
	public MazeGenerator(int complexity, int maxPath)
	{
		this.comp = complexity;
		this.maxPath = maxPath;
		this.g = new Grid(comp, comp);
		this.cellStack = new Stack<Cell>();
		for(int x = 0; x<comp; x++)
		{
			for(int y = 0; y<comp; y++)
			{
				g.getCell(x, y).setData("W");
			}
		}
		g.getCell(0, 0).setData("S");
		g.getCell(0, 0).setVisited(true);
		cellStack.push(g.getCell(0, 0));
	}

	/********************************************************************
	 *
	 * Method: generateMaze
	 *
	 * Description: Constructs the maze using the following condition:
	 * Conditions: The next cell to be visited MUST NOT be visited. In addition, the next cell's neighbors must not be visited either, with exempt
	 * to the prior cell (which shouldn't be worried about since it's technically the "next next")
	 *
	 * @param None
	 *
	 * @return None
	 *
	 ********************************************************************/
	public void generateMaze()
	{
		Random rnd = new Random();
		DIR dir;
		builder: while(maxPath > currPath)
		{
			do
			{
				dir = DIR.values()[rnd.nextInt(DIR.values().length)];
			}
			while(dir.equals(lastDir));
			lastDir = dir;
			int rndAm = rnd.nextInt(comp/2);
			for(int move = 0; move<rndAm; move++)
			{
				if(dir.equals(DIR.UP))
				{
					if(cellStack.peek().getNorth() != null)
					{
						Cell c = cellStack.peek().getNorth();
						boolean canMove = false;
						if(c.getWest() != null && !canMove)
						{
							canMove = c.getWest().getVisited();
						}
						if(c.getNorth() != null && !canMove)
						{
							canMove = c.getNorth().getVisited();
						}
						if(c.getEast() != null && !canMove)
						{
							canMove = c.getEast().getVisited();
						}
						if(!canMove)
						{
							c.setData("*");
							c.setVisited(true);
							cellStack.push(c);
						}
						else
						{
							continue builder;
						}
					}
					else
					{
						continue builder;
					}
				}
				else if(dir.equals(DIR.DOWN))
				{
					if(cellStack.peek().getSouth() != null)
					{
						Cell c = cellStack.peek().getSouth();
						boolean canMove = false;
						if(c.getWest() != null && !canMove)
						{
							canMove = c.getWest().getVisited();
						}
						if(c.getSouth() != null && !canMove)
						{
							canMove = c.getSouth().getVisited();
						}
						if(c.getEast() != null && !canMove)
						{
							canMove = c.getEast().getVisited();
						}
						if(!canMove)
						{
							c.setData("*");
							c.setVisited(true);
							cellStack.push(c);
						}
						else
						{
							continue builder;
						}
					}
					else
					{
						continue builder;
					}
				}
				else if(dir.equals(DIR.LEFT))
				{
					if(cellStack.peek().getWest() != null)
					{
						Cell c = cellStack.peek().getWest();
						boolean canMove = false;
						if(c.getWest() != null && !canMove)
						{
							canMove = c.getWest().getVisited();
						}
						if(c.getNorth() != null && !canMove)
						{
							canMove = c.getNorth().getVisited();
						}
						if(c.getSouth() != null && !canMove)
						{
							canMove = c.getSouth().getVisited();
						}
						if(!canMove)
						{
							c.setData("*");
							c.setVisited(true);
							cellStack.push(c);
						}
						else
						{
							continue builder;
						}
					}
					else
					{
						continue builder;
					}
				}
				else if(dir.equals(DIR.RIGHT))
				{
					if(cellStack.peek().getEast() != null)
					{
						Cell c = cellStack.peek().getEast();
						boolean canMove = false;
						if(c.getSouth() != null && !canMove)
						{
							canMove = c.getSouth().getVisited();
						}
						if(c.getNorth() != null && !canMove)
						{
							canMove = c.getNorth().getVisited();
						}
						if(c.getEast() != null && !canMove)
						{
							canMove = c.getEast().getVisited();
						}
						if(!canMove)
						{
							c.setData("*");
							c.setVisited(true);
							cellStack.push(c);
						}
						else
						{
							continue builder;
						}
					}
					else
					{
						continue builder;
					}
				}
			}
			currPath++;
		}
		cellStack.peek().setData("E");
		//Completed maze without any split paths
		//Getting every 5th item in the stack
		Stack<Cell> obstacle = new Stack<Cell>();
		Stack<Cell> points = new Stack<Cell>();
		Stack<Cell> temp_cellStack = new Stack<Cell>();
		int size = 0;
		while(!cellStack.empty())
		{
			size++;
			temp_cellStack.push(cellStack.pop());
		}
		for(int i = 1; i<size; i++)
		{
			cellStack.push(temp_cellStack.pop());
			if(i%3 == 0)
			{
				points.push(cellStack.peek());
			}
		}
		while(!points.empty())
		{
			obstacle.push(points.pop());
			lastDir = null;
			currPath = 0;
			builder: while(!obstacle.empty() && currPath < maxPath) //Builds off current top
			{
				do
				{
					dir = DIR.values()[rnd.nextInt(DIR.values().length)];
				}
				while(dir.equals(lastDir));
				lastDir = dir;
				int rndAm = rnd.nextInt(comp/2);

				for(int move = 0; move<rndAm; move++)
				{
					if(dir.equals(DIR.UP))
					{
						if(obstacle.peek().getNorth() != null)
						{
							Cell c = obstacle.peek().getNorth();
							boolean canMove = false;
							if(c.getWest() != null && !canMove)
							{
								canMove = c.getWest().getVisited();
							}
							if(c.getNorth() != null && !canMove)
							{
								canMove = c.getNorth().getVisited();
							}
							if(c.getEast() != null && !canMove)
							{
								canMove = c.getEast().getVisited();
							}
							if(!canMove)
							{
								c.setData("*");
								c.setVisited(true);
								obstacle.push(c);
							}
							else
							{
								continue builder;
							}
						}
						else
						{
							continue builder;
						}
					}
					else if(dir.equals(DIR.DOWN))
					{
						if(obstacle.peek().getSouth() != null)
						{
							Cell c = obstacle.peek().getSouth();
							boolean canMove = false;
							if(c.getWest() != null && !canMove)
							{
								canMove = c.getWest().getVisited();
							}
							if(c.getSouth() != null && !canMove)
							{
								canMove = c.getSouth().getVisited();
							}
							if(c.getEast() != null && !canMove)
							{
								canMove = c.getEast().getVisited();
							}
							if(!canMove)
							{
								c.setData("*");
								c.setVisited(true);
								obstacle.push(c);
							}
							else
							{
								continue builder;
							}
						}
						else
						{
							continue builder;
						}
					}
					else if(dir.equals(DIR.LEFT))
					{
						if(obstacle.peek().getWest() != null)
						{
							Cell c = obstacle.peek().getWest();
							boolean canMove = false;
							if(c.getWest() != null && !canMove)
							{
								canMove = c.getWest().getVisited();
							}
							if(c.getNorth() != null && !canMove)
							{
								canMove = c.getNorth().getVisited();
							}
							if(c.getSouth() != null && !canMove)
							{
								canMove = c.getSouth().getVisited();
							}
							if(!canMove)
							{
								c.setData("*");
								c.setVisited(true);
								obstacle.push(c);
							}
							else
							{
								continue builder;
							}
						}
						else
						{
							continue builder;
						}
					}
					else if(dir.equals(DIR.RIGHT))
					{
						if(obstacle.peek().getEast() != null)
						{
							Cell c = obstacle.peek().getEast();
							boolean canMove = false;
							if(c.getSouth() != null && !canMove)
							{
								canMove = c.getSouth().getVisited();
							}
							if(c.getNorth() != null && !canMove)
							{
								canMove = c.getNorth().getVisited();
							}
							if(c.getEast() != null && !canMove)
							{
								canMove = c.getEast().getVisited();
							}
							if(!canMove)
							{
								c.setData("*");
								c.setVisited(true);
								obstacle.push(c);
							}
							else
							{
								continue builder;
							}
						}
						else
						{
							continue builder;
						}
					}
				}
				currPath++;
			}
		}
	}

	/********************************************************************
	*
	* Method: getComplexity
	*
	* Description: Returns the complexity
	*
	* @param None
	*
	* @return int complexity
	*
	********************************************************************/
	public int getComplexity()
	{
		return this.comp;
	}
	
	/********************************************************************
	*
	* Method: getMaxPath
	*
	* Description: Returns the maxPath size
	*
	* @param None
	*
	* @return int maxPath
	*
	********************************************************************/
	public int getMaxPath()
	{
		return this.maxPath;
	}
	
	/********************************************************************
	*
	* Method: toString
	*
	* Description: Prints the grid to string
	*
	* @param None
	*
	* @return None
	*
	********************************************************************/
	public String toString()
	{
		String str = "";
		for(int x = 0; x<comp; x++)
		{
			for(int y = 0; y<comp; y++)
			{
				str += g.getCell(x, y).getData() + " ";
			}
			str += "\n";
		}
		return str;
	}

	/********************************************************************
	*
	* Method: saveToFile
	*
	* Description: Saves the generated maze to a file
	*
	* @param File f - File to save to
	*
	* @return None
	*
	* @throws IOException
	********************************************************************/
	public void saveToFile(File f) throws IOException
	{
		if(f.canWrite())
		{
			BufferedWriter bw = new BufferedWriter(new FileWriter(f));
			String str = "";
			for(int x = 0; x<comp; x++)
			{
				for(int y = 0; y<comp; y++)
				{
					str += g.getCell(x, y).getData() + " ";
				}
				bw.write(str);
				bw.newLine();
				str = "";
			}
			bw.close();
		}
	}
	
	/**
	 * 
	 * @author Josh
	 *
	 */
	private enum DIR
	{
		LEFT, RIGHT, UP, DOWN;
	}
}
