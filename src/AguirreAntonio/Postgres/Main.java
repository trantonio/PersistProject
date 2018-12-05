package AguirreAntonio.Postgres;

import AguirreAntonio.ahelp.JSONhlp;
import AguirreAntonio.ahelp.MenuOptions;

import java.io.IOException;
import java.sql.SQLException;
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
//        if(!JavaPostgreSQLBasic.checkDB("java")){
//        System.out.println(JavaPostgreSQLBasic.createDataBase("java"));
//        }else{
//            System.out.println(JavaPostgreSQLBasic.dropDataBase("java"));
//        }
        int numberOptions = 3;
        do {
            MenuOptions.Menu(MenuOptions.Menup(numberOptions));
        }while (MenuOptions.Menup(numberOptions)==50);
//        Menu(MenuOptions.Menup(3));
        JavaPostgreSQLBasic.CloseAll();
    }
//        public static void Menu (int option) throws IOException, SQLException {
//        String name;
//            switch (option){
//                case 0:
//
//                    break;
//                case 1:
//                    System.out.println(JSONhlp.jsonObject.get("O1name"));
//                    name = sc.nextLine();
//                    System.out.println(JavaPostgreSQLBasic.createDataBase(name));
//
//                    break;
//                case 2:
//                    System.out.println(JSONhlp.jsonObject.get("O2name"));
//                    name = sc.nextLine();
//                    System.out.println(JavaPostgreSQLBasic.dropDataBase(name));
//                    break;
//                case 3:
//                    System.out.println(JSONhlp.jsonObject.get("O3name"));
//                    name = sc.nextLine();
//                    System.out.println(JavaPostgreSQLBasic.checkDB(name));
//                    break;
//                default:
//                    System.out.println(JSONhlp.jsonObject.get("NoOption"));
//                    break;
//            }
//
//
//
//    }

}
