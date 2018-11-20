package AitorRodriguez.xml;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public class MyReader extends DomReader {
    public MyReader(String xml) throws ParserConfigurationException, SAXException, IOException {
        super(xml);
    }

    public List<String> cityWithSea(String countryName){
        return super.extractList("//country[name='"+countryName+"']//city/located_at[@watertype='sea']/../name/text()");
    }
}
