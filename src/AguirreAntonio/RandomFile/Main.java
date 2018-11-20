package AguirreAntonio.RandomFile;

import org.json.simple.parser.ParseException;

public class Main {
    public static void main(String[] args) {
        try {
            Liga l = new Liga();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
