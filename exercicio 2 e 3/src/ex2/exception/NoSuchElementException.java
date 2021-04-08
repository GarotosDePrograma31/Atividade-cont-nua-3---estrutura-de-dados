package ex2.exception;

@SuppressWarnings("serial")
public class NoSuchElementException extends RuntimeException {

	public NoSuchElementException(String err) {
		super(err);
	}

}