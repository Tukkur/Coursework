package lab07;

/**
 * This interface promises that classes that implement it will have
 * these methods. In other words, it makes the promise that MyArrayList
 * and MyLinkedList will have methods named add, get and getSize.
 *
 * @author baylor
 * @param <T>
 */
public interface MyList<T>
{
	public void add(T item);
	public T    get(int index);
	public int  getSize();
        public void clear();
}
