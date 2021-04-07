package exception;

@SuppressWarnings("serial")

public class EmptyStackException extends RuntimeException {
	public EmptyStackException(String teste) {
		super(teste);
	}

}