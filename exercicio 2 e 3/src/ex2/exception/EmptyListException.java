package ex2.exception;

@SuppressWarnings("serial")
public class EmptyListException extends RuntimeException {

	public EmptyListException(String err) {
		super(err);
	}

}