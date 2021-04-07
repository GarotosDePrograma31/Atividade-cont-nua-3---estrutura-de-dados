package exception;

@SuppressWarnings("serial")

public class FullStackException extends RuntimeException {
	public FullStackException(String teste) {
		super(teste);
	}

}