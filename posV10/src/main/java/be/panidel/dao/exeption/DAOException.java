package be.panidel.dao.exeption;

public class DAOException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4020374089579178601L;

	public DAOException() {
		super();
	}

	public DAOException(String message, Throwable cause) {
		super(message, cause);
	}

	public DAOException(String message) {
		super(message);
	}

	public DAOException(Throwable cause) {
		super(cause);
	}
	

}
