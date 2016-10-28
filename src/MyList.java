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
* FILE: MyList.java
*
* DESCRIPTION: This file contains the methods required to create a list, as in add, remove, indexof, etc.
*
***************************************************************************************/
public class MyList {
	
	private Object[] list;
	
	/********************************************************************
	*
	* Method: MyList
	*
	* Description: Initializes list to an array of size 0
	*
	* @param None
	*
	* @return None
	*
	********************************************************************/
	public MyList()
	{
		list = new Object[0];
	}
	
	/********************************************************************
	*
	* Method: add
	*
	* Description: Adds an element to the end of the list
	*
	* @param Integer element - Element to add
	*
	* @return None
	*
	********************************************************************/
	public void add(Object element)
	{
		Object[] newList = new Object[list.length+1];
		for(int i = 0; i<list.length; i++)
		{
			newList[i] = list[i];
		}
		newList[list.length]  = element;
		list = newList;
	}
	
	/********************************************************************
	*
	* Method: add
	*
	* Description: Adds an element at the given index. Returns true if the element was successfully added. False otherwise.
	*
	* @param Integer index - Index to add the element to
	* @param Integer element - Element to add at the index
	*
	* @return Boolean
	*
	********************************************************************/
	public boolean add(int index, Object element)
	{
		Object[] newList = new Object[list.length+1];
		boolean success = false;
		if(!(index < 0 || index > list.length-1))
		{
			for(int i = 0; i<index; i++)
			{
				newList[i] = list[i];
			}
			newList[index] = element;
			for(int i = index; i<list.length; i++)
			{
				newList[i+1] = list[i];
			}
			success = true;
			list = newList;
		}
		return success;
	}

	/********************************************************************
	*
	* Method: clear
	*
	* Description: Clears all contents of the list
	*
	* @param None
	*
	* @return None
	*
	********************************************************************/
	public void clear()
	{
		list = new Object[0];
	}
	
	/********************************************************************
	*
	* Method: contains
	*
	* Description: Returns true if the list contains the given element. False otherwise.
	*
	* @param Integer element - The element to search
	*
	* @return Boolean
	*
	********************************************************************/
	public boolean contains(Object element)
	{
		boolean exists = false;
		for(int i = 0; i<list.length; i++)
		{
			if(list[i].equals(element))
			{
				exists = true;
				break;
			}
		}
		return exists;
	}
	
	/********************************************************************
	*
	* Method: get
	*
	* Description: Returns the element at the given index. Throws an IndexOutOfBoundsException
	*
	* @param Integer index - Element's location in the array
	*
	* @return int
	* 
	* @throws IndexOutOfBoundsException
	*
	********************************************************************/
	/*Returns the element at the given index. Throws an IndexOutOfBoundsException otherwise.*/
	public Object get(int index) throws IndexOutOfBoundsException
	{
		return list[index];
	}
	
	/********************************************************************
	*
	* Method: indexOf
	*
	* Description: Returns the index of the first occurrence of the element or -1 if the element does not exist.
	*
	* @param Integer element - Element to search for the first occurrence of
	*
	* @return Integer
	*
	********************************************************************/
	public int indexOf(Object element)
	{
		int ret = -1;
		for(int i = 0; i<list.length; i++)
		{
			if(list[i].equals(element))
			{
				ret = i;
				break;
			}
		}
		return ret;
	}
	
	/********************************************************************
	*
	* Method: isEmpty
	*
	* Description: Returns true if the list is empty or false if the list is not empty.
	*
	* @param None
	*
	* @return Boolean
	*
	********************************************************************/
	public boolean isEmpty()
	{
		return (list.length != 0) ? false : true;
	}
	
	/********************************************************************
	*
	* Method: lastIndexOf
	*
	* Description: Returns the index of the last occurrence of the element or -1 if the element does not exist.
	*
	* @param Integer element - Element to search for the last occurrence of
	*
	* @return Integer
	*
	********************************************************************/
	public int lastIndexOf(Object element)
	{
		int ret = -1;
		for(int i = list.length-1; i>=0; i--)
		{
			if(list[i].equals(element))
			{
				ret = i;
				break;
			}
		}
		return ret;
	}
	
	/********************************************************************
	*
	* Method: removeIndex
	*
	* Description: Removes the element at the given index. Returns true if the element was removed. False if otherwise.
	*
	* @param Integer index - Element's location that you wis ht oremove.
	*
	* @return Boolean
	*
	********************************************************************/
	public boolean removeIndex(int index)
	{
		boolean removed = false;
		if(!(index < 0 || index >= list.length))
		{
			Object[] newList = new Object[list.length-1];
			for(int i = 0; i<index; i++)
			{
				newList[i] = list[i];
			}
			for(int i = index; i<list.length-1; i++)
			{
				newList[i] = list[i+1];
			}
			list = newList;
			removed = true;
		}
		return removed;
	}
	
	/********************************************************************
	*
	* Method: removeElement
	*
	* Description: Removes the first occurrence of the element. Returns true if the element was removed. False if otherwise.
	*
	* @param Integer element - The element you want to remove (first occurrence)
	*
	* @return Boolean
	*
	********************************************************************/
	public boolean removeElement(Object element)
	{
		boolean removed = false;
		for(int i = 0; i<list.length; i++)
		{
			if(list[i].equals(element))
			{
				removed = true;
				removeIndex(i);
			}
		}
		return removed;
	}
	
	/********************************************************************
	*
	* Method: set
	*
	* Description: Sets the value at the given index to the given element. Returns true if the value was successfully set, false if otherwise.
	*
	* @param Integer index - Index of which you wish to change to the element
	* @param Integer element - Element that you will set at the index
	*
	* @return None
	*
	********************************************************************/
	public boolean set(int index, Object element)
	{
		boolean set = false;
		if(!(index < 0 || index >= list.length))
		{
			list[index] = element;
			set = true;
		}
		return set;
	}
	
	/********************************************************************
	*
	* Method: size
	*
	* Description: Returns the number of elements in this list.
	*
	* @param None
	*
	* @return Integer
	*
	********************************************************************/
	public int size()
	{
		return list.length;
	}
	
	/********************************************************************
	*
	* Method: printList
	*
	* Description: Prints the list. If the list is empty, print "[]". Otherwise, print "[1, 2, 3, etc...]".
	*
	* @param None
	*
	* @return None
	*
	********************************************************************/
	public void printList()
	{
		String str = "[";
		if(list.length == 0)
		{
			str = "[]";
		}
		else
		{
			for(int i = 0; i<list.length; i++)
			{
				if(i==list.length-1)
				{
					str += list[i] + "]";
				}
				else
				{
					str += list[i] + ", ";
				}
			}
		}
		System.out.println(str);
	}
}