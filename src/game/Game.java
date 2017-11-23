package game;

import game.*;
import database.DBConnection;
import java.sql.SQLException;
import java.util.ArrayList;

public class Game {

    private final DBConnection dBConnection;
    public int id;
    public int num_phrases;

    public boolean active;
    public int categoryId;

    public Game(int id, int num_phrases, int categoryId) throws Exception {
        this.id = id;
        this.num_phrases = num_phrases;
        this.categoryId = categoryId;
        this.dBConnection = new DBConnection();
    }

    public Game() throws Exception {
        this.dBConnection = new DBConnection();
    }

    public Game select(int id) throws SQLException, Exception {
        ArrayList<Object[]> data = dBConnection.executeSelectQuery("SELECT id,num_phrases,category_id FROM game WHERE id=" + id);
        return new Game((int) data.get(0)[0], (int) data.get(0)[1], (int) data.get(0)[2]);
    }

    public void insert(Game s) throws SQLException, Exception {
        dBConnection.executeUpdateQuery("INSERT INTO game(num_phrases,category_id) VALUES (" + s.num_phrases + "," + s.categoryId + ")");
    }
}
