package Interfaces;

public interface BPosition<E> extends Position<E>{
	public E element();

	public void setElement(E o);

	public BPosition<E> getLeft();

	public void setLeft(BPosition<E> v);

	public BPosition<E> getRight();

	public void setRight(BPosition<E> v);

	public BPosition<E> getParent();

	public void setParent(BPosition<E> v);

}