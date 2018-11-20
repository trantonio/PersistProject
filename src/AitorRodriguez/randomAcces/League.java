package AitorRodriguez.randomAcces;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class League {
    private static final int NUM_TEAMS = 10;
    private static final int REFEREE_NAME_SIZE = 90;
    private static final int RESULTS_SIZE = 2;
    private static final int DATA_SIZE = 6;
    private static final int TOTAL_SIZE = (RESULTS_SIZE * 2) + REFEREE_NAME_SIZE + DATA_SIZE + 2;
    private static final int DIAGONAL = -2;
    private static final int NOT_PLAYED = -1;
    private static final int MAX_NUM_BACKUPS = 2;
    public static final String FS = System.getProperty("file.separator");

    public File path;
    public JSONParser parser = new JSONParser();
    public JSONObject jsonObject;
    Properties properties = new Properties();
    private Scanner sc = new Scanner(System.in);
    public RandomAccessFile raf;

    public String language;
    public String season;
    private String sponsor;

    public League() throws Exception {
        File propertiesFile = new File("league.properties");
        if (!propertiesFile.exists()) {
            setup();
        } else {
            InputStream input = new FileInputStream("league.properties");
            properties.load(input);
            language = properties.getProperty("language");
            season = properties.getProperty("season");
            sponsor = properties.getProperty("sponsor");
            configParser();
        }
        path = new File(season + ".bin");


        System.out.println("El path existe? "+path.exists());
        if (path.exists()){
            raf = new RandomAccessFile(path, "rw");
            backup();
        } else {
            raf = new RandomAccessFile(path, "rw");
            clearLeague();
        }
        makeTeamDirectory(NUM_TEAMS);

        menu();
    }

    private void backup() throws IOException {
        String pathBackup = "sources" + FS + "backup" + FS + season;

        File backupDirectory = new File(pathBackup); //sources/backup/2017-2018/2017-2018(x).bin
        if (!backupDirectory.exists()) {
            backupDirectory.mkdirs();
            backupDirectory.createNewFile();
        }

        for (int i = 0; i < MAX_NUM_BACKUPS; i++) {
            File backup = new File(pathBackup + FS + season + "(" + (i + 1) + ").bin");
            if (!backup.exists()) {
                copyFiles(season + ".bin", pathBackup + FS + season + "(" + (i + 1) + ").bin");
                break;
            }
        }
    }

    public void position(int t1, int t2) throws IOException {
        int pos = TOTAL_SIZE * NUM_TEAMS * (t1 - 1) + (TOTAL_SIZE * (t2 - 1));
        raf.seek(pos);
    }

    private void setup() throws IOException, ParseException {
        setLanguage();

        configParser();

        System.out.print(jsonObject.get("season"));
        season = sc.nextLine();

        System.out.print(jsonObject.get("sponsor"));
        sponsor = sc.nextLine();

        properties.setProperty("language", language);
        properties.setProperty("season", season);
        properties.setProperty("sponsor", sponsor);
        properties.store(new FileOutputStream("league.properties"), null);
        System.out.println("\n\n");
    }

    public void writeGame(int t1, int t2, Game game) throws IOException {
        position(t1, t2);
        game.write(raf);
    }

    private void addGame() throws Exception {
        int pt1, pt2, t1, t2;
        String referee, date;

        System.out.println(jsonObject.get("adding"));

        System.out.println(jsonObject.get("team") + "1");
        t1 = Integer.parseInt(sc.nextLine());

        System.out.println(jsonObject.get("team") + "2");
        t2 = Integer.parseInt(sc.nextLine());

        System.out.print(jsonObject.get("pTeam1"));
        pt1 = Integer.parseInt(sc.nextLine());

        System.out.print(jsonObject.get("pTeam2"));
        pt2 = Integer.parseInt(sc.nextLine());

        System.out.print(jsonObject.get("refereeName"));
        referee = sc.nextLine();

        System.out.print(jsonObject.get("date"));
        date = sc.nextLine();

        if (t1 != t2) {
            writeGame(t1, t2, new Game((short) pt1, (short) pt2, referee, date));
        } else {
            throw new Exception();
        }
    }

    public void addTeamName() throws Exception {
        String name;
        int team;
        System.out.print(jsonObject.get("teamName"));
        name = sc.nextLine();
        System.out.print(jsonObject.get("team"));
        team = Integer.parseInt(sc.nextLine());
        if (name.length() > 10) {
            throw new Exception();
        } else {
            position(team, team);
            raf.writeShort(DIAGONAL);
            raf.writeUTF(name);
        }
    }

    public void readGamesOfTeam() throws IOException {
        System.out.println(jsonObject.get("team"));
        int team = Integer.parseInt(sc.nextLine());
        readName(team);
        for (int i = 1; i <= NUM_TEAMS; i++) {
            position(team, i);
            short peek = raf.readShort();
            if (peek != DIAGONAL) {
                System.out.println(new Game(peek, raf.readShort(), raf.readUTF(), raf.readUTF(), language).toString());
            }
        }
    }

    private void readName(int team) throws IOException {
        position(team, team);
        int temp = raf.readShort();
        if (temp == -2) {
            System.out.println(raf.readUTF());
        }
    }

    private void configParser() throws IOException, ParseException {
        Object obj = parser.parse(new InputStreamReader(new FileInputStream("sources" + FS + "lang" + FS + language + ".json")));
        jsonObject = (JSONObject) obj;
    }

    /*
    Llista els idiomes i n'assigna un
     */
    private void setLanguage() {
        ArrayList<String> langs = showFiles("sources" + FS + "lang", ".json");
        System.out.println("Select your language:");
        for (int i = 0; i < langs.size(); i++) {
            System.out.println((i + 1) + ". " + langs.get(i));
        }
        System.out.print("Option: ");
        language = langs.get(Integer.parseInt(sc.nextLine()) - 1);
    }

    private ArrayList<String> showFiles(String path, String extension) {
        ArrayList<String> languages = new ArrayList<>();

        File file = new File(path);
        file.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                if (name.toLowerCase().endsWith(extension)) {
                    languages.add(name.substring(0, name.indexOf(".")));
                    return true;
                }
                return false;
            }
        });

        return languages;
    }

    private void copyFiles(String from, String to) throws IOException {
        Path src = Paths.get(from);
        Path dest = Paths.get(to);
        Files.copy(src, dest, REPLACE_EXISTING);
    }

    public void menu() throws Exception {
        System.out.println("\nAntonio Aguire\n\n");
        System.out.println(jsonObject.get("menu"));
        int option = Integer.parseInt(sc.nextLine());
        switch (option) {
            case 1:
                demo();
                //menu();
                break;
            case 2:
                addGame();
                break;
            case 3:
                addTeamName();
                break;
            case 4:
                readGamesOfTeam();
                break;
            case 5:
                System.out.println(this.toString());
                break;
            case 6:
                clearLeague();
                break;
        }
    }

    private void clearLeague() throws Exception {
        writeGame(1, 1, new Game((short) -2, (short) 00, "X", "XXXXXX"));
        for (int local = 0; local < NUM_TEAMS; local++) {
            for (int visit = 0; visit < NUM_TEAMS; visit++) {
                position(local+1, visit+1);
                if (local == visit) {
                    raf.writeShort(DIAGONAL);
                } else {
                    raf.writeShort(NOT_PLAYED);
                }
            }
        }
    }

    private void makeTeamDirectory(int numTeams) throws IOException {
        for (int i = 0; i < numTeams; i++) {
            File teamFile = new File("t" + (i + 1));
            if (!teamFile.exists()) {
                teamFile.mkdir();
            }
            teamFile.createNewFile();
        }
    }

    private void demo() throws Exception {
        writeGame(1, 1, new Game((short) -2, (short) 00, "X", "XXXXXX"));
        for (int local = 0; local < NUM_TEAMS; local++) {
            for (int visit = 0; visit < NUM_TEAMS; visit++) {
                if (local == visit) {
                    raf.writeShort(DIAGONAL);
                } else {
                    writeGame(local + 1, visit + 1, new Game((short) 99, (short) 99, "Akira", "170213"));
                }
            }
        }
        writeGame(1, 2, new Game((short) 123, (short) 123, "Sara", "171111"));
        writeGame(1, 3, new Game((short) 123, (short) 123, "Sara", "171111"));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < NUM_TEAMS; i++) {
            for (int j = 0; j < NUM_TEAMS; j++) {
                if (i!=j){
                    sb.append((i+1)+" vs "+(j+1)+"\n");
                    try {
                        position(i+1, j+1);
                        int seek = raf.readShort();
                        if (seek==NOT_PLAYED){
                            sb.append(jsonObject.get("notPlayed")+"\n");
                        } else {
                            sb.append(new Game((short)seek, raf.readShort(), raf.readUTF(), raf.readUTF(), language).toString());
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    sb.append("=========\n");
                }
            }
        }
        return sb.toString();
    }
}
