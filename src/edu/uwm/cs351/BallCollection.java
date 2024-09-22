package edu.uwm.cs351;

import java.util.AbstractCollection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

/**
 * An implementation of the collection interface 
 * specialized to {@link Ball} objects.
 * The implementation uses dynamic arrays.
 */
public class BallCollection extends AbstractCollection<Ball>// TODO: extends something
{
	public Ball[] data;
	public int manyItems;
	public int version;
	private static int INITIAL_CAPACITY = 1;
	private static Consumer<String> reporter = (s) -> System.out.println("Invariant error: "+ s);

	private BallCollection(boolean ignored) {} // do not change this constructor

	// TODO: dynamic array data structure + version
	// Add an invariant tester (wellFormed())
	// and test it at the start of all public methods, and at the end
	// of public methods that make changes.
	// We recommend that you copy much from BallSeq in HW #2 solution
	
	public BallCollection( )
	{
		// NB: NEVER assert the invariant at the START of the constructor.
		// (Why not?  Think about it.)
		manyItems = 0;
		data = new Ball[INITIAL_CAPACITY];
		version = 0;
		assert wellFormed() : "Invariant false at end of constructor";
	}

	
	private boolean report(String error) {
		reporter.accept(error);
		return false;
	}
	
	@Override // required
	public Iterator<Ball> iterator() {
		assert wellFormed() : "invariant broken at start of iterator()";
		return new MyIterator();
	}
	
	private boolean wellFormed() {
		// TODO Auto-generated method stub
		// Check the invariant.
		// 1. data is never null
		if (data == null) return report("data is null"); // test the NEGATION of the condition

		// 2. The data array is at least as long as the number of items
		//    claimed by the sequence.
		// TODO
		if (data.length < manyItems) return report("ManyItems is greater than data.length: ");

		// 3. currentIndex is never negative and never more than the number of
		//    items claimed by the sequence.
		// TODO
		if(manyItems < 0) return report("Current element is negative or never more than the number of items claimed");

		// If no problems discovered, return true
		return true;
}

	private class MyIterator // TODO: implements something
	implements Iterator<Ball> // 3#
	{
		public int index;
		public boolean canRemove;
		public int colVersion;

		MyIterator(boolean ignored) {} // should only be used by Spy

		public MyIterator() {
			this.index = 0;
			this.canRemove = true;
			this.colVersion = version;
			
		}

		public boolean wellFormed() {
			// TODO Auto-generated method stub
			if(!BallCollection.this.wellFormed()) return false;
			if(BallCollection.this.version != colVersion) return true;
			if(index >= manyItems || index < -1) return report("The index field is a invalid index in the dynamic array, or equal not to -1");
			if(canRemove && index < 0) return report("the index is not a valid index ");
			
			return true;
		}

		@Override
		public boolean hasNext() {
			return canRemove;
			// TODO Auto-generated method stub

		}

		@Override
		public Ball next() {
			return null;
			// TODO Auto-generated method stub
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
				
		}	
	}
	@Override
	public int size() {
		assert wellFormed() : "Invariant failed at the start of size()";
		return manyItems;
	}
	
	@Override
	public boolean add(Ball b) {
		assert wellFormed() : "invariant failed at start of insert";

		if(data.length == manyItems) {
			Ball[] biggerArray;
			int newCapacity = 2*data.length+1;
			biggerArray = new Ball[newCapacity];
			//Copy all the elements in data array to new array
			for(int i=0;i<manyItems;++i) {
				biggerArray[i] = data[i];		
			}
			data = biggerArray;
		}
		data[manyItems++] = b;
		version++;

		assert wellFormed() : "invariant failed at end of insert";
		return true;

	}
	/**
	 * Used for testing the invariant.  Do not change this code.
	 */
	public static class Spy {
		/**
		 * Return the sink for invariant error messages
		 * @return current reporter
		 */
		public Consumer<String> getReporter() {
			return reporter;
		}

		/**
		 * Change the sink for invariant error messages.
		 * @param r where to send invariant error messages.
		 */
		public void setReporter(Consumer<String> r) {
			reporter = r;
		}

		/**
		 * Create a debugging instance of the main class
		 * with a particular data structure.
		 * @param a static array to use
		 * @param m size to use
		 * @param v current version
		 * @return a new instance with the given data structure
		 */
		public BallCollection newInstance(Ball[] a, int m, int v) {
			BallCollection result = new BallCollection(false);
			result.data = a;
			result.manyItems = m;
			result.version = v;
			return result;
		}
		
		/**
		 * Return an iterator for testing purposes.
		 * @param bc main class instance to use
		 * @param i index of iterator
		 * @param r the value of 'canRemove'
		 * @param v the value of colVersion
		 * @return iterator with this data structure
		 */
		public Iterator<Ball> newIterator(BallCollection bc, int i, boolean r, int v) {
			MyIterator result = bc.new MyIterator(false);
			result.index = i;
			result.canRemove = r;
			result.colVersion = v;
			return result;
		}
		
		/**
		 * Return whether debugging instance meets the 
		 * requirements on the invariant.
		 * @param bs instance of to use, must not be null
		 * @return whether it passes the check
		 */
		public boolean wellFormed(BallCollection bs) {
			return bs.wellFormed();
		}
		
		/**
		 * Return whether debugging instance meets the 
		 * requirements on the invariant.
		 * @param i instance of to use, must not be null
		 * @return whether it passes the check
		 */
		public boolean wellFormed(Iterator<Ball> i) {
			return ((MyIterator)i).wellFormed();
		}
	
	}
}
	