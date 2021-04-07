package Interfaces;

import exception.BoundaryViolationException;
import exception.InvalidPositionException;

public interface ArvoreBinaria<E> extends Tree<E> {

	public Position<E> left(Position<E> v) throws InvalidPositionException, BoundaryViolationException;

	public Position<E> right(Position<E> v) throws InvalidPositionException, BoundaryViolationException;

	public boolean hasLeft(Position<E> v) throws InvalidPositionException;

	public boolean hasRight(Position<E> v) throws InvalidPositionException;

}