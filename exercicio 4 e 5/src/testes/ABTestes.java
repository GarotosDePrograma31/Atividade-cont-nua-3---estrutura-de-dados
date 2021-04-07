package testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Interfaces.Position;
import pilhas.*;
import arvoreb4e5.LinkedBinaryTree;

class BinaryTreeTest {

	// a) 
	LinkedBinaryTree<String> T = BuildExpression("((((3+1)*3)/((9-5)+2))-((3*(7-4))+6))");

	// b) 
	@Test
	void binaryPreorderTest() {
		assertEquals("-/*+313+-952+*3-746", T.binaryPreOrder(T, T.root()));
	}

	// c) 
	@Test
	void binaryPostorderTest() {
		assertEquals("31+3*95-2+/374-*6+-", T.binaryPostOrder(T, T.root()));
	}

	// d) 
	@Test
	void evaluateExpression() {
		assertEquals(-13.0, T.evaluateExpression(T, T.root()));
	}

	// e) 
	@Test
	void inorderTest() {
		assertEquals("3+1*3/9-5+2-3*7-4+6", T.inorder(T, T.root()));
	}

	// f) 
	@Test
	void makeBTSSearchTest() {
		LinkedBinaryTree<Integer> BTS = makerBTSearch();

		assertEquals("12, 25, 31, 36, 42, 58, 62, 75, 90",
				BTS.inorder(BTS, BTS.root(), ", ").substring(0, BTS.inorder(BTS, BTS.root(), ", ").length() - 2));
	}
	// g) 
	
	@Test
	void desenharArvore() {
		T.desenhaArvore(T, T.left(T.root()), 0, 0);
	}

	// h) 
	@Test
	void eulerTourTest() {
		LinkedBinaryTree<Integer> BTS = makerBTSearch();
		assertEquals("-/*+333+111+*333*/+-999-555-+222+/-+*333*-777-444-*+666+-", T.eulerTour(T, T.root()));
	}

	// i) 
	@Test
	void printExpressionTest() {
		LinkedBinaryTree<Integer> BTS = makerBTSearch();
		assertEquals("((((3+1)*3)/((9-5)+2))-((3*(7-4))+6))", T.printExpression(T, T.root()));
	}

	// j) 
	@Test
	void contarNodosEsquerdaTest() {
		assertEquals(11, T.contesquerda(T, T.root()));
	}

	// k) 
	@Test
	void contarNodosDireitaTest() {
		assertEquals(7, T.contdireita(T, T.root()));
	}

	

	public LinkedBinaryTree<String> BuildExpression(String E) {
		ArrayStack<LinkedBinaryTree<String>> S = new ArrayStack<LinkedBinaryTree<String>>();

		for (int i = 0; i < E.length(); i++) {
			if (E.charAt(i) != ')' && E.charAt(i) != '(') {
				LinkedBinaryTree<String> T = new LinkedBinaryTree<String>();
				T.addRoot(Character.toString(E.charAt(i)));
				S.push(T);
			} else if (E.charAt(i) == '(') {

			} else if (E.charAt(i) == ')') {
				LinkedBinaryTree<String> T2 = new LinkedBinaryTree<String>();
				T2 = S.pop();
				LinkedBinaryTree<String> T = new LinkedBinaryTree<String>();
				T = S.pop();
				LinkedBinaryTree<String> T1 = new LinkedBinaryTree<String>();
				T1 = S.pop();
				T.attach(T.root(), T1, T2);
				S.push(T);
			}
		}
		return S.pop();
	}

	public LinkedBinaryTree<Integer> makerBTSearch() {
		LinkedBinaryTree<Integer> n = new LinkedBinaryTree<Integer>();
		int totalnum = 9;
		int[] num = { 31, 25, 42, 12, 36, 90, 62, 75 };
		Position<Integer> raiz, left, right, d, f;
		n.addRoot(58);
		raiz = n.root();
		left = n.root();
		right = n.root();

		left = n.insertLeft(raiz, 31);
		right = n.insertRight(left, 42);
		left = n.insertLeft(left, 25);
		n.insertLeft(right, 36);
		n.insertLeft(left, 12);

		right = n.insertRight(raiz, 90);
		left = n.insertLeft(right, 62);
		n.insertRight(left, 75);

		return n;

	}

}