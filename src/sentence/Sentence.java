package sentence;

import database.DBConnection;
import java.sql.SQLException;
import java.util.ArrayList;

public class Sentence {

    private final DBConnection dBConnection;
    public int id;
    public String english;
    public String spanish;
    public boolean active;
    public int categoryId;

    public Sentence(int id, String english, String spanish, boolean active, int categoryId) throws Exception {
        this.id = id;
        this.english = english;
        this.spanish = spanish;
        this.active = active;
        this.categoryId = categoryId;
        this.dBConnection = new DBConnection();
    }

    public Sentence() throws Exception {
        this.dBConnection = new DBConnection();
    }

    public ArrayList<Sentence> fillTable(int catId) throws SQLException, Exception {
        ArrayList<Object[]> data = dBConnection.executeSelectQuery("SELECT id,english,spanish,active,category_id FROM sentence WHERE active AND category_id=" + catId);
        ArrayList<Sentence> sentns = new ArrayList();

        for (int i = 0; i < data.size(); i++) {
            sentns.add(new Sentence((int) data.get(i)[0], (String) data.get(i)[1], (String) data.get(i)[2], (int) data.get(i)[3]==1, (int) data.get(i)[4]));
        }
        return sentns;
    }

    public Sentence select(int id) throws SQLException, Exception {
        ArrayList<Object[]> data = dBConnection.executeSelectQuery("SELECT id,english,spanish,active,category_id FROM sentence WHERE id=" + id);
        return new Sentence((int) data.get(0)[0], (String) data.get(0)[1], (String) data.get(0)[2], (int) data.get(0)[3]==1, (int) data.get(0)[4]);
    }

    public void insert(Sentence s) throws SQLException, Exception {
        dBConnection.executeUpdateQuery("INSERT INTO sentence(english,spanish,category_id) VALUES ('" + s.english + "','" + s.spanish + "'," + s.categoryId + ")");
    }

    public void update(Sentence s) throws SQLException, Exception {
        dBConnection.executeUpdateQuery("UPDATE sentence SET english='" + s.english + "',spanish='" + s.spanish + "' WHERE id=" + s.id);
    }

    public void delete(int id) throws SQLException, Exception {
        dBConnection.executeUpdateQuery("UPDATE sentence SET active=false WHERE id=" + id);
    }
}
