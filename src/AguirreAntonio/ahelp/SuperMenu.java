package AguirreAntonio.ahelp;

import AguirreAntonio.Postgres.JavaPostgreSQLBasic;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class SuperMenu {

    static Scanner sc = new Scanner(System.in);
    public SuperMenu(String nombreCarpeta1, String nombreCarpeta2,int numOptions) throws IOException, ParseException, SQLException {
        //Creamos la configuracion para json
        JSONhlp.configParser(nombreCarpeta1,nombreCarpeta2);
        for(int i = 0; i<=numOptions;i++){
            System.out.println("\t"+i+".- "+JSONhlp.jsonObject.get("Option"+i));
        }
        Scanner sc = new Scanner(System.in);
        do {
            Menu(sc.nextInt());
        }while(sc.nextInt()==50);
    }
    public static void Menu(int option) throws IOException, SQLException {
        String name;

            switch (option) {
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
