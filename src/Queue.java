import java.util.NoSuchElementException;

/***************************************************************************************
*
* NAME: Joshua Nishiguchi
*
* HOMEWORK: 5
*
* CLASS: ICS 211
*
* INSTRUCTOR: Scott Robertson
*
* DATE: February 25, 2016
*
* FILE: Queue<E>.java
*
* DESCRIPTION: This file contains the methods required to create a queue, and uses the MyList class to store the data
*
***************************************************************************************/
@SuppressWarnings("unchecked")
public class Queue<E> {
	
	MyList list;
	
	/********************************************************************
	*
	* Method: Queue
	*
	* Description: Constructor for creating a queue
	*
	* @param None
	*
	* @return None
	*
	********************************************************************/
	public Queue()
	{
		list = new MyList();
	}
	
	/********************************************************************
	*
	* Method: Queue
	*
	* Description: Constructor for creating a queue with a preset list
	*
	* @param MyList list
	*
	* @return None
	*
	********************************************************************/
	public Queue(MyList list)
	{
		this.list = list;
	}
	
	/********************************************************************
	*
	* Method: offer
	*
	* Description: Adds an element to the top of the queue and returns it
	*
	* @param E element
	*
	* @return E element
	*
	********************************************************************/
	public E offer(E element)
	{
		list.add(element);
		return element;
	}
	
	/********************************************************************
	*
	* Method: poll
	*
	* Description: Removes and returns the first element in the queue
	*
	* @param None
	*
	* @return E element
	*
	********************************************************************/
	public E poll()
	{
		E ele = null;
		if(list.size()>0)
		{
			ele = (E) list.get(0);
			list.removeIndex(0);
		}
		return ele;
	}
	
	/********************************************************************
	*
	* Method: remove
	*
	* Description: Removes and returns the first element in the queue
	*
	* @param None
	*
	* @return E element
	* 
	* @throws NoSuchElementException
	*
	********************************************************************/
	public E remove()
	{
		if(list.size()<=0)
		{
			throw new NoSuchElementException();
		}
		E ele = (E) list.get(0);
		list.removeIndex(0);
		return ele;
	}
	
	/********************************************************************
	*
	* Method: peek
	*
	* Description: Returns the first element in the queue
	*
	* @param None
	*
	* @return E element
	*
	********************************************************************/
	public E peek()
	{
		E ele = null;
		if(list.size()>0)
		{
			ele = (E) list.get(0);
		}
		return ele;
	}
	
	/********************************************************************
	*
	* Method: element
	*
	* Description: Returns the first element in the queue
	*
	* @param None
	*
	* @return None
	* 
	* @throws NoSuchElementException
	*
	********************************************************************/
	public E element()
	{
		if(list.size()<=0)
		{
			throw new NoSuchElementException();
		}
		E ele = (E) list.get(0);
		return ele;
	}

}
