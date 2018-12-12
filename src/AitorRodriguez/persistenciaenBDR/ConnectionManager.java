package AitorRodriguez.persistenciaenBDR;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConnectionManager {
    private Connection con;
    private String url;
    private String user;
    private String pass;

    /**
     * Load Driver for Postgresql database. Only works on Postrgresql database.
     *
     * @throws ClassNotFoundException
     */
    public ConnectionManager() throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        System.out.println("Load Driver = OK");
    }

    public void connect(String url, String user, String password) {
        try {
            con = DriverManager.getConnection(url, user, password);
            this.url = url;
            this.user = user;
            this.pass = password;
            System.out.println("Connection = OK \nWaiting queries...\n");
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public boolean insertCountry(String countryName) {
        String insert = "INSERT INTO country(name) VALUES(?)";
        try {
            PreparedStatement ps = con.prepareStatement(insert);
            ps.setString(1, countryName);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean insertCity(String country, String city) throws SQLException {
        String select = "SELECT * \n" +
                "FROM country \n" +
                "WHERE name = ?";
        PreparedStatement statement = con.prepareStatement(select);
        statement.setString(1, country);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            //System.out.println(rs.getInt(1)+" - "+rs.getString(2)+" = "+city);
            String insert = "INSERT INTO city(country_id, name) \n" +
                    "VALUES((SELECT country_id " +
                    "FROM country " +
                    "WHERE name = ?), ?)";
            statement = con.prepareStatement(insert);
            statement.setString(1, country);
            statement.setString(2, city);
            statement.executeUpdate();
        }
        statement.close();
        return true;
    }

    public List<String> listCountryCities(String country) throws SQLException {
        ArrayList<String> out = new ArrayList<>();
        Statement statement = con.createStatement();
        String select = "select * from city where country_id = 9";
        ResultSet rs = statement.executeQuery(select);
        while (rs.next()) {
            out.add(rs.getInt(1) + " - " + rs.getInt(2) + " - " + rs.getString(3));
        }
        statement.close();
        return out;
    }

    public boolean createTables() {
        Statement statement = null;
        String query = null;

        try {
            statement = con.createStatement();
            query = "CREATE TABLE IF NOT EXISTS country (" +
                    "country_id SERIAL PRIMARY KEY, \n" +
                    "name VARCHAR(40) NOT NULL)";
            statement.executeUpdate(query);
            System.out.println("Create table country = OK");

            query = "CREATE TABLE IF NOT EXISTS city (" +
                    "city_id SERIAL PRIMARY KEY, \n" +
                    "country_id integer, \n" +
                    "name VARCHAR(70) NOT NULL, \n" +
                    "CONSTRAINT fk_city_country FOREIGN KEY (country_id) REFERENCES country (country_id))";
            statement.executeUpdate(query);
            statement.close();
            System.out.println("Create table country = OK");
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            return false;
        }

        return true;
    }

    public boolean createTableCity() {
        Statement statement = null;
        String query = null;

        try {
            statement = con.createStatement();
            query = "CREATE TABLE IF NOT EXISTS city (" +
                    "city_id SERIAL PRIMARY KEY, \n" +
                    "country_id integer, \n" +
                    "name VARCHAR(70) NOT NULL, \n" +
                    "CONSTRAINT fk_city_country FOREIGN KEY (country_id) REFERENCES country (country_id))";
            statement.executeUpdate(query);
            System.out.println("Create table country = OK");
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            return false;
        }

        return true;
    }

    public void disconnect() {
        try {
            con.close();
            System.out.println("\n\nDisconnection = OK");
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}