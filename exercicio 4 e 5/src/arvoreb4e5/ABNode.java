package arvoreb4e5;

import Interfaces.BPosition;

public class ABNode<E> implements BPosition<E>{

private E element; 

private BPosition<E> left, right, parent; 

public ABNode(E element, BPosition<E> parent, BPosition<E> left, BPosition<E> right) {

setElement(element);

setParent(parent);

setLeft(left);

setRight(right);

}

public E element() { return element; }

public void setElement(E o) { element = o; }

public BPosition<E> getLeft() { return left; }

public void setLeft(BPosition<E> v) { left = v; }

public BPosition<E> getRight() { return right; }

public void setRight(BPosition<E> v) { right = v; }

public BPosition<E> getParent() { return parent; }

public void setParent(BPosition<E> v) { parent = v; }

}