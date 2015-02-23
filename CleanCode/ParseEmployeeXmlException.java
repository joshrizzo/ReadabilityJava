package CleanCode;

public class ParseEmployeeXmlException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public ParseEmployeeXmlException(String message) {											///NOTE: Used var names to differentiate constructors.
		super(message);
	}

	public ParseEmployeeXmlException(Exception cause) {
		super(cause);
	}
}
