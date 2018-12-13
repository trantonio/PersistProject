package AguirreAntonio.Postgres;

import AguirreAntonio.ahelp.JSONhlp;
import AguirreAntonio.ahelp.MenuOptions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    /**
     * Testing Java PostgreSQL connection with host and port
     * Probando la conexión en Java a PostgreSQL especificando el host y el puerto.
     *
     * @param args the command line arguments
     */
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws Exception {


        new JavaPostgreSQLBasic();
        System.out.println(JavaPostgreSQLBasic.connectToPostgresDataBase());

        int numberOptions = 6;
        int numOptions = 0;
        do {
            numOptions = MenuOptions.Menup(numberOptions);
            MenuOptions.Menu(numOptions);
        }while (numOptions!=50);


        JavaPostgreSQLBasic.CloseAll();
    }


}
