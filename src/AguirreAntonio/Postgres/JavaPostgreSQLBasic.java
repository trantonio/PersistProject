package AguirreAntonio.Postgres;


import AguirreAntonio.ahelp.Constantes;
import AguirreAntonio.ahelp.JSONhlp;
import AguirreAntonio.ahelp.PostgresBasics;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class JavaPostgreSQLBasic implements Constantes, PostgresBasics {
    private static Connection con = null;
    private static Statement st = null;

    private String query;


    public JavaPostgreSQLBasic(){
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
    public void testPostgres(String select) throws SQLException {
        st = con.createStatement();
        ResultSet rs =st.executeQuery(select);
        while(rs.next()){
            System.out.println(rs.getString(1));
        }
    }
    public static String returnDataBases() throws SQLException {
        st = con.createStatement();
        ResultSet rs =st.executeQuery("SELECT datname FROM pg_database");
        while(rs.next()){
            System.out.println(rs.getString(1));
        }
        return "\n--------------------";
    }
    public static String createDataBase(String name) throws SQLException{
        if(!checkDB(name)) {
            st = con.createStatement();
            st.executeUpdate(Createdb + name);
            return "-->" + JSONhlp.jsonObject.get("CreateDatabase");
        }else{
            return "-->"+ JSONhlp.jsonObject.get("DataBaseExists");
        }
    }
    public static String dropDataBase(String name) throws SQLException{
        if(checkDB(name)) {
            st = con.createStatement();
            st.executeUpdate(Dropdb + name);
            return "--> " + JSONhlp.jsonObject.get("DropDB");
        }else{
            return "-->" + JSONhlp.jsonObject.get("DataBaseNoExists");
        }
    }

    /*
    CREATE TABLE SOCI
       (CODSOCI smallint CONSTRAINT SOCI_CODSOCI_PK PRIMARY KEY ,
        DNI varchar(9) ,
        NOM   varchar(15) CONSTRAINT SOCI_NOMSOCI_NN NOT NULL,
        COGNOMS varchar(20) CONSTRAINT SOCI_COGNOMS_NN NOT NULL,
    	DATA_NAIXEMENT DATE,
        ADRECA varchar(50) CONSTRAINT SOCI_DIRSOCI_NN NOT NULL,
        TELEFON varchar(9) CONSTRAINT SOCI_TELFSOCI_NN NOT NULL,
		SEXE varchar(1)
);
     */
    public String createTable(String nameTable, String[] args) throws SQLException {
        st = con.createStatement();
        query = "CREATE TABLE IF NOT EXISTS city (" +
                    "city_id SERIAL PRIMARY KEY, \n" +
                    "country_id integer, \n" +
                    "cityName VARCHAR(70) NOT NULL)";
            st.addBatch(query);
            st.executeBatch();
        return "Create Table Exit";
    }
    public String statment(String statement) throws SQLException {
        st = con.createStatement();
        query = statement;
        st.addBatch(query);
        st.executeBatch();
        return "Statement exit!";
    }
    public String SeeTable(String nameTable) throws SQLException {
        st = con.createStatement();
        ResultSet rs =st.executeQuery("SELECT * FROM "+nameTable);
        while(rs.next()){
            System.out.println(rs.getString(1));
        }
        return "Exito de "+ nameTable;
    }
    public static void CloseAll() throws SQLException{
         st.close();
        con.close();
    }

}