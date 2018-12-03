package AguirreAntonio.Postgres;


import AguirreAntonio.ahelp.Constantes;
import AguirreAntonio.ahelp.JSONhlp;
import AguirreAntonio.ahelp.PostgresBasics;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JavaPostgreSQLBasic implements Constantes, PostgresBasics {
    private static Connection con = null;
    private static Statement st = null;


    JavaPostgreSQLBasic(){
        try {
            JSONhlp.configParser("postgres","conexion.json");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static String ConnectToPostgresDataBase() throws SQLException {
        con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);
        return ""+JSONhlp.jsonObject.get("ConnectExit");
    }
    public static boolean CheckDB(String namedb) throws SQLException {
        st = con.createStatement();
        //Probar esta sentencia real
        st.execute("SELECT datname FROM pg_database");
        return false;
    }
    public static String CreateDataBase(String name) throws SQLException{
        st = con.createStatement();
        st.executeUpdate(Createdb+name);
        CloseAll();
        return ""+JSONhlp.jsonObject.get("CreateDatabase");
    }
    public static String DropDataBase(String name) throws SQLException{
        st = con.createStatement();
        st.executeUpdate(Dropdb+name);
        return ""+JSONhlp.jsonObject.get("DropDB");

    }
    public static void CloseAll() throws SQLException{
        st.close();
        con.close();
    }

}