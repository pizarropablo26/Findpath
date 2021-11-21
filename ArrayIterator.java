


import java.util.*;
/**
 * This class represents an iterator over the elements of an array.
 * @author jy
 *
 * @param <T> a generic type
 */
public class ArrayIterator<T> implements Iterator
{
   private int count;    // the number of elements in the collection
   private int current;  // the current position in the iteration 
   private T[] array; 

   /**
    * This method sets up this iterator using the specified items.
    * @param collection collection for which the iterator will be created
    * @param size size of the collection
    */
   public ArrayIterator (T[] collection, int size)
   {
      array = collection;
      count = size;
      current = 0;
   }

   /**
    * This method returns true if this iterator has a next element
    * @return  true if this iterator has at least one more element to deliver
    */
   public boolean hasNext()
   {
	   if(current < count) {
		   return true;
	   }
	   else {
		   return false;
	   }
   }

   /**
    * This method returns the next element in the iteration.
    * @return  the next element in the iteration
    * @throws NoSuchElementException a exception thrown if there is no element
    */
   public T next()
   {
      if (! hasNext()) {
         throw new NoSuchElementException();
      }
 	  current++;
      return array[current-1]; 
   }

   /**
    * The remove operation is not supported in this collection.
    * @throws UnsupportedOperationException  thrown if an unsupported operation exception occurs
    */
   public void remove() throws UnsupportedOperationException
   {
      throw new UnsupportedOperationException();
   }
}

