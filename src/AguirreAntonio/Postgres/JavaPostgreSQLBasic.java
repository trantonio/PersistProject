package AguirreAntonio.Postgres;


import AguirreAntonio.ahelp.Constantes;
import AguirreAntonio.ahelp.JSONhlp;
import AguirreAntonio.ahelp.PostgresBasics;
import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class JavaPostgreSQLBasic implements Constantes, PostgresBasics {
    private static Connection con = null;
    private static Statement st = null;

    private String query;
    static String dbName;
    static Properties properties = new Properties();
    public JavaPostgreSQLBasic(){
        try {
            JSONhlp.configParser("postgres","conexion");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static String connectToPostgresDataBase() throws SQLException, IOException {
        File propertiesFile = new File("postgres.properties");
        if (!propertiesFile.exists()) {
            setupDB();
        } else {
            InputStream input = new FileInputStream("postgres.properties");
            properties.load(input);
            dbName = properties.getProperty("dataBaseName");
        }
        con = DriverManager.getConnection(DB_URL+dbName, DB_USER, DB_PASSWD);
        return ""+JSONhlp.jsonObject.get("ConnectExit");
    }
    public static void setupDB() throws IOException {
        Scanner scDB = new Scanner(System.in);
        System.out.println("Indica la base de datos para iniciar sesion --->");
        dbName = scDB.nextLine();
        properties.setProperty("dataBaseName", dbName);
        properties.store(new FileOutputStream("postgres.properties"), null);
    }
    public static void changeDB() throws SQLException, IOException {
        con.close();
        setupDB();
        con = DriverManager.getConnection(DB_URL+dbName, DB_USER, DB_PASSWD);
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
            return "--->" + JSONhlp.jsonObject.get("CreateDatabase");
        }else{
            return "--->"+ JSONhlp.jsonObject.get("DataBaseExists");
        }
    }
    public static String dropDataBase(String name) throws SQLException{
        if(checkDB(name)) {
            st = con.createStatement();
            st.executeUpdate(Dropdb + name);
            return "---> " + JSONhlp.jsonObject.get("DropDB");
        }else{
            return "--->" + JSONhlp.jsonObject.get("DataBaseNoExists");
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
    public String createTableCountry() throws SQLException {
        st = con.createStatement();
        query = "CREATE TABLE IF NOT EXISTS Country (" +
                    "Country_id SERIAL PRIMARY KEY, \n" +
                    "CountryName VARCHAR(40) NOT NULL)";
            st.addBatch(query);
            st.executeBatch();
        return ""+JSONhlp.jsonObject.get("CreateTableOK");
    }
    public String statment(String statement) throws SQLException {
        st = con.createStatement();
        query = statement;
        st.addBatch(query);
        st.executeBatch();
        return "Statement - OK";
    }
    public String seeTable(String nameTable) throws SQLException {
        st = con.createStatement();
        ResultSet rs =st.executeQuery("SELECT * FROM "+nameTable);
        while(rs.next()){
            System.out.println(rs.getString(1));
        }
        return "Exito de "+ nameTable;
    }
    public void countryOnPostgres() throws Exception {
        ReadDB mr = new ReadDB("Data/resource/world.xml");
        for (int i = 0;i<mr.Country().size();i++){
            int a= 1+i;
            String insert = "INSERT INTO country(Country_id,CountryName) VALUES(?,?)";
            try {
                PreparedStatement ps = con.prepareStatement(insert);
                ps.setInt(1,a);
                ps.setString(2, mr.Country().get(i));
                ps.executeUpdate();
            } catch (SQLException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
        System.out.println(mr.Country().get(0));
        System.out.println(mr.Country().size());
        System.out.println(mr.Country());
    }
    public static void closeAll() throws SQLException{
         st.close();
        con.close();
        System.out.println(JSONhlp.jsonObject.get("Disconect"));
    }

}