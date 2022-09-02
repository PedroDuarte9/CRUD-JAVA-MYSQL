package aplicacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import database.DB;


public class Principal {
	
	public static void main(String[] args) throws ParseException {
		
		//Classe para editar a data
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/mm/dd");
		
		//Declarando a váriável para poder chamar no método
		Connection conn = null;
		
		//Declarando a váriável para poder chamar no método
		PreparedStatement st = null; 
		
		//Tratamento de exceções
		try {
			conn = DB.getConnection(); //Iniciando a chamada da variável para receber a conexão com o Banco 
			
			//Iniciando a variável reponsável pela inserção dos dados SQL
			st = conn.prepareStatement(
					"INSERT INTO evento_comanda"
					+ "(ID, CD_PERSON, NR_COMANDA, CD_AREA_EVT, TP_PERSON, DH_ATD, CD_USU_ATD, TP_SIT, DH_ATU, CD_USU_ATU)" 
					+ "VALUES "
					+ "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);//Particulariedade do Java para inserir os dados que forem necessários e retornar o id da linha que foi inserida
			
			//Variável.setTipoDoDado(Posição, DadoInserido)
			st.setInt(1, 3);
			st.setLong(2, 2300);
			st.setLong(3,1003);
			st.setLong(4, 155);
			st.setString(5, "CONVIDADO");
			st.setDate(6, new java.sql.Date(sdf.parse("2022/09/02").getTime()));
			st.setLong(7, 6321);
			st.setBoolean(8, false);
			st.setDate(9,new java.sql.Date(sdf.parse("2022/09/03").getTime()));
			st.setLong(10, 2626);
			
			//Variável para executar a inserção, no banco
			int linhasAfetadas = st.executeUpdate();
			
			//Nesse caso faremos isso para retornar o id da linha que foi inserida
			if(linhasAfetadas > 0) {
				ResultSet rt = st.getGeneratedKeys();
				while(rt.next()) {
					int id = rt.getInt(1);
					System.out.println("Pronto, o ID é = " + id);
				}
			}
			else{
				System.out.println("Nenhuma linha afetada !");
			}
			
		}//Restante do tratamento de exceções pegando as exceções específicas
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(ParseException e) {
			e.printStackTrace();
		}//Fechando as conexões 
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
			
		}
	}
}
