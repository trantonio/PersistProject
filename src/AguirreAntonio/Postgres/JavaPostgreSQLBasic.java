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
    Connection con = null;
    Statement st = null;


    JavaPostgreSQLBasic(){
        try {
            JSONhlp.configParser("postgres","conexion.json");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void ConnectToPostgresDataBase() throws SQLException {
        con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);
        System.out.println(JSONhlp.jsonObject.get("ConectExit"));
    }
    public void CreateDataBase(String name) throws SQLException{
        st = con.createStatement();
        st.executeUpdate(Createdb+name);
        System.out.println(JSONhlp.jsonObject.get("CreateDatabase"));
        CloseAll();
    }
    public void DropDataBase(String name) throws SQLException{
        st = con.createStatement();
        st.executeUpdate(Dropdb+name);

    }
    public void CloseAll() throws SQLException{
        st.close();
        con.close();
    }

}