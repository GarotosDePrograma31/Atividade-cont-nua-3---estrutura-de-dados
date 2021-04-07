package arvoreb4e5;

import java.util.Iterator;

import exception.BoundaryViolationException;
import exception.EmptyListException;
import exception.InvalidPositionException;
import Interfaces.Position;
import Interfaces.PositionList;

public class NodePositionList<E> implements PositionList<E> {

	protected int numElts; 

	protected Node<E> header, trailer; 

	public NodePositionList() {

		numElts = 0;

		header = new Node<E>(null, null, null); 

		trailer = new Node<E>(header, null, null); 

		header.setNext(trailer); 

	}
	protected Node<E> checkPosition(Position<E> p) throws InvalidPositionException {

		if (p == null)
			throw new InvalidPositionException("Posição null passada para NodeList");

		if (p == header)
			throw new InvalidPositionException("O node do cabeçalho não é uma posição válida");

		if (p == trailer)
			throw new InvalidPositionException("O node não é uma posição válida");

		try {

			Node<E> temp = (Node<E>) p;

			if ((temp.getPrev() == null) || (temp.getNext() == null))

				throw new InvalidPositionException("A posição não pertence a uma NodeList válida");

			return temp;

		} catch (ClassCastException e) {

			throw new InvalidPositionException("A posição está errada para esta lista");

		}

	}

	public int size() {
		return numElts;
	}

	public boolean isEmpty() {
		return (numElts == 0);
	}

	public Position<E> first() throws EmptyListException {

		if (isEmpty())
			throw new EmptyListException("Lista está vazia");

		return header.getNext();

	}

	public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException {

		Node<E> v = checkPosition(p);

		Node<E> prev = v.getPrev();

		if (prev == header)
			throw new BoundaryViolationException("Não é possível avançar além do início da lista");

		return prev;

	}
	public void addBefore(Position<E> p, E element) throws InvalidPositionException {

		Node<E> v = checkPosition(p);

		numElts++;

		Node<E> newNode = new Node<E>(v.getPrev(), v, element);

		v.getPrev().setNext(newNode);

		v.setPrev(newNode);

	}
	public void addFirst(E element) {

		numElts++;

		Node<E> newNode = new Node<E>(header, header.getNext(), element);

		header.getNext().setPrev(newNode);

		header.setNext(newNode);

	}
	public E remove(Position<E> p) throws InvalidPositionException {

		Node<E> v = checkPosition(p);

		numElts--;

		Node<E> vPrev = v.getPrev();

		Node<E> vNext = v.getNext();

		vPrev.setNext(vNext);

		vNext.setPrev(vPrev);

		E vElem = v.element();


		v.setNext(null);

		v.setPrev(null);

		return vElem;

	}

	public E set(Position<E> p, E element) throws InvalidPositionException {

		Node<E> v = checkPosition(p);

		E oldElt = v.element();

		v.setElement(element);

		return oldElt;

	}

	public Position<E> last() {

		if (isEmpty())
			throw new EmptyListException("List is empty");

		return trailer.getPrev();

	}

	public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException {

		Node<E> v = checkPosition(p);

		Node<E> next = v.getNext();

		if (next == trailer)
			throw new BoundaryViolationException("Cannot advance past the finaling of the list");

		return next;

	}

	public void addLast(E e) {

		numElts++;

		Node<E> newNode = new Node<E>(trailer.getPrev(), trailer, e);

		trailer.getPrev().setNext(newNode);

		trailer.setPrev(newNode);

	}

	public void addAfter(Position<E> p, E e) throws InvalidPositionException {

		if (checkpositionlist(p) == false) {
			throw new InvalidPositionException("Nodo n�o pertence a lista");
		}

		Node<E> v = checkPosition(p);

		numElts++;

		Node<E> newNode = new Node<E>(v, v.getNext(), e);

		v.getNext().setPrev(newNode);

		v.setNext(newNode);

	}

	public void MakeFirst(Position<E> mudar) {
		Node p = header.getNext();
		Node temp = this.checkPosition(mudar);

		Node mprev = temp.getPrev();
		Node mnext = temp.getNext();
		mprev.setNext(mnext);
		mnext.setPrev(mprev);

		temp.setNext(header.getNext());
		temp.setPrev(header);
		header.setNext(temp);

	}

	public boolean checkpositionlist(Position<E> p) {
		Node<E> recebido = checkPosition(p);
		Node<E> primeiro = header.getNext();

		for (int i = 0; i < numElts; i++) {

			if (recebido.element() == primeiro.element()) {
				if (recebido.getPrev() == primeiro.getPrev() && recebido.getNext() == recebido.getNext()) {
					return true;
				}
			}
			primeiro = primeiro.getNext();
		}
		return false;
	}

	public static <E> String toString(PositionList<E> l) {
		String s = "";
		for (E i : l) {
			s += ", " + i;
		}
		s = (s.length() == 0 ? s : s.substring(2));
		return "[" + s + "]";
	}
	
	public Iterator<E> iterator() {
		return new EIterator<E>(this);
	}

	public String toString() {
		return toString(this);
	}
}