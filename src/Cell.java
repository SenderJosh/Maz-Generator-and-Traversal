/***************************************************************************************
 *
 * NAME: Alyssa Higuchi
 *
 * HOMEWORK: 6
 *
 * CLASS: ICS 211
 *
 * INSTRUCTOR: Scott Robertson
 *
 * DATE: February 25, 2016
 *
 * FILE: Cell.java
 *
 * DESCRIPTION: This file contains the Cell class for homework 6. You can think of a 
 *              Cell in the same way that you think of a node.
 *
 ***************************************************************************************/

public class Cell{
	private Cell north;
	private Cell east;
	private Cell south;
	private Cell west;
	private int x, y;
	private String data;
	private boolean visited = false;

	/********************************************************************
	 *
	 * Method: Cell
	 *
	 * Description: Constructor for the Cell class
	 *
	 * @param  None
	 * 			
	 * @return None
	 * 		
	 ********************************************************************/

	public Cell(int x, int y) {
		super();
		north = null;
		east = null;
		south = null;
		west = null;
		data = null;
		this.x = x;
		this.y = y;
	}

	/********************************************************************************
	 *
	 * Method: setNorth
	 *
	 * Description: Sets the north value of the current cell to a given cell
	 *
	 * @param  cell  The Cell which will be directly north of the current cell
	 * 			
	 * @return None
	 * 		
	 ********************************************************************************/

	public void setNorth(Cell cell) {
		this.north = cell;
	}

	public int getX()
	{
		return this.x;
	}
	public int getY()
	{
		return this.y;
	}

	/********************************************************************************
	 *
	 * Method: setEast
	 *
	 * Description: Sets the east value of the current cell to a given cell
	 *
	 * @param  cell  The Cell which will be directly east of the current cell
	 * 			
	 * @return None
	 * 		
	 ********************************************************************************/

	public void setEast(Cell cell) {
		this.east = cell;
	}

	/********************************************************************************
	 *
	 * Method: setSouth
	 *
	 * Description: Sets the south value of the current cell to a given cell
	 *
	 * @param  cell  The Cell which will be directly south of the current cell
	 * 			
	 * @return None
	 * 		
	 ********************************************************************************/

	public void setSouth(Cell cell) {
		this.south = cell;
	}

	/********************************************************************************
	 *
	 * Method: setWest
	 *
	 * Description: Sets the west value of the current cell to a given cell
	 *
	 * @param  cell  The Cell which will be directly west of the current cell
	 * 			
	 * @return None
	 * 		
	 ********************************************************************************/

	public void setWest(Cell cell) {
		this.west = cell;
	}

	/********************************************************************************
	 *
	 * Method: setData
	 *
	 * Description: Sets the data of the current cell to the given data
	 *
	 * @param  newData  The data which the cell should contain
	 * 			
	 * @return None
	 * 		
	 ********************************************************************************/

	public void setData(String newData) {
		this.data = newData;
	}

	/********************************************************************************
	 *
	 * Method: setVisited
	 *
	 * Description: Sets whether or not this cell has been visited
	 *
	 * @param  bool  The truth value of this cell being visited
	 * 			
	 * @return None
	 * 		
	 ********************************************************************************/
	public void setVisited(boolean bool)
	{
		this.visited = bool;
	}

	/********************************************************************************
	 *
	 * Method: getNorth
	 *
	 * Description: Returns the cell directly north of the given cell
	 *
	 * @param  None
	 * 			
	 * @return The cell directly north of the current cell
	 * 		
	 ********************************************************************************/

	public Cell getNorth() {
		return this.north;
	}

	/********************************************************************************
	 *
	 * Method: getEast
	 *
	 * Description: Returns the cell directly east of the given cell
	 *
	 * @param  None
	 * 			
	 * @return The cell directly east of the current cell
	 * 		
	 ********************************************************************************/

	public Cell getEast() {
		return this.east;
	}

	/********************************************************************************
	 *
	 * Method: getSouth
	 *
	 * Description: Returns the cell directly south of the given cell
	 *
	 * @param  None
	 * 			
	 * @return The cell directly south of the current cell
	 * 		
	 ********************************************************************************/

	public Cell getSouth() {
		return this.south;
	}

	/********************************************************************************
	 *
	 * Method: getWest
	 *
	 * Description: Returns the cell directly west of the given cell
	 *
	 * @param  None
	 * 			
	 * @return The cell directly west of the current cell
	 * 		
	 ********************************************************************************/

	public Cell getWest() {
		return this.west;
	}

	/********************************************************************************
	 *
	 * Method: getData
	 *
	 * Description: Returns the data contained in the current cell
	 *
	 * @param  None
	 * 			
	 * @return The data contained in the current cell
	 * 		
	 ********************************************************************************/

	public String getData() {
		return this.data;
	}


	/********************************************************************************
	 *
	 * Method: getVisited
	 *
	 * Description: Returns whether or not the cell has been visited or not
	 *
	 * @param  None
	 * 			
	 * @return The boolean value of whether or not the cell has been visited
	 * 		
	 ********************************************************************************/
	public boolean getVisited()
	{
		return this.visited;
	}
}