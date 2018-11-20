package AguirreAntonio.Postgres;

import java.sql.SQLException;

public class Main {
    /**
     * Testing Java PostgreSQL connection with host and port
     * Probando la conexión en Java a PostgreSQL especificando el host y el puerto.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        JavaPostgreSQLBasic j= new JavaPostgreSQLBasic();
            j.ConnectToPostgresDataBase();
            j.CreateDataBase("java");

            j.CloseAll();

    }
}
