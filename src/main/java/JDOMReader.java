import org.jdom2.*;
import org.jdom2.input.*;
import org.jdom2.output.*;

import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;

public class JDOMReader {

    private static final String XMLDATEFORMAT = "yyyy-MM-dd'T'HH:mm:ss";

    public List<Customer> getDataFromXML(String filename) throws DataConversionException, ParseException {

        List<Customer> data = new ArrayList<>();
        File file = new File(filename);

        SAXBuilder saxBuilder = new SAXBuilder();
        Document document;
        try {
            document = saxBuilder.build(file);
        } catch (JDOMException | IOException e) {
            e.printStackTrace();
            return null;
        }

        Element root = document.getRootElement();
        List<Element> customerElements = root.getChildren("customer");

        for (Element customerEl : customerElements) {
            Customer customer = new Customer();
            data.add(customer);

            Attribute attribute = customerEl.getAttribute(Customer.ID);
            customer.setId(attribute.getIntValue());

            customer.setName(customerEl.getChildText(Customer.NAME));
            customer.setPhone(customerEl.getChildText(Customer.PHONE));
            customer.setAbout(customerEl.getChildText(Customer.ABOUT));
            customer.setAge(Integer.parseInt(customerEl.getChildText(Customer.AGE)));
            customer.setActive(Boolean.parseBoolean(customerEl.getChildText(Customer.ACTIVE)));
            customer.setBalance(new BigDecimal(customerEl.getChildText(Customer.BALANCE)));

            DateFormat dateFormat = new SimpleDateFormat(XMLDATEFORMAT);
            customer.setJoined(dateFormat.parse(customerEl.getChildText(Customer.JOINED)));
        }
//        XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
//        String xmlString = xmlOutputter.outputString(document);
//        System.out.println(xmlString);
        return data;
    }
}
