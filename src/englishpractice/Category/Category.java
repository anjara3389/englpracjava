package englishpractice.Category;

import englishpractice.DBConnection;
import java.sql.SQLException;
import java.util.ArrayList;

public class Category {

    private final DBConnection dBConnection;
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
        this.dBConnection = new DBConnection();
    }

    public Category() throws Exception {
        this.dBConnection = new DBConnection();
    }

    public ArrayList<Category> fillTable() throws SQLException, Exception {
        ArrayList<Object[]> data = dBConnection.executeSelectQuery("SELECT id,name FROM category");
        ArrayList<Category> categs = new ArrayList();

        for (int i = 0; i < data.size(); i++) {
            categs.add(new Category((int) data.get(i)[0], (String) data.get(i)[1]));
        }
        return categs;
    }

    public Category select(int id) throws SQLException, Exception {
        ArrayList<Object[]> data = dBConnection.executeSelectQuery("SELECT id,name FROM category WHERE id=" + id);
        return new Category((int) data.get(0)[0], (String) data.get(0)[1]);
    }

    public void insert(Category c) throws SQLException, Exception {
        if (!isValidated(c)) {
            throw new Exception("Ya exíste una categoría con ese nombre");
        }
        dBConnection.executeUpdateQuery("INSERT INTO category(name) VALUES ('" + c.name + "')");
    }

    public void update(Category c) throws SQLException, Exception {
        if (!isValidated(c)) {
            throw new Exception("Ya exíste una categoría con ese nombre");
        }

        dBConnection.executeUpdateQuery("UPDATE category SET name='" + c.name + "' WHERE id=" + c.id);
    }

    public void delete(int id) throws SQLException, Exception {
        dBConnection.executeUpdateQuery("DELETE FROM category WHERE id=" + id);
    }

    public boolean isValidated(Category c) throws Exception {
        ArrayList<Object[]> data = dBConnection.executeSelectQuery("SELECT COUNT(*)>0 FROM category WHERE name like '" + c.name + "' AND id<>" + c.id);
        return (Long) data.get(0)[0] == 0;
    }
}
