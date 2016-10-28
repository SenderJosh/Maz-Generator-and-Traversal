import java.util.EmptyStackException;

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
* FILE: Stack.java
*
* DESCRIPTION: This file contains the methods required to create a stack, and uses the MyList class to store the data
*
***************************************************************************************/
@SuppressWarnings("unchecked")
public class Stack<E> {

	private MyList list;
	
	/********************************************************************
	*
	* Method: Stack
	*
	* Description: Constructor for the stack class
	*
	* @param None
	*
	* @return None
	*
	********************************************************************/
	public Stack()
	{
		list = new MyList();
	}
	
	/********************************************************************
	*
	* Method: Stack
	*
	* Description: Constructor for the stack class, takes in a list for a preset stack
	*
	* @param MyList list
	*
	* @return None
	*
	********************************************************************/
	public Stack(MyList list)
	{
		this.list = list;
	}
	
	/********************************************************************
	*
	* Method: push
	*
	* Description: Adds an element to the top of the stack
	*
	* @param E element
	*
	* @return E element
	*
	********************************************************************/
	public E push(E element)
	{
		list.add(element);
		return element;
	}
	
	/********************************************************************
	*
	* Method: pop
	*
	* Description: Takes off the top element and returns it
	*
	* @param None
	*
	* @return E element
	* 
	* @throws EmptyStackException
	*
	********************************************************************/
	public E pop() throws EmptyStackException
	{
		if(list.size()<=0)
		{
			throw new EmptyStackException();
		}
		E ele = (E) list.get(list.size()-1);
		list.removeIndex(list.size()-1);
		return ele;
	}
	
	/********************************************************************
	*
	* Method: peek
	*
	* Description: Returns the top element of the stack
	*
	* @param None
	*
	* @return E element
	* 
	* @throws EmptyStackException
	*
	********************************************************************/
	public E peek() throws EmptyStackException
	{
		if(list.size()<=0)
		{
			throw new EmptyStackException();
		}
		return (E)list.get(list.size()-1);
	}
	
	/********************************************************************
	*
	* Method: empty
	*
	* Description: Checks if the stack is empty
	*
	* @param None
	*
	* @return Boolean
	*
	********************************************************************/
	public boolean empty()
	{
		return (list.size() > 0) ? false : true;
	}
}
