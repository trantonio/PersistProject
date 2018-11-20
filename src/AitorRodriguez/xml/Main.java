package AitorRodriguez.xml;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {

        MyReader mr = new MyReader("sources/world.xml");

        System.out.println("Antonio Aguirre\n"+mr.cityWithSea("Spain"));
    }
}
