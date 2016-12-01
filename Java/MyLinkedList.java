/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lab07;

/**
 *
 * @author tukkkur
 */
public class MyLinkedList<T> 
implements MyList<T> 
{
    ListNode<T> head;
   
    
    
   
   
   public int getSize()
   {
       int size = 0;
       ListNode curr = head;
       while (curr != null)
       {
           curr = curr.next;
           size++;
       }
       return size;
   }
   public T get(int index)
   {
       int x = 0;
       ListNode<T> curr = head;
      while (null != curr)
      {
         if (x == index)
         {
             return curr.data;
         }
         curr = curr.next;
         x++;
      }
       return curr.data;
   }
   public void add(T item)
   {
       ListNode<T>  current = head;
       ListNode<T> newOne = new ListNode<>();
       newOne.data = item;
       if (current == null)
       {
           head = newOne;
       }
       else {
           while (null != current.next)
           {
           current = current.next;
           }
           current.next = newOne;
       }
   }
   public void clear()
   {
       while (head.next != null)
       {
           head.data = null;
           head = head.next;        
       }
   }
}
