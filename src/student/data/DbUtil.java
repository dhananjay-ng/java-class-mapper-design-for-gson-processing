package student.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Properties;

public class DbUtil {
	public static String url = "jdbc:derby:c:/dev/db/student-db3";
	public static String driver = "org.apache.derby.jdbc.EmbeddedDriver";

	public static void main(String args[]) {
		

		startDerby();
		try (Connection con = DbUtil.getConnection()) {
			//createUserTable(con);
			//createStudentTable(con);


		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			stopDerby();

		}

	}

	private static void createUserTable(Connection con) throws SQLException {
		String sql = "create Table \"user\""//
				+ "("//
				+ "id varchar(25) not null primary key,"//
				+ "name varchar(50) not null,"//
				+ "password varchar(50) not null"//
				+ ")";//
		try(Statement statement=con.createStatement()){
			statement.execute(sql);

		}
		
		
	}
	
	private static void createStudentTable(Connection con) throws SQLException {
		String sql = "create Table \"student\""//
				+ "("//
				+ "id varchar(25) not null primary key,"//
				+ "name varchar(50) not null,"//
				+ "gender varchar(2) not null,"//
				+ "birthDate date not null,"//
				+ "joinDate date not null,"//
				+ "standard integer not null,"//
				+ "division varchar(2) not null,"//
				+ "rollNumber integer not null"//
				+ ")";//
		try(Statement statement=con.createStatement()){
			statement.execute(sql);
		}
	}

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url);
    }
    
	public static void stopDerby() {
		try {
			Properties properties = new Properties();
			properties.setProperty("shutdown", "true");
			Connection c = DriverManager.getConnection(url, properties);
		} catch (SQLException e) {
			// Ignore expected Exception
		}
	}

	public static void startDerby() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		try {
			Properties properties = new Properties();
			properties.setProperty("create", "true");
			Connection c = DriverManager.getConnection(url, properties);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
