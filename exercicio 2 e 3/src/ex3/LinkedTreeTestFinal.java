package ex3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import ex2.interfaces.Position;
import ex2.interfaces.PositionList;
import ex2.interfaces.TreePosition;
import ex2.tree.DiscNode;
import ex2.tree.LinkedTree;
import ex2.tree.NodePositionList;
import ex2.tree.TreeNode;

class LinkedTreeTestFinal {
	TreePosition<String> raiz;

	Position<Position<String>> p, s;

	PositionList<Position<String>> filhos;

	LinkedTree<String> T = criarArvoreT();
	@Test
	void test() {
		assertFalse(T.isEmpty());
		
		assertEquals("[Eletronics R'Us, P&D, Vendas, Internacional, Canad�, Am�rica do Sul, "

		+ "Ultramar, �frica, Europa, �sia, Austr�lia, Nacional, Compras, Manufatura, TV, CD, Tuner]",

		T.toString(), "Pr�-ordem da �rvore T ");

		raiz = T.root();

		filhos = raiz.getChildren();

		p = filhos.first();

		assertEquals("P&D", p.element().element(), "P&D");

		assertTrue(T.isExternal(p.element()));

		assertEquals(raiz, T.parent(p.element()), "Deve ser a raiz");

		s = filhos.next(p);

		assertEquals("Vendas", s.element().element(), "Vendas");

		assertTrue(T.isInternal(s.element()));

		T.replace(p.element(), "Pesquisa e Desenvolvimento");

		assertEquals("[Eletronics R'Us, Pesquisa e Desenvolvimento, Vendas, Internacional, Canad�, Am�rica do Sul, "

		+ "Ultramar, �frica, Europa, �sia, Austr�lia, Nacional, Compras, Manufatura, TV, CD, Tuner]",

		T.toString(), "Pr�-ordem da �rvore T ");

		assertTrue(T.isRoot(raiz));

		T.swapElements(p.element(), s.element());

		assertEquals("[Eletronics R'Us, Vendas, Pesquisa e Desenvolvimento, Internacional, Canad�, Am�rica do Sul, "

		+ "Ultramar, �frica, Europa, �sia, Austr�lia, Nacional, Compras, Manufatura, TV, CD, Tuner]",

		T.toString(), "Pr�-ordem da �rvore T ");
	}
	
	

		@Test
		void ParentheticRepresentationTest() {
			System.out.print(T.parentheticRepresentation(T, T.root()));
		}
	
	

	@Test
	void PosOrderTestTest() {
		String saida = "P&D\n"
				+ "Canad�\n"
				+ "Am�rica do Sul\n"
				+ "�frica\n"
				+ "Europa\n"
				+ "�sia\n"
				+ "Austr�lia\n"
				+ "Ultramar\n"
				+ "Internacional\n"
				+ "Nacional\n"
				+ "Vendas\n"
				+ "Compras\n"
				+ "TV\n"
				+ "CD\n"
				+ "Tuner\n"
				+ "Manufatura\n"
				+ "Eletronics R'Us";
		assertEquals(saida,T.toStringpostorder(T, T.root()));
	}
	

	@Test
	void depthTest() {

		assertEquals(1, T.depth(T,T.root().getChildren().first().element()), "A profundidade da Arvore");
	}
	

	@Test
	void height1Test() {
		assertEquals(4, T.height1(T), "Altura da �rvore T");
		
	}

	@Test
	void height2Test() {
		assertEquals(4, T.height2(T, T.root()), "Altura da �rvore T");
				
	}

	private TreeNode<String> criarFilho(TreeNode<String> p, String n) {

		PositionList<Position<String>> filhos;

		TreeNode<String> aux;

		filhos = p.getChildren();

		aux = new TreeNode<String>();

		aux.setElement(n);

		aux.setParent(p);

		aux.setChildren(new NodePositionList<Position<String>>());

		filhos.addLast(aux);

		return aux;

	}

	public LinkedTree<String> criarArvoreT() {

		LinkedTree<String> T = new LinkedTree<String>();

		TreeNode<String> raiz, v, m, i, u;

		T.addRoot("Eletronics R'Us");

		raiz = (TreeNode<String>) T.root();

		raiz.setChildren(new NodePositionList<Position<String>>());

		criarFilho(raiz, "P&D");

		v = criarFilho(raiz, "Vendas");

		criarFilho(raiz, "Compras");

		m = criarFilho(raiz, "Manufatura");

		i = criarFilho(v, "Internacional");

		criarFilho(v, "Nacional");

		criarFilho(i, "Canad�");

		criarFilho(i, "Am�rica do Sul");

		u = criarFilho(i, "Ultramar");

		criarFilho(u, "�frica");

		criarFilho(u, "Europa");

		criarFilho(u, "�sia");

		criarFilho(u, "Austr�lia");

		criarFilho(m, "TV");

		criarFilho(m, "CD");

		criarFilho(m, "Tuner");

		return T;

	}

	public LinkedTree<DiscNode<String>> criarArvoreD() {

		LinkedTree<DiscNode<String>> T = new LinkedTree<DiscNode<String>>();

		DiscNode<String> raiz, cs252, cs016, projetos, trabalhos, demos, temas;

		
		T.addRoot(new DiscNode<String>("5124 /usuario/rt/cursos/",null,null));

		raiz = (DiscNode) T.root().getElement();
		raiz.setChildren(new NodePositionList<Position<String>>());


		cs252 = criarFilhoD(raiz, "4874 cs252/");

		cs016 = criarFilhoD(raiz, "249 cs016/");


		projetos = criarFilhoD(cs252, "4870 projetos/");
		criarFilhoD(cs252, "3 notas/");


		criarFilhoD(cs016, "8 notas/");
		temas = criarFilhoD(cs016, "1 temas/");

		trabalhos = criarFilhoD(projetos, "1 trabalhos/");
		demos = criarFilhoD(projetos, "1 demos");

		criarFilhoD(temas, "3 hw1");
		criarFilhoD(temas, "2 hw2");
		criarFilhoD(temas, "4 hw3");
		criarFilhoD(temas, "57 pr1");
		criarFilhoD(temas, "97 pr2");
		criarFilhoD(temas, "74 pr3");

		criarFilhoD(trabalhos, "26 comprebaixo");
		criarFilhoD(trabalhos, "55 vendealto");
		criarFilhoD(demos, "4786 mercado");

		return T;

	}

	private DiscNode<String> criarFilhoD(DiscNode<String> p, String n) {

		PositionList<Position<String>> filhos;

		DiscNode<String> aux;
		
		String nome = n.split(n)[0];
		int by = Integer.parseInt(n.split(n)[1]);

		filhos = p.getChildren();

		aux = new DiscNode<String>();

		aux.setElement(n);
		aux.setKbyte(by);
		aux.setname(nome);

		aux.setParent(p);

		aux.setChildren(new NodePositionList<Position<String>>());

		filhos.addLast(aux);

		return aux;

	}
}