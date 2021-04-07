package arvoreb4e5;

import java.util.Arrays;
import java.util.Iterator;

import exception.BoundaryViolationException;
import exception.EmptyTreeException;
import exception.InvalidPositionException;
import exception.NonEmptyTreeException;
import Interfaces.BPosition;
import Interfaces.ArvoreBinaria;
import Interfaces.Position;
import Interfaces.PositionList;
import Interfaces.Tree;

public class LinkedBinaryTree<E> implements ArvoreBinaria<E> {

	protected BPosition<E> root; 

	protected int size; 


	public LinkedBinaryTree() {

		root = null; 

		size = 0;

	}

	public int size() {
		return size;
	}


	public boolean isInternal(Position<E> v) throws InvalidPositionException {

		checkPosition(v); 

		return (hasLeft(v) || hasRight(v));

	}


	public boolean isRoot(Position<E> v) throws InvalidPositionException {

		checkPosition(v);

		return (v == root());

	}


	public boolean hasLeft(Position<E> v) throws InvalidPositionException {

		BPosition<E> vv = checkPosition(v);

		return (vv.getLeft() != null);

	}

	public Position<E> root() throws EmptyTreeException {

		if (root == null)
			throw new EmptyTreeException("The tree is empty");

		return root;

	}

	public Position<E> left(Position<E> v) throws InvalidPositionException, BoundaryViolationException {

		BPosition<E> vv = checkPosition(v);

		Position<E> leftPos = (Position<E>) vv.getLeft();

		if (leftPos == null)
			throw new BoundaryViolationException("No left child");

		return leftPos;

	}

	public Position<E> parent(Position<E> v) throws InvalidPositionException, BoundaryViolationException {

		BPosition<E> vv = checkPosition(v);

		Position<E> parentPos = (Position<E>) vv.getParent();

		if (parentPos == null)
			throw new BoundaryViolationException("No parent");

		return parentPos;

	}

	public Iterable<Position<E>> children(Position<E> v) throws InvalidPositionException {

		PositionList<Position<E>> children = new NodePositionList<Position<E>>();

		if (hasLeft(v))
			children.addLast(left(v));

		if (hasRight(v))
			children.addLast(right(v));

		return children;

	}

	public void inorderPositions(Position<E> v, PositionList<Position<E>> pos) throws InvalidPositionException {

		if (hasLeft(v))
			inorderPositions(left(v), pos); 

		pos.addLast(v);

		if (hasRight(v))
			inorderPositions(right(v), pos); 

	}

	public Iterable<Position<E>> positionsInorder() {

		PositionList<Position<E>> positions = new NodePositionList<Position<E>>();

		if (size != 0)
			inorderPositions(root(), positions); 

		return positions;

	}

	public Iterable<Position<E>> positions() {

		PositionList<Position<E>> positions = new NodePositionList<Position<E>>();

		if (size != 0)
			preorderPositions(root(), positions); 

		return positions;

	}

	public Iterator<E> iterator() {

		Iterable<Position<E>> positions = positions();

		PositionList<E> elements = new NodePositionList<E>();

		for (Position<E> pos : positions)
			elements.addLast(pos.element());

		return elements.iterator(); 

	}

	public E replace(Position<E> v, E o) throws InvalidPositionException {

		BPosition<E> vv = checkPosition(v);

		E temp = v.element();

		vv.setElement(o);

		return temp;

	}

	public Position<E> sibling(Position<E> v) throws InvalidPositionException, BoundaryViolationException {

		BPosition<E> vv = checkPosition(v);

		BPosition<E> parentPos = vv.getParent();

		if (parentPos != null) {

			BPosition<E> sibPos;

			BPosition<E> leftPos = parentPos.getLeft();

			if (leftPos == vv)
				sibPos = parentPos.getRight();

			else
				sibPos = parentPos.getLeft();

			if (sibPos != null)
				return sibPos;

		}

		throw new BoundaryViolationException("No sibling");

	}

	public Position<E> addRoot(E e) throws NonEmptyTreeException {

		if (!isEmpty())
			throw new NonEmptyTreeException("Tree already has a root");

		size = 1;

		root = createNode(e, null, null, null);

		return root;

	}

	public Position<E> insertLeft(Position<E> v, E e) throws InvalidPositionException {

		BPosition<E> vv = checkPosition(v);

		Position<E> leftPos = (Position<E>) vv.getLeft();

		if (leftPos != null)
			throw new InvalidPositionException("Node already has a left child");

		BPosition<E> ww = createNode(e, vv, null, null);

		vv.setLeft(ww);

		size++;

		return ww;

	}

	public E remove(Position<E> v) throws InvalidPositionException {

		BPosition<E> vv = checkPosition(v);

		BPosition<E> leftPos = vv.getLeft();

		BPosition<E> rightPos = vv.getRight();

		if (leftPos != null && rightPos != null)
			throw new InvalidPositionException("Cannot remove node with two children");

		BPosition<E> ww; 
		if (leftPos != null)
			ww = leftPos;

		else if (rightPos != null)
			ww = rightPos;

		else 

			ww = null;

		if (vv == root) { 

			if (ww != null)
				ww.setParent(null);

			root = ww;

		} else { 

			BPosition<E> uu = vv.getParent();

			if (vv == uu.getLeft())
				uu.setLeft(ww);

			else
				uu.setRight(ww);

			if (ww != null)
				ww.setParent(uu);

		}

		size--;

		return v.element();

	}

	public void attach(Position<E> v, ArvoreBinaria<E> T1, ArvoreBinaria<E> T2) throws InvalidPositionException {

		BPosition<E> vv = checkPosition(v);

		if (isInternal(v))
			throw new InvalidPositionException("Cannot attach from internal node");

		if (!T1.isEmpty()) {

			BPosition<E> r1 = checkPosition(T1.root());

			vv.setLeft(r1);

			r1.setParent(vv); // T1 deve ser invalidada

		}

		if (!T2.isEmpty()) {

			BPosition<E> r2 = checkPosition(T2.root());

			vv.setRight(r2);

			r2.setParent(vv); 

		}

	}

	protected BPosition<E> checkPosition(Position<E> v) throws InvalidPositionException {

		if (v == null || !(v instanceof BPosition))
			throw new InvalidPositionException("The position is invalid");

		return (BPosition<E>) v;

	}

	protected BPosition<E> createNode(E element, BPosition<E> parent, BPosition<E> left, BPosition<E> right) {

		return new ABNode<E>(element, parent, left, right);

	}

	protected void preorderPositions(Position<E> v, PositionList<Position<E>> pos) throws InvalidPositionException {

		pos.addLast(v);

		if (hasLeft(v))
			preorderPositions(left(v), pos); 

		if (hasRight(v))
			preorderPositions(right(v), pos); 

	}

	public boolean isEmpty() {
		return (size == 0);
	}

	public boolean isExternal(Position<E> v) throws InvalidPositionException {
		return !isInternal(v);
	}

	public Position<E> right(Position<E> v) throws InvalidPositionException, BoundaryViolationException {

		BPosition<E> vv = checkPosition(v);

		Position<E> rightPos = (Position<E>) vv.getRight();

		if (rightPos == null)
			throw new BoundaryViolationException("No right child");

		return rightPos;

	}

	public boolean hasRight(Position<E> v) throws InvalidPositionException {

		BPosition<E> vv = checkPosition(v);

		return (vv.getRight() != null);

	}

	public static <E> String toString(LinkedBinaryTree<E> T) {

		String s = "";

		for (Iterator<E> it = T.iterator(); it.hasNext();) {

			s += ", " + it.next().toString();

		}

		s = (s.length() == 0 ? s : s.substring(2));

		return "[" + s + "]";

	}

	public String binaryPostOrder(LinkedBinaryTree<E> T, Position<E> v) {

		LinkedBinaryTree<E> sub = new LinkedBinaryTree<E>();
		String f = "";
		sub.addRoot(T.root().element());
		if (T.hasLeft(v)) {

			f += binaryPostOrder(sub, sub.left(v));
		}
		if (T.hasRight(v)) {
			f += binaryPostOrder(sub, sub.right(v));
		}

		f += T.checkPosition(v).element();
		return f;

	}

	public String binaryPreOrder(LinkedBinaryTree<E> T, Position<E> v) {

		LinkedBinaryTree<E> sub = new LinkedBinaryTree<E>();
		String f = "";
		f = T.checkPosition(v).element().toString();
		sub.addRoot(T.root().element());
		if (T.hasLeft(v)) {
			f += binaryPreOrder(sub, sub.left(v));
		}
		if (T.hasRight(v)) {
			f += binaryPreOrder(sub, sub.right(v));
		}

		return f;

	}

	public Double evaluateExpression(LinkedBinaryTree<E> T, Position<E> v) {
		Double f = 0.0;
		if (T.isInternal(v)) {
			Double x = evaluateExpression(T, T.left(v));
			Double y = evaluateExpression(T, T.right(v));
			switch (T.checkPosition(v).element().toString()) {
			case "+":
				return f = x + y;

			case "-":
				return f = x - y;

			case "/":
				return f = x / y;

			case "*":
				return f = x * y;
			}

		}
		return f = Double.parseDouble(T.checkPosition(v).element().toString());
	}

	public String inorder(LinkedBinaryTree<E> T, Position<E> v, String divisor) {
		String f = "";
		if (T.hasLeft(v) == true) {
			f += inorder(T, T.left(v), divisor);
		}

		f += T.checkPosition(v).element().toString() + divisor;
		if (T.hasRight(v) == true) {
			f += inorder(T, T.right(v), divisor);
		}

		return f;

	}

	public Position<E> insertRight(Position<E> v, E e) throws InvalidPositionException {

		BPosition<E> vv = checkPosition(v);

		Position<E> rightPos = (Position<E>) vv.getRight();

		if (rightPos != null)
			throw new InvalidPositionException("Node already has a left child");

		BPosition<E> ww = createNode(e, vv, null, null);

		vv.setRight(ww);

		size++;

		return ww;

	}

	public String parentheticRepresentation(Tree<E> T, Position<E> v) {

		String s = v.element().toString(); 

		if (T.isInternal(v)) {

			Boolean firstTime = true;

			for (Position<E> w : T.children(v)) {

				if (firstTime) {

					s += "(" + parentheticRepresentation(T, w);

					firstTime = false;

				} else {

					s += "," + parentheticRepresentation(T, w);

				}

				s += ")"; 

			}

		}

		return s;

	}

	public String eulerTour(LinkedBinaryTree<E> T, Position<E> v) {
		String f = "";
		f += T.checkPosition(v).element();

		if (T.hasLeft(v)) {
			f += eulerTour(T, T.left(v));
		}
		f += T.checkPosition(v).element();
		if (T.hasRight(v)) {
			f += eulerTour(T, T.right(v));
		}
		f += T.checkPosition(v).element();
		return f;

	}

	public String printExpression(LinkedBinaryTree<E> T, Position<E> v) {
		String f = "";
		if (T.isInternal(v)) {
			f += "(";
		}
		if (T.hasLeft(v)) {
			f += printExpression(T, T.left(v));
		}
		if (T.isInternal(v)) {
			f += (T.checkPosition(v).element());
		} else {
			f += (T.checkPosition(v).element());
		}
		if (T.hasRight(v)) {
			f += printExpression(T, T.right(v));
		}
		if (T.isInternal(v)) {
			f += ")";
		}

		return f;
	}

	public int contesquerda(LinkedBinaryTree<E> T, Position<E> v) {

		int f = 0;

		f = inorder(T, T.left(v)).length();

		return f;

	}

	public int contdireita(LinkedBinaryTree<E> T, Position<E> v) {

		int f = 0;

		f = inorder(T, T.right(v)).length();

		return f;

	}

	public String inorder(LinkedBinaryTree<E> T, Position<E> v) {
		String f = "";
		if (T.hasLeft(v) == true) {
			f += inorder(T, T.left(v));
		}
		f += T.checkPosition(v).element().toString();
		if (T.hasRight(v) == true) {
			f += inorder(T, T.right(v));
		}

		return f;

	}

	public int depth(LinkedBinaryTree<E> T, Position<E> v) {
		if (T.isRoot(v)) {
			return 0;
		}
		return 1 + depth(T, T.parent(v));
	}

	public void desenhaArvore(LinkedBinaryTree<E> T, Position<E> v, int qtdenpercorrido, int profundidade) {

		PositionList<Position<E>> posicoes = new NodePositionList<Position<E>>();
		posicoes = (PositionList<Position<E>>) T.positionsInorder();

		String[][] desenho = new String[T.height1(T) + 1][posicoes.size()];

		for (String linha[] : desenho) {
			Arrays.fill(linha, "=");
		}

		int aux = 0;
		for (Position<E> posicao : posicoes) {

			desenho[T.depth(T, posicao)][aux] = posicao.element().toString();

			aux++;
		}

		for (int i = profundidade; i < desenho.length; i++) {
			for (int j = qtdenpercorrido; j < desenho[0].length; j++) {
				System.out.print(desenho[i][j]);
			}
			System.out.println();
		}
	}

	public int height1(LinkedBinaryTree<E> T) {
		int h = 0;
		for (Position<E> v : T.positions()) {
			if (T.isExternal(v)) {
				h = Math.max(h, T.depth(T, v));
			}
		}
		return h;
	}

}