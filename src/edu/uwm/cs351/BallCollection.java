package edu.uwm.cs351;

import java.util.AbstractCollection;
import java.util.Iterator;
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
	private static Consumer<String> reporter = (s) -> System.out.println("Invariant error: "+ s);

	private BallCollection(boolean ignored) {} // do not change this constructor

	// TODO: dynamic array data structure + version
	// Add an invariant tester (wellFormed())
	// and test it at the start of all public methods, and at the end
	// of public methods that make changes.
	// We recommend that you copy much from BallSeq in HW #2 solution
	
	@Override // required
	public Iterator<Ball> iterator() {
		assert wellFormed() : "invariant broken at start of iterator()";
		return new MyIterator();
	}
	
	private boolean wellFormed() {
		// TODO Auto-generated method stub
		return false;
	}

	private class MyIterator // TODO: implements something
	implements Iterator<Ball> // 3#
	{
		public int index;
		public boolean canRemove;
		public int colVersion;

		MyIterator(boolean ignored) {} // should only be used by Spy

		public MyIterator() {
			// TODO Auto-generated constructor stub
		}

		public boolean wellFormed() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Ball next() {
			// TODO Auto-generated method stub
			return null;
		}
		
		// TODO: data structure including local version
		// Implement invariant, constructor and required methods.
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

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}
}
