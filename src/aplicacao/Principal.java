package aplicacao;

import java.sql.Connection;

import database.DB;

public class Principal {
	
	public static void main(String[] args) {
		
		Connection conn = DB.getConnection(); 
				DB.closeConnection();
	}
}
