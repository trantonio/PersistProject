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
        new JavaPostgreSQLBasic();

        System.out.println(JavaPostgreSQLBasic.ConnectToPostgresDataBase());
        JavaPostgreSQLBasic.CheckDB("java");
//        System.out.println(JavaPostgreSQLBasic.CreateDataBase("java"));
//        System.out.println(JavaPostgreSQLBasic.DropDataBase("java"));

        JavaPostgreSQLBasic.CloseAll();
    }
}
