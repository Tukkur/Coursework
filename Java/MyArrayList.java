package hw3;

public class MyArrayList<T>
{

	private T[] createArrayOfSize(int size)
	{
		T[] newArray = (T[]) new Object[size];
		return newArray;
	}
        
         public T[] data = createArrayOfSize(100);
    
    public MyArrayList()
    {
       data = createArrayOfSize(100);
    }
    
    public MyArrayList(int initialCapacity)
    {
       createArrayOfSize(initialCapacity);
    }
    
    public MyArrayList(T[] items)
    {
        data = (T[]) new Object[items.length];
        for (int i = 0; i < data.length; i++)
        {
            data[i] = items[i];
        }
        
    }
    
    public void add(T item)
    {
         if (atCapacity() == true)
            {
                grow();
            }
            for (int i = 0; i < data.length; i++)
            {
                if (null == data[i])
                {
                    data[i] = item;
                    break;
                }
            }
    }
    
    public void addAll(T[] items)
    {
        if (atCapacity() == true)
        {
            grow();
        }
        for (int x = 0; x < items.length; x++)
        {
            for (int i = 0; i < data.length; i++)
            {
                if (null == data[i])
                {
                    data[i] = items[x];
                    break;
                }
            }
        }
    }
    
    public void clear()
        {
            for (int i = 0; i < data.length; i++)
            {
                data[i] = null;
            }
        }
    
    public boolean contains(T item)
    {
        boolean found = false;
        for (int i = 0; i < data.length; i++)
        {
            if (item.equals(data[i]))
            {
                found = true;
            }
        }
        return found;
    }
   
    public T get(int index)
    {
        return data[index];
    }
    
    
    public int getSize()
    {
        int count = 0;
            for (int i = 0; i < data.length; i++)
            {
                if (null != data[i])
                {
                    count++;
                }
            }
            return count;
    }
    
    
    public void remove(int index)
    {   
        if (index < getSize())
        {
       for (int i = index; i < getSize()-1; i++)
       {
           data[i] = data[i+1];
       }
     data[getSize()] = null;
        }
    }
    
    public void removeRange(int start, int end)
    {
        for (int i = start; i <= end; i++)
        {
           remove(start);
        }
    }
    
    public void insert(int index, T item)
    {
        T[] placeholder = data;
        grow();
        data[index]= item;
        int replaced = index-1;
        int end = data.length-1;
        for (int i = replaced; i < end-1; i++)
        {
            data[i] = placeholder[i+1];
        }
    }
    
    public void update(int index, T item)
    {
        data[index] = item;
    }
    
    private void grow()
	{
          T[] newOne = (T[]) new Object[data.length * 2];
           for (int i = 0; i < data.length; i++)
           {
               newOne[i] = data[i];
           }
           data = newOne;
	}
    
    private boolean atCapacity()
        {
                for (int i = 0; i < data.length; i++) 
                {
                    if (data[i] == null)
                    {
                       return false;
                    } 
               }
            return true;
            
        }
}
