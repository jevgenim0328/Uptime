package pack;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class Converter implements ConverterInterface {
	static final String DEFAULT_CURRENCY_NAME_FOR_AMAZON_PRICE = "USD";
//Constructor.
	public Converter() {
	}
// Currency converter, work online once, get ratio from WEB-service and return it.
//
 //______________________________________________________________	
	public double convert(String convertFrom, String convertTo) {
      String str1 = "http://www.webservicex.net/currencyconvertor.asmx/ConversionRate?FromCurrency=";
      if(convertFrom == null)     	  convertFrom = DEFAULT_CURRENCY_NAME_FOR_AMAZON_PRICE;
      if(convertFrom.length()==0 )    convertFrom = DEFAULT_CURRENCY_NAME_FOR_AMAZON_PRICE;

      String requestUrl = str1 + convertFrom +"&ToCurrency=" + convertTo;
      String ratioStr = null;
      try {
          DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
          DocumentBuilder db = dbf.newDocumentBuilder();
// System.out.println("Päring " + requestUrl);         
          Document doc = db.parse(requestUrl);
          doc.getDocumentElement().normalize();   
          Node row = doc.getFirstChild();
          ratioStr = row.getTextContent();
      } catch (Exception e) {
          throw new RuntimeException(e);
      }
      return stringToDouble(ratioStr);
	}
	
	//Convert string "1234.56789" to double 1234.56789
 //___________________________________________________
    public static double stringToDouble(String str) {
        double celoe;
        double mantissa;
        int count;
        int razrjad = 0;
        String s1;
        double rezult;
        count = str.indexOf('.');
        if(count!=-1) {
            celoe = (double) Uptime.stringToInt(str.substring(0,count));
            s1 = str.substring(count+1);
            mantissa = (double) Uptime.stringToInt(s1);
            razrjad = s1.length();
            rezult =  (celoe + mantissa * Math.pow(0.1,razrjad));
         } else  {
        	rezult = (double)Uptime.stringToInt(str);
         }
        return rezult;
       }
	
}
