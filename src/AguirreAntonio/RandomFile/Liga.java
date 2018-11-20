package AguirreAntonio.RandomFile;




import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Liga {
    //Clases para utilizar json
    JSONObject jsonObject;
    public JSONParser jsonParser = new JSONParser();
    //Separador de carpetas o archivos
    private static final String FS = File.separator;
    //Dejamos abierto el scaner para leer datos del usuario
    private Scanner sc = new Scanner(System.in);
    //Datos que necesita la clase
    String team1, team2;
    String referee;
    String patrocinador, temporada;
    //Tamaño estipulado
    private static final int SIZE_NAME_REFEREE = 30;
    private static final int SIZE_NAME_REFEREE_JP = 120;


    public Liga() throws ParseException {
        try {
            ConfigurationLigue();
        } catch (IOException e) {
            e.printStackTrace();
        }
        menu();
    }

    public void menu(){
        System.out.println(jsonObject.get("title"));
        System.out.println(jsonObject.get("main"));
        try {
            byte option = sc.nextByte();
            switch (option) {
                case 1:
                    CreateLeague();
                    break;
                case 2:
                    SeeResults();
                    break;
                case 3:

                    break;

            }

        }catch (InputMismatchException ime){
            System.err.println("Carácter no esperado o numero demasiado grande. - InputMismatchException");
        }
    }

    private void CreateLeague() {

        team1= (String)jsonObject.get("team") +" 1";
        team2= (String)jsonObject.get("team") +" 2";
        System.out.println(jsonObject.get("referee"));

        //TODO Mejorar
            do {
                referee = sc.nextLine();
                }
                while ( referee.length() == 0 || referee.trim().equals(" ")|| referee.length() >= SIZE_NAME_REFEREE) ;


    }

    private void SeeResults() {
    }

    private void ConfigurationLigue() throws IOException, ParseException {
        //Añadimos
        jsonObject= (JSONObject)jsonParser.parse(new InputStreamReader(new FileInputStream("Data"+FS+"resource"+ FS+"language"+FS+"spanish.json" )));


    }

}
