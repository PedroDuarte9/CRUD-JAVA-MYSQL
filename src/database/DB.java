package database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {

	//Método Estático para Iniciar a Conexão com o Banco de dados, iniciada em nulo.  
	private static Connection conn = null;
	
	
	//Continuação do Método com o bloco que fará a conexão com o banco através
	//das informações que foram passadas nas propriedades
	
	public static Connection getConnection() {
		if(conn == null) {
			try {
			Properties props = loadProperties();
			String url = props.getProperty("dburl");
			conn = DriverManager.getConnection(url, props);
			}
			catch(SQLException e) {
				throw new DbException(e.getMessage());	
			}
		}
		return conn;
	}
	//Método estático para fechar o Statement
	public static void closeStatement(Statement st){
		if (st != null){
			try {
				st.close();
			}
			catch(SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	//Método Estático para fechar o ResultSet
	public static void closeResultSet(ResultSet rt){
		if (rt != null){
			try {
				rt.close();
			}
			catch(SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	public static void closeConnection() {
		if (conn != null) {
			try {
				conn.close();
			}
			catch(SQLException e) {
				throw new DbException(e.getMessage());
			}
		}	
	}
	
	//Método Estático para definir onde está a propriedade dos arquivos que serão lidos pelo banco,
	//com tratamento de exceções para evitar possíveis erros
	private static Properties loadProperties() {
		try(FileInputStream fs = new FileInputStream("db.properties")){
			Properties props = new Properties();
			props.load(fs);
			return props;
		}
		catch(IOException e) {
			throw new DbException(e.getMessage());	
		}
	}
}
