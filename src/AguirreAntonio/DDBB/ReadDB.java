package AguirreAntonio.DDBB;


import AitorRodriguez.xml.DomReader;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public class ReadDB extends DomReader {


        public ReadDB(String xml) throws ParserConfigurationException, SAXException, IOException {
            super(xml);
        }

        public List<String> cityWithSea(String countryName){
            return super.extractList("//country[name='"+countryName+"']//city/located_at[@watertype='sea']/../name/text()");
        }
    public List<String> namePerson(){
        return super.extractList("//Piso//..//nombre/text()");
    }


}
