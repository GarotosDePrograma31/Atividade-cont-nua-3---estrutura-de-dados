package arvoreb4e5;

import java.util.Iterator;

import exception.NoSuchElementException;
import Interfaces.Position;
import Interfaces.PositionList;

public class EIterator<E> implements Iterator<E> {

	protected PositionList<E> list;

	protected Position<E> cursor;


	public EIterator(PositionList<E> L) {

		list = L;

		cursor = (list.isEmpty()) ? null : list.first();

	}

	public boolean hasNext() {
		return (cursor != null);
	}


	public E next() throws NoSuchElementException {

		if (cursor == null)
			throw new NoSuchElementException("No next element");

		E toReturn = cursor.element();

		cursor = (cursor == list.last()) ? null : list.next(cursor);

		return toReturn;

	}
	public void remove() throws UnsupportedOperationException {

		throw new UnsupportedOperationException("remove");

	}

}