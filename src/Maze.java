import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JOptionPane;


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
 * FILE: Maze.java
 *
 * DESCRIPTION: This file contains the methods used to traverse through the maze by stack and queue, as well as resetting the maze
 *
 ***************************************************************************************/
public class Maze{

	/**
	 * 
	 */
	private int numRows, numCols;
	private boolean found = false;
	Grid g, origG;
	

	/********************************************************************
	 *
	 * Method: Maze
	 *
	 * Description: Constructor to set the default values for the original grid, current grid, and the number of rows/columns
	 *
	 * @param int numRows - Number of rows
	 * @param int numCols - Number of columns
	 * @param String filename - Name of the file to read the data into
	 *
	 * @return None
	 *
	 ********************************************************************/
	public Maze(int numRows, int numCols, String filename)
	{
		this.numRows = numRows;
		this.numCols = numCols;
		this.g = new Grid(numRows, numCols);
		this.origG = new Grid(numRows, numCols);
		String[] eles = new String[numRows*numCols];
		try
		{
			Scanner scan = new Scanner(new File(filename));
			System.out.println(filename);
			int i = 0;
			for(int x = 0; x<numRows; x++)
			{
				for(int y = 0; y<numCols; y++)
				{
					String s = scan.next();
					g.getCell(x, y).setData(s);
					origG.getCell(x, y).setData(s);
					eles[i] = g.getCell(x, y).getData();
					i++;
				}
			}
			scan.close();
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
	}

	/********************************************************************
	 *
	 * Method: findExitUsingStack
	 *
	 * Description: Traverses through the maze using a Stack. If there is no start, print “This maze has no start!”
	 If an exit cannot be found then print, “No exit was found.” Otherwise, as soon as an exit has
	 been found, stop traversing through the maze, and print, “You have found the exit!” Each
	 time a cell is visited, print the maze.
	 *
	 * @param None
	 *
	 * @return None
	 *
	 ********************************************************************/
	public void findExitUsingStack()
	{
		Stack<Cell> stack = new Stack<Cell>();
		boolean start = false, end = false;
		for(int x = 0; x<numRows; x++)
		{
			for(int y = 0; y<numCols; y++)
			{
				System.out.println((g.getCell(x, y).getData()));
				if(!start &&  (g.getCell(x, y).getData().equalsIgnoreCase("S")))
				{
					start = true;
					stack.push(g.getCell(x, y));
				}
			}
		}
		if(start)
		{
			while(!stack.empty())
			{
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				boolean passed = false;
				if(stack.peek().getData().equalsIgnoreCase("E"))
				{
					end = true;
					break;
				}
				if(stack.peek().getNorth() != null)
				{
					if(stack.peek().getNorth().getData().equalsIgnoreCase("*")
							|| stack.peek().getNorth().getData().equalsIgnoreCase("E"))
					{
						stack.push(stack.peek().getNorth());
						if(stack.peek().getData().equalsIgnoreCase("E"))
						{
							end = true;
						}
						stack.peek().setData("V");
						Main.updateTable(stack.peek().getX(), stack.peek().getY(), stack.peek().getData());
						passed = true;
					}
				}
				if(stack.peek().getEast() != null && !passed)
				{
					if(stack.peek().getEast().getData().equalsIgnoreCase("*")
							|| stack.peek().getEast().getData().equalsIgnoreCase("E"))
					{
						stack.push(stack.peek().getEast());
						if(stack.peek().getData().equalsIgnoreCase("E"))
						{
							end = true;
						}
						stack.peek().setData("V");
						Main.updateTable(stack.peek().getX(), stack.peek().getY(), stack.peek().getData());
						passed = true;
					}
				}
				if(stack.peek().getSouth() != null && !passed)
				{
					if(stack.peek().getSouth().getData().equalsIgnoreCase("*")
							|| stack.peek().getSouth().getData().equalsIgnoreCase("E"))
					{
						stack.push(stack.peek().getSouth());
						if(stack.peek().getData().equalsIgnoreCase("E"))
						{
							end = true;
						}
						stack.peek().setData("V");
						Main.updateTable(stack.peek().getX(), stack.peek().getY(), stack.peek().getData());
						passed = true;
					}
				}
				if(stack.peek().getWest() != null && !passed)
				{
					if(stack.peek().getWest().getData().equalsIgnoreCase("*")
							|| stack.peek().getWest().getData().equalsIgnoreCase("E"))
					{
						stack.push(stack.peek().getWest());
						if(stack.peek().getData().equalsIgnoreCase("E"))
						{
							end = true;
						}
						stack.peek().setData("V");
						Main.updateTable(stack.peek().getX(), stack.peek().getY(), stack.peek().getData());
						passed = true;
					}
				}
				System.out.println(this.toString());
				if(!passed)
				{
					stack.pop();
				}
			}
		}
		else
		{
			JOptionPane.showMessageDialog(Main.frame, "Start does not exist!");
			System.out.println("Start does not exist");
		}
		if(end)
		{
			JOptionPane.showMessageDialog(Main.frame, "End found!");
			System.out.println("End found");
		}
		else
		{
			JOptionPane.showMessageDialog(Main.frame, "No end has been found, or the path to it is blocked!");
			System.out.println("No end has been found, or the path to it is blocked");
		}
	}

	/********************************************************************
	 *
	 * Method: findExitUsingQueue
	 *
	 * Description: Traverses through the maze using a Queue. If there is no start, print “This maze has no start!”
	 If an exit cannot be found then print, “No exit was found.” Otherwise, as soon as an exit has
	 been found, stop traversing through the maze, and print, “You have found the exit!” Each
	 time a cell is visited, print the maze.
	 *
	 * @param None
	 *
	 * @return None
	 *
	 ********************************************************************/
	public void findExitUsingQueue()
	{
		Queue<Cell> q = new Queue<Cell>();
		boolean start = false, end = false;
		for(int x = 0; x<numRows; x++)
		{
			for(int y = 0; y<numCols; y++)
			{
				if(!start &&  (g.getCell(x, y).getData().equalsIgnoreCase("S")))
				{
					start = true;
					q.offer(g.getCell(x, y));
				}
			}
		}
		if(start)
		{
			while(q.peek() != null)
			{
				long currTime = System.currentTimeMillis();
				while(System.currentTimeMillis()-currTime < 500)
				{

				}
				if(q.peek().getData().equalsIgnoreCase("E"))
				{
					end = true;
					break;
				}
				if(q.peek().getNorth() != null)
				{
					if(q.peek().getNorth().getData().equalsIgnoreCase("*")
							|| q.peek().getNorth().getData().equalsIgnoreCase("E"))
					{
						q.offer(q.peek().getNorth());
					}
				}
				q.peek().setData("V");
				Main.updateTable(q.peek().getX(), q.peek().getY(), q.peek().getData());
				if(q.peek().getEast() != null)
				{
					if(q.peek().getEast().getData().equalsIgnoreCase("*")
							|| q.peek().getEast().getData().equalsIgnoreCase("E"))
					{
						q.offer(q.peek().getEast());
					}
				}
				q.peek().setData("V");
				Main.updateTable(q.peek().getX(), q.peek().getY(), q.peek().getData());
				if(q.peek().getSouth() != null)
				{
					if(q.peek().getSouth().getData().equalsIgnoreCase("*")
							|| q.peek().getSouth().getData().equalsIgnoreCase("E"))
					{
						q.offer(q.peek().getSouth());
					}
				}
				q.peek().setData("V");Main.updateTable(q.peek().getX(), q.peek().getY(), q.peek().getData());
				if(q.peek().getWest() != null)
				{
					if(q.peek().getWest().getData().equalsIgnoreCase("*")
							|| q.peek().getWest().getData().equalsIgnoreCase("E"))
					{
						q.offer(q.peek().getWest());
					}
				}
				q.peek().setData("V");Main.updateTable(q.peek().getX(), q.peek().getY(), q.peek().getData());
				q.remove();
				System.out.println(this.toString());
			}
		}
		else
		{
			JOptionPane.showMessageDialog(Main.frame, "Start does not exist!");
			System.out.println("Start does not exist");
		}
		if(end)
		{
			JOptionPane.showMessageDialog(Main.frame, "End Found!");
			System.out.println("End found");
		}
		else
		{
			JOptionPane.showMessageDialog(Main.frame, "No end has been found or path to it has been blocked!");
			System.out.println("No end has been found or path to it has been blocked");
		}
	}

	
	/********************************************************************
	 *
	 * Method: findExitUsingRecursion
	 *
	 * Description: Traverses through the maze using a recursion
	 *
	 * @param Cell c - Next cell
	 *
	 * @return Cell - Current cell
	 *
	 ********************************************************************/
	boolean dispOnce = false;
	public Cell findExitUsingRecursion(Cell c)
	{
		if(c != null)
		{
			long currTime = System.currentTimeMillis();
			while(System.currentTimeMillis()-currTime < 500)
			{

			}
			if(!found)
			{
				if(!c.getData().equalsIgnoreCase("S"))
				{
					c.setData("V");
				}
				Main.updateTable(c.getX(), c.getY(), c.getData());
				System.out.println(this.toString());
				if(c.getNorth() != null)
				{
					if(c.getNorth().getData().equalsIgnoreCase("E") || c.getNorth().getData().equalsIgnoreCase("*"))
					{
						if(c.getNorth().getData().equalsIgnoreCase("E"))
						{
							found = true;
							c = c.getNorth();
						}
						else
						{
							this.findExitUsingRecursion(c.getNorth());
						}
					}
				}
				if(c.getEast() != null)
				{
					if(c.getEast().getData().equalsIgnoreCase("E") || c.getEast().getData().equalsIgnoreCase("*"))
					{
						if(c.getEast().getData().equalsIgnoreCase("E"))
						{
							found = true;
							c = c.getEast();
						}
						else
						{
							this.findExitUsingRecursion(c.getEast());
						}
					}
				}
				if(c.getSouth() != null)
				{
					if(c.getSouth().getData().equalsIgnoreCase("E") || c.getSouth().getData().equalsIgnoreCase("*"))
					{
						if(c.getSouth().getData().equalsIgnoreCase("E"))
						{
							found = true;
							c = c.getSouth();
						}
						else
						{
							this.findExitUsingRecursion(c.getSouth());
						}
					}
				}
				if(c.getWest() != null)
				{
					if(c.getWest().getData().equalsIgnoreCase("E") || c.getWest().getData().equalsIgnoreCase("*"))
					{
						if(c.getWest().getData().equalsIgnoreCase("E"))
						{
							found = true;
							c = c.getWest();
						}
						else
						{
							this.findExitUsingRecursion(c.getWest());
						}
					}
				}
			}
		}
		else
		{
			JOptionPane.showMessageDialog(Main.frame, "No start has been found");
			System.out.println("No start has been found");
		}
		if(found && !dispOnce)
		{
			dispOnce = true;
			JOptionPane.showMessageDialog(Main.frame, "End Found!");
			System.out.println("End found");
		}
		if(c.getData().equalsIgnoreCase("S") && !found)
		{
			JOptionPane.showMessageDialog(Main.frame, "End has not been found.");
			System.out.println("End has not been found");
		}
		return c;
	}

	/********************************************************************
	 *
	 * Method: findStart
	 *
	 * Description: Finds the starting cell
	 *
	 * @param None
	 *
	 * @return Cell - starting cell
	 *
	 ********************************************************************/
	public Cell findStart()
	{
		Cell c = null;
		for(int x = 0; x<this.numRows; x++)
		{
			for(int y = 0; y<this.numCols; y++)
			{
				if(g.getCell(x, y).getData().equalsIgnoreCase("S"))
				{
					c = g.getCell(x, y);
					break;
				}
			}
		}
		if(c == null)
		{
			JOptionPane.showMessageDialog(Main.frame, "Start does not exist!");
		}
		return c;
	}

	/********************************************************************
	 *
	 * Method: getCellData
	 *
	 * Description: Gets the 2d array of the cells' data
	 *
	 * @param None
	 *
	 * @return String[][] all the cell data
	 *
	 ********************************************************************/
	public String[][] getCellData()
	{
		String[][] s = new String[this.numRows][this.numCols];
		for(int x = 0; x<this.numRows; x++)
		{
			for(int y = 0; y<this.numRows; y++)
			{
				s[x][y] = g.getData(x, y);
			}
		}
		return s;
	}

	/********************************************************************
	 *
	 * Method: resetMaze
	 *
	 * Description: Resets the maze using the initial file.
	 *
	 * @param None
	 *
	 * @return None
	 *
	 ********************************************************************/
	public void resetMaze()
	{
		for(int x = 0; x<this.numRows; x++)
		{
			for(int y = 0; y<this.numCols; y++)
			{
				g.getCell(x, y).setData(origG.getData(x, y));
			}
		}
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
		for(int x = 0; x<numRows; x++)
		{
			for(int y = 0; y<numCols; y++)
			{
				str += g.getCell(x, y).getData() + " ";
			}
			str += "\n";
		}
		return str;
	}
}
