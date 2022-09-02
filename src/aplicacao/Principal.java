package aplicacao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.DB;
import database.DbException;

public class Principal {
	
	public static void main(String[] args) {
		
		Connection conn = null;
		Statement st = null;
		ResultSet rt = null;
		try {
			conn = DB.getConnection();
			
			st = conn.createStatement();
			
			rt = st.executeQuery("select * from evento_comanda");
			
			while(rt.next()) {
				System.out.println(rt.getInt("ID")+ ", " + rt.getLong("CD_PERSON")
				+ ", " + rt.getLong("NR_COMANDA") + ", " + rt.getLong("NR_COMANDA")+ 
				", " + rt.getString("TP_PERSON") + ", " + rt.getDate("DH_ATD")+ 
				", " + rt.getLong("CD_USU_ATD")+ ", " + rt.getBoolean("TP_SIT") + 
				", " + rt.getDate("DH_ATU")+ ", " + rt.getLong("CD_USU_ATU"));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			
		}
		finally {
			DB.closeStatement(st);;
			DB.closeResultSet(rt);
			DB.closeConnection();
		}
		
		
		
		
		
	}
}
