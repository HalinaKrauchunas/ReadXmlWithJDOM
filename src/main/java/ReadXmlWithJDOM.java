import org.jdom2.*;
import org.jdom2.output.*;

import java.text.*;
import java.util.*;

public class ReadXmlWithJDOM {

    public static void main(String[] args) throws DataConversionException, ParseException {

        JDOMReader jdomReader = new JDOMReader();
        List<Customer> data = jdomReader.getDataFromXML(DataProvider.DATADIR + "customers.xml");

        for (Customer customer : data) {
            System.out.println(customer);
        }
    }
}
