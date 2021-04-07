package Interfaces;

public interface TreeP<E> extends Position<E> {

	public void setElement(E o);

	public E getElement();

	public PositionList<Position<E>> getChildren();

	public void setChildren(PositionList<Position<E>> c);

	public TreeP<E> getParent();

	public void setParent(TreeP<E> v);

}