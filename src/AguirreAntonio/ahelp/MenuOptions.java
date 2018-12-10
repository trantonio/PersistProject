package AguirreAntonio.ahelp;

import AguirreAntonio.Postgres.JavaPostgreSQLBasic;
import AguirreAntonio.Postgres.ReadDB;
import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class MenuOptions {

    static Scanner sc = new Scanner(System.in);
    public static int Menup(int numOptions) throws IOException, ParseException {
        //Creamos la configuracion para json
        JSONhlp.configParser("postgres","conexion");
        for(int i = 0; i<=numOptions;i++){
            System.out.println("\t"+i+".- "+JSONhlp.jsonObject.get("Option"+i));
        }
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }

    public static void Menu(int option) throws IOException, SQLException, ParserConfigurationException, SAXException {
        String name;
        switch (option){
            case 0:

                break;
            case 1:
                System.out.println(JSONhlp.jsonObject.get("O1name"));
                name = sc.nextLine();
                System.out.println(JavaPostgreSQLBasic.createDataBase(name));

                break;
            case 2:
                System.out.println(JSONhlp.jsonObject.get("O2name"));
                name = sc.nextLine();
                System.out.println(JavaPostgreSQLBasic.dropDataBase(name));
                break;
            case 3:
                System.out.println(JSONhlp.jsonObject.get("O3name"));
                name = sc.nextLine();
                System.out.println(JavaPostgreSQLBasic.checkDB(name));
                break;
            case 4:
                System.out.println(JavaPostgreSQLBasic.returnDataBases());

                break;
            case 6:
                ReadDB mr = new ReadDB("Data/resource/world.xml");

                System.out.println("Antonio Aguirre\n"+mr.cityWithSea("Spain"));
                break;
            case 50:

                System.out.println("Salimos!");
                System.exit(1);
                break;
            default:
                System.out.println(JSONhlp.jsonObject.get("NoOption"));
                break;
        }



    }

}
