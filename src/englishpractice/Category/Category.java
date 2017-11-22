package englishpractice.Category;

import englishpractice.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Category {

    private ResultSet resultSet = null;
    private DBConnection dBConnection;

    public int id;
    public String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Category(int id, String name) throws Exception {
        this.id = id;
        this.name = name;
        dBConnection = new DBConnection();
        dBConnection.readDataBase();
    }

    public Category() throws Exception {
        dBConnection = new DBConnection();
        dBConnection.readDataBase();
    }

    public ArrayList<Category> fillTable() throws SQLException, Exception {
        ArrayList<Category> categs = new ArrayList();
        resultSet = dBConnection.executeSelectQuery("SELECT id,name FROM category");
        while (resultSet.next()) {
            Category categ = new Category(resultSet.getInt("id"), resultSet.getString("name"));
            categs.add(categ);
        }
        return categs;
    }

    public Category select(int id) throws SQLException, Exception {
        resultSet = dBConnection.executeSelectQuery("SELECT id,name FROM category WHERE id=" + id);
        if (resultSet.first()) {
            return new Category(resultSet.getInt("id"), resultSet.getString("name"));
        }
        return null;
    }

    public void insert(Category c) throws SQLException {
        dBConnection.executeUpdateQuery("INSERT INTO category(name) VALUES ('" + c.name + "')");
    }

    public void update(Category c) throws SQLException {
        dBConnection.executeUpdateQuery("UPDATE category SET name='" + c.name + "' WHERE id=" + c.id);
    }

    public void delete(int id) throws SQLException {
        dBConnection.executeUpdateQuery("DELETE FROM category WHERE id=" + id);
    }
}
