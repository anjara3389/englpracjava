package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBConnection {

    public Connection connectDB() throws Exception {
        Class.forName("com.mysql.jdbc.Driver"); // This will load the MySQL driver, each DB has its own driver
        return DriverManager.getConnection("jdbc:mysql://localhost/englishpractice?user=root&password="); // Setup the connection with the DB
    }

    public void executeUpdateQuery(String query) throws SQLException, Exception {
        try (Connection connection = connectDB(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {//Las variables que se declaran aquí sirven dentro del try. Una vez ejecutado lo de adentro del try el cierra esos recursos(conexión,preparedStatement) que se usaron. 
            preparedStatement.executeUpdate();
        }
    }

    public ArrayList<Object[]> executeSelectQuery(String query) throws SQLException, Exception {
        try (Connection connection = connectDB(); PreparedStatement preparedStatement = connection.prepareStatement(query); ResultSet resultSet = preparedStatement.executeQuery()) { //Las variables que se declaran aquí sirven dentro del try. Una vez ejecutado lo de adentro del try el cierra esos recursos(conexión,preparedStatement,resultset) que se usaron

            int numCol = resultSet.getMetaData().getColumnCount();
            ArrayList data = new ArrayList();

            while (resultSet.next()) {
                Object[] obj = new Object[numCol];
                int i = 0;
                while (i < numCol) {
                    obj[i] = resultSet.getObject(i + 1);
                    i++;
                }
                data.add(obj);
            }

            return data;
        }
    }
}
