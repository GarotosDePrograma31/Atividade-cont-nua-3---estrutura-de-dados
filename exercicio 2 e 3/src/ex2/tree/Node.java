package ex2.tree;

import ex2.exception.InvalidPositionException;
import ex2.interfaces.Position;

public class Node<E> implements Position<E> {

	private Node<E> prev, next; 

	private E element; 


	public Node(Node<E> newPrev, Node<E> newNext, E elem) {

		prev = newPrev;

		next = newNext;

		element = elem;

	}

	public E element() throws InvalidPositionException {

		if ((prev == null) && (next == null))

			throw new InvalidPositionException("Position is not in a list!");

		return element;

	}

	public Node<E> getNext() {
		return next;
	}

	public Node<E> getPrev() {
		return prev;
	}

	public void setNext(Node<E> newNext) {
		next = newNext;
	}

	public void setPrev(Node<E> newPrev) {
		prev = newPrev;
	}

	public void setElement(E newElement) {
		element = newElement;
	}

	
}