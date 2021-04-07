package pilhas;

import exception.EmptyStackException;
import exception.FullStackException;
import Interfaces.Stack;

public class ArrayStack<E> implements Stack<E> {

	protected int capacidade; 

	public static final int CAPACIDADE = 1000; 

	protected E S[]; 

	protected int top = -1; 

	public ArrayStack() {


		this(CAPACIDADE);
	}

	@SuppressWarnings("unchecked")

	public ArrayStack(int cap) {

		capacidade = cap;



		S = (E[]) new Object[capacidade];

	}

	public int size() {

		return (top + 1);

	}

	public boolean isEmpty() {

		return (top < 0);

	}

	public void push(E element) throws FullStackException {

		if (size() == capacidade)

			throw new FullStackException("Stack is full.");

		S[++top] = element;

	}

	public E top() throws EmptyStackException {

		if (isEmpty())
			throw new EmptyStackException("Stack is empty.");

		return S[top];

	}

	public E pop() throws EmptyStackException {

		E element;

		if (isEmpty())
			throw new EmptyStackException("Stack is empty.");

		element = S[top];

		S[top--] = null;

		return element;

	}

	public String toString() {
		String s;
		s = "[";
		if (size() > 0)
			s += S[0];
		if (size() > 1) {
			for (int i = 1; i <= size() - 1; i++) {
				s += ", " + S[i];
			}
		}
		return s + "]";

	}

}