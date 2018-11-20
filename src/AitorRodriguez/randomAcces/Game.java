package AitorRodriguez.randomAcces;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;

public class Game {
    private short pTeam1;
    private short pTeam2;
    private String refereeName;
    private String date;

    private String language;
    public final static String fs = System.getProperty("file.separator");

    public Game(short pTeam1, short pTeam2, String refereeName, String date) throws Exception {
        if (pTeam1>200 || pTeam2>200){
            throw new Exception();
        } else {
            this.pTeam1 = pTeam1;
            this.pTeam2 = pTeam2;
        }
        if (refereeName.length()>30){
            throw new Exception();
        } else {
            this.refereeName = refereeName;
        }
        if (date.length()>6){
            throw new Exception();
        } else {
            this.date = date;
        }
    }

    public Game(short pTeam1, short pTeam2, String refereeName, String date, String language) {
        this.pTeam1 = pTeam1;
        this.pTeam2 = pTeam2;
        this.refereeName = refereeName;
        this.date = date;
        this.language = language;
    }

    public void write(RandomAccessFile randomAccessFile) throws IOException {
        randomAccessFile.writeShort(this.pTeam1);
        randomAccessFile.writeShort(this.pTeam2);
        randomAccessFile.writeUTF(this.refereeName);
        randomAccessFile.writeUTF(this.date);
    }

    @Override
    public String toString() {
        StringBuilder print = new StringBuilder();

        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new InputStreamReader(new FileInputStream("sources"+fs+"lang"+fs + language + ".json")));
            JSONObject jsonObject = (JSONObject) obj;

            print.append(jsonObject.get("pTeam1") + String.valueOf(pTeam1) + "\n");
            print.append(jsonObject.get("pTeam2") + String.valueOf(pTeam2) + "\n");
            print.append(jsonObject.get("refereeName") + String.valueOf(refereeName) + "\n");
            print.append(jsonObject.get("date") + String.valueOf(date) + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return print.toString();
    }
}
