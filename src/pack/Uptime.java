package pack;
/**********************************************************************************************
 * Copyright 2009 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file 
 * except in compliance with the License. A copy of the License is located at
 *
 *       http://aws.amazon.com/apache2.0/
 *
 * or in the "LICENSE.txt" file accompanying this file. This file is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under the License. 
 *
 * ********************************************************************************************
 *
 *  Amazon Product Advertising API
 *  Signed Requests Sample Code
 *
 *  API Version: 2009-03-31
 *
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/*
 * This class shows how to make a simple authenticated ItemLookup call to the
 * Amazon Product Advertising API.
 * 
 * See the README.html that came with this sample for instructions on
 * configuring and running the sample.
 */
public class Uptime {
    /*
     * Your AWS Access Key ID, as taken from the AWS Your Account page.
     */
    private static String AWS_ACCESS_KEY_ID;

    /*
     * Your AWS Secret Key corresponding to the above ID, as taken from the AWS
     * Your Account page.
     */
    private static String AWS_SECRET_KEY;

    /*
     * Use one of the following end-points, according to the region you are
     * interested in:
     * 
     *      US: ecs.amazonaws.com 
     *      CA: ecs.amazonaws.ca 
     *      UK: ecs.amazonaws.co.uk 
     *      DE: ecs.amazonaws.de 
     *      FR: ecs.amazonaws.fr 
     *      JP: ecs.amazonaws.jp
     * 
     */
    private static final String ENDPOINT = "ecs.amazonaws.com";  // .com

    /*
     * The Item ID to lookup. The value below was selected for the US locale.
     * You can choose a different value if this value does not work in the
     * locale of your choice.
     */
//    private static final String ITEM_ID = "";
	static String pair = "";
	static double value = 0.0;
    static List<Goods> goodsList;
//_____________________________________________
    public static void processingRequest(String inputParam) {

        SignedRequestsHelper helper;
        String requestUrl = null;
        
    	Path path = Paths.get("c:\\temp", "AWS.txt");
    	String cvsSplitBy = ";";
       	List<String> read = null;
    	
		try {
			read = Files.readAllLines(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] account = read.get(0).split(cvsSplitBy);
		AWS_ACCESS_KEY_ID = account[0];
		AWS_SECRET_KEY = account[1];
        
        
        try {
            helper = SignedRequestsHelper.getInstance(ENDPOINT, AWS_ACCESS_KEY_ID, AWS_SECRET_KEY);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
//   System.out.println("Sisestatud on " + inputParam);

   		Map<String, String> params = new HashMap<String, String>();
        params.put("Service", "AWSECommerceService");
        params.put("Version", "2015-06-01");
/*         
         params.put("Operation", "ItemLookup");    //ItemLookup kuni 150   ; ItemSearch 10  
         params.put("IdType", "UPC");
        params.put("ItemId","090733305015");
//        params.put("VariationPage","130");
         params.put("SearchIndex","All");  //All     ;  ItemSearch -i puhul
*/        
         params.put("Operation", "ItemSearch");    //ItemLookup kuni 150   ; ItemSearch 10  
        params.put("SearchIndex","All");  //All     ;  ItemSearch -i puhul
   //     params.put("Keywords", "Lenovo laptop I5 17-inch");
  
        params.put("Keywords", inputParam);
           
        params.put("ResponseGroup", "Large");
        params.put("AssociateTag","mytag-20");  // th0426-20      mytag-20

        requestUrl = helper.sign(params);       // Load personal data for login to amazon service.
        goodsList = fetchTitle(requestUrl);
    }

    // Calculate optimal choice for  currency exchange for buy this goods.
    // Save results to list.
  //__________________________________  
    static void spekulanto(Goods g) {
    	String convertTo = "EUR";
    	if(!pair.equals(g.getCurrencyBuy() + convertTo) || pair==null) { // Do new request, if needed.
    	  Converter c = new Converter();
    	  value = c.convert(g.getCurrencyBuy(),convertTo);
    	  pair = g.getCurrencyBuy() + convertTo;                  
    	  }
    	g.setCurrencyGet(convertTo);
    	g.setConvertedPrice(trunkTwo(g.getPrice() * value));
    }	

 //Save only 2 place after comma, trunk other.
    private static Double trunkTwo(Double x) {
    	int temp = (int)(x * 100);
    	 return ((double)temp)/100;
    }
	/*
     * Utility function to fetch the response from the service and extract the
     * title, price and currency code from the XML to the list. (XML parsing)
     */
  //_________________________________________________________  
    private static List<Goods> fetchTitle(String requestUrl) {
        List<Goods> goods = new ArrayList<Goods>();
        String title = null;
        String pildiLink = null;
        String price="";
        String currency = null;
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
// System.out.println(requestUrl);        
            Document doc = db.parse(requestUrl);
            doc.getDocumentElement().normalize();   
           
            NodeList leht = doc.getElementsByTagName("Item");   //  Get items info from list of answers.
            for (int i = 0; i <leht.getLength(); i++) {          // Get one product from list
           	Node row = leht.item(i);							 // Extract one product data from doc
            	if(row.getNodeType()==Node.ELEMENT_NODE) {
            		Element elm1 = (Element) row;                  //Extract product name.
            		title = elm1.getElementsByTagName("Title").item(0).getTextContent();    // Get product name
                    Node subRow = elm1.getElementsByTagName("ListPrice").item(0);      
                if(subRow!=null && subRow.getNodeType()==Node.ELEMENT_NODE) {
                	  Element elm3 = (Element) subRow;
             	      price    = elm3.getElementsByTagName("Amount")        .item(0).getTextContent();  //Get price of product.
             	      currency = elm3.getElementsByTagName("CurrencyCode")  .item(0).getTextContent();  //Get currency code of price.             	      if(price.equals(null)) {                                // If usual price not exist, catch LowertNewPrice.
             	      if(price==null || currency==null)                   // Maybe this price not simple.
             	        subRow   = elm1.getElementsByTagName("LowestNewPrice").item(0);    // Try this type of price.
                      if(subRow.getNodeType()==Node.ELEMENT_NODE) {
                        elm3 = (Element) subRow;                       
                        price    = elm3.getElementsByTagName("Amount")        .item(0).getTextContent();
                        currency = elm1.getElementsByTagName("CurrencyCode")  .item(0).getTextContent();
                          }
            	      }
          // Ostime toode pilt ülesse.      
                Node subRow2 = elm1.getElementsByTagName("SmallImage").item(0);    // Get product image
                if(subRow2!=null && subRow2.getNodeType()==Node.ELEMENT_NODE) {
                	Element elm4 = (Element) subRow2;
           	      pildiLink    = elm4.getElementsByTagName("URL").item(0).getTextContent();     //Get price URL of product.
         	      if(pildiLink==null)   {                // Maybe this price not simple.
           	        subRow2   = elm1.getElementsByTagName("LowestNewPrice").item(0);    // Try this type of price.
                    if(subRow2.getNodeType()==Node.ELEMENT_NODE) {
                      Element elm5 = (Element) subRow2; 
                      pildiLink    = elm5.getElementsByTagName("SmallImage").item(0).getTextContent();
                    }
                         }
                      }
                   }
        		Goods ese = new Goods(title,trunkTwo(fStringToDouble(price)),currency,pildiLink);   // Create new record (object).
        		goods.add(ese);                                                //Save it to the list. 
//        		System.out.println("Lisatud " + ese);
           } 	
        } catch (Exception e) {
        	System.out.println("XML parsing faild.");
            throw new RuntimeException(e);
        }
        return goods;
    }
 
    // Utility convert string ("123456") to double 1234.56 .
    //   
  //_____________________________________________________  
    public static double fStringToDouble(String str) {
        double celoe = 0.0;
        double mantissa;
        int count;
        double sign = 1.0;
        int razrjad = 0;
        String s1;
        double rezult;
            count = str.length()-2;
            if(count<0) {
            	count = 0;
              }	else {
                 celoe = (double) stringToInt(str.substring(0,count));
              }
            s1 = str.substring(count);
           mantissa = (double) stringToInt(s1);
            razrjad = s1.length();
            if(s1.length()==1 || (s1.length()==2 && s1.charAt(0)=='-')) {
            	razrjad = 2;
            }
            if(celoe<0) {
            	sign = -1.0;
            }
            rezult =  (celoe + sign * mantissa * Math.pow(0.1,razrjad ));
        return rezult;
       }
 // Additional function, supply previous and similar.  
  //____________________________________________  
    public static int stringToInt(String s) {
    	int count = 0;
    	int rezult = 0;
    	int nummer = 0;
    	boolean isNegative = false;
    	if(s.length()==0) {
    		return 0;
    	}
    	if(s.charAt(0)=='-') {
    		isNegative = true;
    		count++;
    	}
    	
    	while(count<s.length()) {
    		nummer = s.charAt(count++)-'0';
    		  if(nummer<0 || nummer>9)
    			  return -1;
     		rezult = rezult * 10 + nummer ;
     	}
    	if(isNegative) {
    		rezult *= -1;
    	}
    return rezult;	
    }

}
