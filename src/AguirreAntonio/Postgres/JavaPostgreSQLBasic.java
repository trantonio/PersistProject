package AguirreAntonio.Postgres;


import AguirreAntonio.ahelp.Constantes;
import AguirreAntonio.ahelp.JSONhlp;
import AguirreAntonio.ahelp.PostgresBasics;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.sql.*;

public class JavaPostgreSQLBasic implements Constantes, PostgresBasics {
    private static Connection con = null;
    private static Statement st = null;


    JavaPostgreSQLBasic(){
        try {
            JSONhlp.configParser("postgres","conexion");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static String connectToPostgresDataBase() throws SQLException {
        con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);
        return ""+JSONhlp.jsonObject.get("ConnectExit");
    }
    public static boolean checkDB(String namedb) throws SQLException {
        st = con.createStatement();
        //Probar esta sentencia real

        ResultSet rs =st.executeQuery("SELECT datname FROM pg_database");
        while(rs.next()) {
            //Devuelve el parametro de la primera columna en texto
            rs.getString(1);
            if(rs.getString(1).equals(namedb)) {
                return true;
            }
        }
        return false;
    }
    public static String returnDataBases(){

        return "";
    }
    public static String createDataBase(String name) throws SQLException{
        st = con.createStatement();
        st.executeUpdate(Createdb+name);
        CloseAll();
        return "-->"+JSONhlp.jsonObject.get("CreateDatabase");
    }
    public static String dropDataBase(String name) throws SQLException{
        st = con.createStatement();
        st.executeUpdate(Dropdb+name);
        return "--> "+JSONhlp.jsonObject.get("DropDB");

    }
    public static void CloseAll() throws SQLException{
        st.close();
        con.close();
    }

}