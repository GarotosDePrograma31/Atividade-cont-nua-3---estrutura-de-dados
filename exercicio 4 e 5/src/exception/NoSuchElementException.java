package exception;

@SuppressWarnings("serial")
public class NoSuchElementException extends RuntimeException {

	public NoSuchElementException(String teste) {
		super(teste);
	}

}