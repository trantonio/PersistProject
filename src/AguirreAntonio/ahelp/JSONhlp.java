package AguirreAntonio.ahelp;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class JSONhlp implements Constantes{

    public static JSONObject jsonObject;
    public static JSONParser parser = new JSONParser();

    public static void configParser(String n1,String n2) throws IOException, ParseException {
        Object obj = parser.parse(new InputStreamReader(new FileInputStream("Data/resource" + FS + n1 + FS  + n2)));
        jsonObject = (JSONObject) obj;
    }
}
