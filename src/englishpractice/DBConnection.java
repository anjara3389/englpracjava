package englishpractice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBConnection {

    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public void connectDB() throws Exception {
        Class.forName("com.mysql.jdbc.Driver"); // This will load the MySQL driver, each DB has its own driver
        this.connection = DriverManager.getConnection("jdbc:mysql://localhost/englishpractice?user=root&password="); // Setup the connection with the DB
    }

    public void close() throws SQLException {
        if (this.connection != null) {
            this.connection.close();
        }
        if (this.preparedStatement != null) {
            this.preparedStatement.close();
        }
        if (this.resultSet != null) {
            this.resultSet.close();
        }
    }

    public void executeUpdateQuery(String query) throws SQLException, Exception {
        try {
            connectDB();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
        } finally {
            close();
        }
    }

    public ArrayList<Object[]> executeSelectQuery(String query) throws SQLException, Exception {
        connectDB();
        preparedStatement = connection.prepareStatement(query);

        resultSet = preparedStatement.executeQuery();
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
        close();
        return data;
    }
}
