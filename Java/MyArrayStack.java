/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hw3;

/**
 *
 * @author tukkkur
 */
public class MyArrayStack<T> {
    T[] stack = createArrayOfSize(100);
    private T[] createArrayOfSize(int size)
	{
		T[] newArray = (T[]) new Object[size];
		return newArray;
	}
    
    public void clear()
    {
        for (int i = 0; i < stack.length; i++)
        {
            stack[i] = null;
        }
    }
    
    public boolean isEmpty()
    {
       for (T data1 : stack)
       {
           if (null != data1)
           {
               return false;
           }
       }
       return true;
    }
    
    
    
    public T peek()
    {
        if (atCapacity() == true)
        {
            grow();
        }
        if (isEmpty())
        {
            return null;
        }
        return stack[getSize()-1];
    }
    
    public void push(T data)
    {
        if (atCapacity() == true)
        {
            grow();
        }
        stack[getSize()] = data;
    }
    public T pop()
    {
        T top = null;
        if (!isEmpty())
        {
             int index = 0;
        while (null != stack[index])
        {
            index++;
        }
            top = stack[index-1];
            stack[index-1] = null;
        }
        return top;
    }
    
    
    private void grow()
	{
           T[] newOne = MyArrayList(stack.length * 2);
           for (int i = 0; i < stack.length; i++)
           {
               newOne[i] = stack[i];
           }
           stack = newOne;
	}
    private boolean atCapacity()
        {
                for (int i = 0; i < stack.length; i++) 
                {
                    if (stack[i] == null)
                    {
                       return false;
                    } 
               }
            return true;
            
        }
    
    private int getSize()
    {
        int count = 0;
            for (int i = 0; i < stack.length; i++)
            {
                if (null != stack[i])
                {
                    count++;
                }
            }
            return count;
    }
     private T[] MyArrayList(int initialCapacity)
    {
        T[] newData = (T[]) new Object[initialCapacity];
        return newData;
    }
    
    private T[] MyArrayList(T[] items)
    {
        int size = 0;
        for (T x : items) 
        {
            size++;
        }
        T[] list = MyArrayList(size);
        for (int i = 0; i < list.length; i++)
        {
            items[i] = list[i];
        }
        return list;
    }

    
}
