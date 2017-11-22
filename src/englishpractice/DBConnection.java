package englishpractice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {

    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement;

    public void readDataBase() throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver"); // This will load the MySQL driver, each DB has its own driver
            connect = DriverManager.getConnection("jdbc:mysql://localhost/englishpractice?user=root&password="); // Setup the connection with the DB
            statement = connect.createStatement();  // Statements allow to issue SQL queries to the database 
        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        }
    }

    public void executeUpdateQuery(String query) throws SQLException {
        preparedStatement = connect.prepareStatement(query);
        preparedStatement.executeUpdate();
    }

    public ResultSet executeSelectQuery(String query) throws SQLException {
        preparedStatement = connect.prepareStatement(query);
        return preparedStatement.executeQuery();
    }

    public void close(Connection connect, Statement statement, ResultSet resultSet) throws Exception {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connect != null) {
                connect.close();
            }
        } catch (SQLException e) {
            throw new Exception(e);
        }
    }
}
