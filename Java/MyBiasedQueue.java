/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hw3;

/**
 *
 * @author tukkkur
 * @param <T>
 */
public class MyBiasedQueue<T> {
     T[] queue = createArrayOfSize(100);
    String nameOfThingWeHate;
    
    private T[] createArrayOfSize(int size)
	{
		T[] newArray = (T[]) new Object[size];
		return newArray;
	}
    
    
    public MyBiasedQueue(String nameOfThingWeHate)
    {
     this.nameOfThingWeHate = nameOfThingWeHate; 
      T[] queue = createArrayOfSize(100);
    }
    public  void clear()
    {
        for (int i = 0; i < queue.length; i++)
        {
            queue[i] = null;
        }
    }
    
    public boolean isEmpty()
    {
         for (T data1 : queue)
       {
           if (null != data1)
           {
               return false;
           }
       }
       return true;
    }
    
    public Object peek()
    {
        if (isEmpty())
        {
            return null;
        }
        for (int i = 0; i < queue.length-1; i++)
        {
        if (queue[i].toString().equals(nameOfThingWeHate))
        {
             push(queue[i]);
        }
        else return queue[i];
        }
        return queue[0];
    }
    
    public Object pop()
    {
         if (isEmpty())
        {
            grow();
        }
         else if (peek().toString().equals(nameOfThingWeHate))
         {
             push((T)nameOfThingWeHate);
         }
        for (int i = 0; i < getSize()-1; i++)
        {
            queue[i] = queue[i+1];
        }
        queue[getSize()] = null;
        T answer = queue[0];
        return answer;
    }
    
        public void push(T data)
        {
           if (isEmpty())
           {
               grow();
           }
            if (atCapacity() == true)
            {
                grow();
            }
             queue[getSize()]=data;
    }
            
            
            
            
        
    
    
    
    
    
    
    private void grow()
	{
           T[] newOne = MyArrayList(queue.length * 2);
           for (int i = 0; i < queue.length; i++)
           {
               newOne[i] = queue[i];
           }
           queue = newOne;
	}
     
     
     private T[] MyArrayList(int initialCapacity)
    {
        T[] newData = (T[]) new Object[initialCapacity];
        return newData;
    }
     
      private void pushToBack(T data)
    {
         T[] placeholder = MyArrayList(queue.length);
        for (int i = 0; i < queue.length; i++)
        {
            placeholder[i] = queue[i];
        }
        queue[queue.length-1] = data;
        for (int x = 0; x < queue.length-1; x++)
        {
            queue[x] = placeholder[x];
        }
    }
      
       private boolean atCapacity()
        {
                for (int i = 0; i < queue.length; i++) 
                {
                    if (queue[i] == null)
                    {
                       return false;
                    } 
               }
            return true;
            
        }
        public int getSize()
    {
        int count = 0;
            for (int i = 0; i < queue.length; i++)
            {
                if (null != queue[i])
                {
                    count++;
                }
            }
            return count;
    }
}
