package aplicacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import database.DB;


public class Principal {
	
	public static void main(String[] args)  {
		
		
		Connection conn = null;
		
		PreparedStatement st = null; 
		
		
		try {
			conn = DB.getConnection();
			
			st = conn.prepareStatement(
					"UPDATE evento_comanda"
				+ " SET TP_PERSON = ? "
				+ "WHERE"
				+ "(ID = ?)"
				);
			
			st.setString(1, "SÓCIO PROPRIETÁRIO");
			st.setInt(2, 3);
			
			int linhasAfetadas = st.executeUpdate();
			
			System.out.println("Pronto !  Linha Alterada " + linhasAfetadas);
			
			}
			catch(SQLException e){
				e.printStackTrace();
				
			}
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}
}
