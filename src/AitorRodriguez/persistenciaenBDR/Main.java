package AitorRodriguez.persistenciaenBDR;

import AguirreAntonio.ahelp.Constantes;

public class Main implements Constantes {
    public static void main(String[] args) throws Exception {
        ConnectionManager cm = new ConnectionManager();

        cm.connect(DB_URL,DB_USER,DB_PASSWD);
        cm.createTables();
        cm.insertCountry("Spain");
        cm.insertCity("Barcelona","Bcn");
        System.out.println(cm.listCountryCities("Barcelona"));

        cm.disconnect();
    }
}
