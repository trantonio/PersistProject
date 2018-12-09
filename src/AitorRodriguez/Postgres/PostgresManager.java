package AitorRodriguez.Postgres;

import java.sql.*;

public class PostgresManager {

//        private Connection con;
//        private Statement statement;
//
//        private String query;
//        private final int BATCHSIZE = 60;
//
//        public PostgresManager(String url, String user, String pass) throws SQLException {
//            connect(url, user, pass);
//        }
//
//        private void connect(String url, String user, String password) throws SQLException {
//            con = DriverManager.getConnection(url, user, password);
//            System.out.println("Auto Driver(Postgresql) => OK");
//            System.out.println("Connection => OK\n");
//        }
//
//        public void fillDataBase(List<Country> world) throws SQLException {
//            createTables();
//
//            query = "INSERT INTO country(carCode, countryName) \n" +
//                    "VALUES(?, ?)";
//            PreparedStatement psCountry = con.prepareStatement(query);
//
//            query = "INSERT INTO city(country_id, cityName) \n" +
//                    "VALUES((SELECT country_id \n" +
//                    "FROM country \n" +
//                    "WHERE countryName = ?), ?)";
//            PreparedStatement psCity = con.prepareStatement(query);
//
//            fillCountryTable(world, psCountry, psCity);
//        }
//
//        private void fillCountryTable(List<Country> world, PreparedStatement psCountry, PreparedStatement psCity) throws SQLException {
//            int count = 0;
//            statement = con.createStatement();
//            for (Country country : world) {
//                psCountry.setString(1, country.getCarCode());
//                psCountry.setString(2, country.getCountryName());
//                psCountry.addBatch();
//                if (++count % BATCHSIZE == 0) {
//                    psCountry.executeBatch();
//                }
//            }
//            psCountry.executeBatch();
//            psCountry.close();
//            statement.close();
//
//            fillCityTable(world, psCity);
//        }
//
//        private void fillCityTable(List<Country> world, PreparedStatement psCity) throws SQLException {
//            int count = 0;
//            statement = con.createStatement();
//            for (Country country : world) {
//                for (City city : country.getCities()) {
//                    psCity.setString(1, country.getCountryName());
//                    psCity.setString(2, city.getCityName());
//                    psCity.addBatch();
//                    if (++count % BATCHSIZE == 0) {
//                        psCity.executeBatch();
//                    }
//                }
//            }
//            psCity.executeBatch();
//            psCity.close();
//            statement.close();
//        }
//
//        private void createTables() throws SQLException {
//            statement = con.createStatement();
//
//            query = "  " +
//                    "country_id SERIAL PRIMARY KEY, \n" +
//                    "carCode VARCHAR(5) NOT NULL UNIQUE, \n" +
//                    "countryName VARCHAR(35) NOT NULL)";
//            statement.addBatch(query);
//
//            query = "CREATE TABLE IF NOT EXISTS city (" +
//                    "city_id SERIAL PRIMARY KEY, \n" +
//                    "country_id integer, \n" +
//                    "cityName VARCHAR(70) NOT NULL, \n" +
//                    "CONSTRAINT fk_city_country FOREIGN KEY (country_id) REFERENCES country (country_id))";
//            statement.addBatch(query);
//            statement.executeBatch();
//
//            statement.close();
//        }
//
//        public List<Country> selectWorld() throws SQLException {
//            List<Country> world = new ArrayList<>();
//
//            statement = con.createStatement();
//            query = "SELECT * FROM country";
//            ResultSet rs = statement.executeQuery(query);
//            while (rs.next()) {
//                Country country = new Country(rs.getString(2), rs.getString(3));
//
//                query = "SELECT cityName FROM city WHERE country_id = ?";
//                PreparedStatement ps = con.prepareStatement(query);
//                ps.setInt(1, rs.getInt(1));
//                ResultSet cities = ps.executeQuery();
}
