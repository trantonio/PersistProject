package AguirreAntonio.DDBB;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {

        ReadDB Rdb = new ReadDB("Data/resource/Piso.xml");

        System.out.println(Rdb.namePerson());



        ReadDB mr = new ReadDB("Data/resource/world.xml");

        System.out.println("Antonio Aguirre\n"+mr.cityWithSea("Spain"));
    }
}
