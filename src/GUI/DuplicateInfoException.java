package GUI;

public class DuplicateInfoException extends RuntimeException{

	private static final long serialVersionUID = -8457184792357764767L;
	
	public DuplicateInfoException(String errorMessage) {
		super(errorMessage);
	}
	
}
