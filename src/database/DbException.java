package database;

public class DbException extends RuntimeException{
	/**
	 * Classe com Exceção personalizada para Evitar de tratar diferentes tipos de exceções nas outras classes.
	 * Com um costrutor personalizado também 
	 */
	private static final long serialVersionUID = 1L;
	
	public DbException(String msg) {
		super(msg);
	}

}
