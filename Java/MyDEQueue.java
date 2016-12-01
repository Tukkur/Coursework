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
public class MyDEQueue<T> extends MyLinkedList<T>

{
    
    public void pushToBack(T data) {
		add(data);
	}
    public void pushToFront(T data)
    {
        insert(0, data);
    }

	public T pop()
	{
		if (isEmpty())
		{
			return null;
		}

		T data = get(0);
		head = head.next;
		return data;
	}
        
        public T peek() {
            if (isEmpty())
		{
			return null;
		}
		T data = get(0);
		return data;
        }
}