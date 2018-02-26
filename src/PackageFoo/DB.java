package PackageFoo;

import java.sql.*;

public class DB {
public String PATH;

    public void setPATH(String PATH) {
        this.PATH = PATH;
    }

    public String getPATH() {
        return PATH;
    }

    public Connection ConnectionDB(String path) {
        //System.out.println(path);
        String DatabaseName = path + "Library.db";
        setPATH(path);

        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" + DatabaseName);
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return c;
    }

    public Connection ConnectionDB() {

        String path = getPATH();
        String DatabaseName = path + "Library.db";

        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" + DatabaseName);
            } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return c;
    }

    public void CreateBOOKTable(){

        Statement stmt = null;

        try {
            Connection c = ConnectionDB();

            stmt = c.createStatement();
            //todo: remember to FK this
            String sql = "CREATE TABLE IF NOT EXISTS BOOK " +
                    "(BOOKID         INTEGER   PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    " TITLE          VARCHAR(45)                         NOT NULL, " +
                    " AUTHOR         VARCHAR(45)                         NULL, " +
                    " ISBN           INT                                 NULL, " +
                    " CREATED_AT     DATETIME  DEFAULT CURRENT_TIMESTAMP NOT NULL, " +
                    " UPDATED_AT     DATETIME  DEFAULT CURRENT_TIMESTAMP NOT NULL  " +
                    ")";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        //debug
        //System.out.println("BOOK Table created successfully");
    }
}
