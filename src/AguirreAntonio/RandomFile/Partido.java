package AguirreAntonio.RandomFile;

public class Partido {

    private short id, res1, res2;
    private String nameArbitro;
    private String data = "YYMMDD";

    public Partido(short id, short res1, short res2, String nameArbitro) {
        this.id = id;
        this.res1 = res1;
        this.res2 = res2;
        this.nameArbitro = nameArbitro;
    }
}
