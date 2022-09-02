package aplicacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import database.DB;
import database.DbIntegrityException;


public class Principal {
	
	public static void main(String[] args)  {
		
		
		Connection conn = null;
		
		PreparedStatement st = null; 
		
		
		try {
			conn = DB.getConnection();
			
			st = conn.prepareStatement(
				"DELETE FROM evento_comanda "
				+ "WHERE"
				+ "(ID = ?)"
				);
			
			st.setInt(1, 3);
			
			int linhasAfetadas = st.executeUpdate();
			
			System.out.println("Pronto !  Linha Alterada " + linhasAfetadas);
			
			}
			catch(SQLException e){
				throw new DbIntegrityException(e.getMessage()); //Exceção para caso seja deletado uma linha que contenha uma FK
				
			}
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}
}
